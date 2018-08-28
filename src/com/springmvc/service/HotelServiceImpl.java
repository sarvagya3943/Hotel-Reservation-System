package com.springmvc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.Hotel;
import com.springmvc.model.HotelInfo;
import com.springmvc.model.SearchObject;
import com.springmvc.model.*;

@Service("HotelService")
@Transactional

public class HotelServiceImpl implements HotelService {
	
	private Connection connection = null ;
	
	private String dB_URL = "jdbc:mysql://localhost:3306/" ;
	private String dB_name = "hotel_reservation_system" ;
	private String driver = "com.mysql.jdbc.Driver" ;
	private String userName = "root" ; 
	private String password = "root" ;
	private PreparedStatement statement = null ;
	private ResultSet resultSet = null ;
	
	private void connectToDB() {
		System.out.println("Trying to connect to database.");
		try {
			Class.forName(driver).newInstance() ;
			connection = DriverManager.getConnection(dB_URL + dB_name, userName , password) ;
		}
		catch(Exception e) {
			System.out.println("Could not connect to the database !" + e.toString()) ;
		}
	}

	
	private void closeDBconnection() {
		try {
			if(!connection.isClosed()) {
				connection.close() ;
			}
		}
		catch(Exception e) {
			System.out.println("Could not close the database !" + e.toString()) ;
		}
	}
	@Override
	public ArrayList<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		ArrayList<Hotel> allHotels = new ArrayList<Hotel>();
		connectToDB() ;
		try {
			String queryString = "SELECT * from hotehhhhhl" ;
			statement = connection.prepareStatement(queryString) ;
			resultSet = statement.executeQuery() ;
			while(resultSet.next()) {
				Hotel newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));
				allHotels.add(newHotel) ;
			}
			closeDBconnection() ;
			return allHotels ;
		}
		catch(Exception e) {
			System.out.println("Error while getting all hotels " + e.toString()) ;
			return null;
		}
	
	}
	
	public String convertDate(Date date) {
		String pattern = "yyyy-MM-dd" ;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern) ;
		return simpleDateFormat.format(date) ;
	}

	@Override
	public ArrayList<HotelInfo> getAllHotelsBySearch(SearchObject searchObj) {
		// TODO Auto-generated method stub
		ArrayList<HotelInfo> allHotelsInfo = new ArrayList<HotelInfo>();
		System.out.println(searchObj + " " + searchObj.getCheckInStr().getClass().getName()) ;
		connectToDB() ;
		try {
			//TODO
			String queryString = "SELECT hotel.hotel_id , hotel.name , hotel.city , hotel_room_type.type , hotel_room_type.count , hotel_room_type.cost , hotel_room_type.extra_guest_price , hotel_room_type.max_extra_guests , (hotel_room_type.capacity - reservation.room_count) as available" + 
					" FROM hotel " + 
					"join hotel_room_type on hotel.hotel_id=hotel_room_type.hotel_id " + 
					"left join reservation on hotel.hotel_id=reservation.hotel_id and reservation.room_type=hotel_room_type.type" + 
					" where city like '%" + searchObj.getCity() + "%' and status='active' and '" + searchObj.getCheckInStr() + "' < reservation.to_date and reservation.from_date < '" + searchObj.getCheckOutStr() + "'" + 
					" UNION " + 
					"SELECT hotel.hotel_id , hotel.name , hotel.city , hotel_room_type.type , hotel_room_type.count , hotel_room_type.cost , hotel_room_type.extra_guest_price , hotel_room_type.max_extra_guests , hotel_room_type.capacity as available" + 
					" FROM hotel " + 
					"join hotel_room_type on hotel.hotel_id=hotel_room_type.hotel_id " + 
					"left join reservation on hotel.hotel_id=reservation.hotel_id and reservation.room_type=hotel_room_type.type" + 
					" where city like '%" + searchObj.getCity() + "%' and ('" + searchObj.getCheckInStr() + "' > reservation.to_date or status='inactive' or reservation_id is NULL or reservation.from_date > '" + searchObj.getCheckOutStr() + "');" ;
			System.out.println(queryString) ;
			Statement stmt = connection.createStatement() ;
			resultSet = stmt.executeQuery(queryString) ;
			while(resultSet.next()) {
				HotelInfo newHotelInfo = new HotelInfo();
				newHotelInfo.getHotel().setHotel_id(resultSet.getInt("hotel_id")) ;
				newHotelInfo.getHotel().setName(resultSet.getString("name")) ;
				newHotelInfo.getHotel().setCity(resultSet.getString("city")) ;
				newHotelInfo.getRoomType().setType(resultSet.getString("type")) ;
				newHotelInfo.getRoomType().setCount(resultSet.getInt("count")) ;
				newHotelInfo.getRoomType().setCost(resultSet.getInt("cost")) ;
				newHotelInfo.getRoomType().setExtraGuestPrice(resultSet.getInt("extra_guest_price")) ;
				newHotelInfo.getRoomType().setMaxExtraGuests(resultSet.getInt("max_extra_guests")) ;
				newHotelInfo.setAvailableRooms(resultSet.getInt("available")) ;
				System.out.println(newHotelInfo) ;
				allHotelsInfo.add(newHotelInfo) ;
			}
		}
		catch(Exception e) {
			System.out.println("Error while getting filtered hotels " + e.toString()) ;
		}
		finally {
			closeDBconnection() ;
			return allHotelsInfo ;
		}
	}
	@Override
	public ArrayList<Hotel> getHotelsForAdmin(SearchForAdmin searchForAdmin) {
		// TODO Auto-generated method stub
		ArrayList<Hotel> allHotels = new ArrayList<Hotel>();
		try{
			
			if((searchForAdmin.getCity() == null || searchForAdmin.getCity() == "") && (searchForAdmin.getName()!=null || searchForAdmin.getName()!="")){
				connectToDB();
				String queryString = "SELECT * from hotel where name like '%"+searchForAdmin.getName()+"%'" ;
				statement = connection.prepareStatement(queryString) ;
				resultSet = statement.executeQuery() ;
				while(resultSet.next()) {
					Hotel newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));
					allHotels.add(newHotel) ;
				}
			}
				
			else if((searchForAdmin.getName() == null || searchForAdmin.getName() == "") && (searchForAdmin.getCity()!=null || searchForAdmin.getCity()!="")){
					connectToDB();
					String queryString = "SELECT * from hotel where city like '%"+searchForAdmin.getCity()+"%'" ;
					statement = connection.prepareStatement(queryString) ;
					resultSet = statement.executeQuery() ;
					while(resultSet.next()) {
						Hotel newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));
						allHotels.add(newHotel) ;
					}
				}
			
			else if((searchForAdmin.getName() == null || searchForAdmin.getName() == "") && (searchForAdmin.getCity()==null || searchForAdmin.getCity()=="")){
					connectToDB();
					String queryString = "SELECT * from hotel" ;
					statement = connection.prepareStatement(queryString) ;
					resultSet = statement.executeQuery() ;
					while(resultSet.next()) {
						Hotel newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));
						allHotels.add(newHotel) ;
					}
				}
				else {
					connectToDB();
					String queryString = "SELECT * from hotel where city like '%"+searchForAdmin.getCity()+"%' and name like '%"+searchForAdmin.getName()+"%'" ;
					statement = connection.prepareStatement(queryString) ;
					resultSet = statement.executeQuery() ;
					while(resultSet.next()) {
						Hotel newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));
						allHotels.add(newHotel) ;
					}

				}	
		}catch(Exception e) {
			System.out.println("Error while getting all hotels " + e.toString()) ;
		}
		finally {
			closeDBconnection() ;
			
		}
		return allHotels ;
	}
	public int addHotels(HotelRoom hotelRoom){
		//		System.out.println(hotelRoom);
		ArrayList<HotelRoom> allHotelRooms = new ArrayList<HotelRoom>();
		try{
			connectToDB();
			Statement stmt =  connection.createStatement() ;
			String query = "insert into hotel(name , city) values ('"+hotelRoom.getName()+"' , '"+hotelRoom.getCity()+"')";
			int returned = stmt.executeUpdate(query) ;


			String query2 = "SELECT * from hotel where name = '"+hotelRoom.getName()+"' and city='"+hotelRoom.getCity()+"'";
			PreparedStatement statement = connection.prepareStatement(query2) ;
			ResultSet resultSet = statement.executeQuery() ;
			Hotel newHotel = null;
			while(resultSet.next()) {
				newHotel = new Hotel(resultSet.getInt("hotel_id"),resultSet.getString("name"),resultSet.getString("city"));

			}
				System.out.println("Hotel max guests ; " +hotelRoom.getCapacity());
			Statement stmt2 = (Statement) connection.createStatement();
			String query3 = "insert into hotel_room_type( hotel_id , type , cost , capacity , extra_guest_price , max_extra_guests ) values('"+newHotel.getHotel_id()+"' , '"+hotelRoom.getRoomType()+"' , '"+hotelRoom.getPrice()+"', '"+hotelRoom.getCapacity()+"' , '"+hotelRoom.getExtraGuestPrice()+"' , '"+hotelRoom.getMaxExtraGuests()+"')";
			int returned2 = stmt2.executeUpdate(query3);

			if(returned == 1 && returned2 ==1) allHotelRooms.add(hotelRoom) ;
			return 1 ;
		}
		catch(SQLException e) {
			e.printStackTrace() ; return 0 ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	@Override
	public int getHotelByNameCity(HotelRoom hotelRoom) {
		int flag = 0;
		// TODO Auto-generated method stub
		ArrayList<Hotel> hotelNameCity = getAllHotels();
		for( Hotel hotel : hotelNameCity ) {
			if(hotel.getName() == hotelRoom.getName() && hotel.getCity() == hotelRoom.getCity()) {
				flag = 1;
				break;
			}
		}
		return flag;
	}
	@Override
	public int deleteHotelById(Hotel hotel){
		try{
			connectToDB();
			Statement stmt =  connection.createStatement() ;
			String query = "delete from hotel where hotel_id = '"+hotel.getHotel_id()+"'";
			//			String query2 = "delete from hotel_room_type where hotel_id = '"+hotel_id+"'";
			int r= stmt.executeUpdate(query);
//			Statement stmt2 =  connection.createStatement() ;
//			String query2 = "delete from hotel where hotel_id = '"+hotel.getHotel_id()+"'";
//			int r2= stmt.executeUpdate(query2);
			if(r==1  )
				return 1;


		}
		catch (SQLException e) {
			e.printStackTrace(); return 0;
		}
		finally
		{
			closeDBconnection();
		}
		return 1;

	}


	@Override
	public ArrayList<UpdateHotel> getHotelForUpdate(UpdateId updateId) {
		// TODO Auto-generated method stub
		ArrayList<UpdateHotel> updateHotel = new ArrayList<UpdateHotel>();
		try{
			System.out.println(updateId.getHotelId());
			connectToDB();
			String a = "AC";
			String b = "NON-AC";
			String query = "SELECT * FROM hotel_room_type where hotel_id = '"+updateId.getHotelId()+"' and type ='"+a+"' ";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				UpdateHotel updateHotel1 = new UpdateHotel(resultSet.getString("type") , resultSet.getInt("cost") , resultSet.getInt("extra_guest_price") , resultSet.getInt("max_extra_guests"));
			updateHotel.add(updateHotel1);
			}
			
			String query2 = "SELECT * FROM hotel_room_type where hotel_id = '"+updateId.getHotelId()+"' and type ='"+b+"' ";
			Statement stmt =  connection.createStatement() ;
			resultSet = stmt.executeQuery(query2);
			while(resultSet.next()){
				UpdateHotel updateHotel2 = new UpdateHotel(resultSet.getString("type") , resultSet.getInt("cost") , resultSet.getInt("extra_guest_price") , resultSet.getInt("max_extra_guests"));
			updateHotel.add(updateHotel2);
			}
			System.out.println(updateHotel);
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				closeDBconnection();
			}
		return updateHotel;
	}


	@Override
	public Room changeRoom(Room room) {
		// TODO Auto-generated method stub
		Room newRoom = new Room();
		try{
			System.out.print(room);
			connectToDB();
			String a = "a";
			String b = "b";
			Statement stmt =  connection.createStatement() ;
			String query = "UPDATE hotel_room_type SET max_extra_guests = '"+room.getMaxA()+"' , extra_guest_price ='"+room.getPriceEA()+"', cost = '"+room.getPrice()+"' where hotel_id = '"+room.getHotelId()+"' and type ='"+a+"'";
			int r = stmt.executeUpdate(query) ;
			
			Statement stmt2 =  connection.createStatement() ;
			String query2 = "UPDATE hotel_room_type SET max_extra_guests = '"+room.getMaxB()+"' , extra_guest_price ='"+room.getPriceEB()+"', cost = '"+room.getPriceB()+"' where hotel_id = '"+room.getHotelId()+"' and type ='"+b+"'";
			int r2 = stmt2.executeUpdate(query2);
			System.out.println(room);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			closeDBconnection();
		}
		
		
		return newRoom;
	}


	
}
