package com.springmvc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.Reservation;
import com.springmvc.model.*;

@Service("ReservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	private Connection connection = null ;
	private String dB_URL = "jdbc:mysql://localhost:3306/" ;
	private String dB_name = "hotel_reservation_system" ;
	private String driver = "com.mysql.jdbc.Driver" ;
	private String userName = "root" ; 
	private String password = "root" ;
	private Statement statement = null ; 
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
	public int makeReservation(Reservation reservation) {
		
		System.out.println(reservation) ;
		
		try {
			connectToDB() ;
			statement = connection.createStatement() ;
			String updateQuery = "insert into reservation(from_date,to_date,total_price,user_id,hotel_id,room_count,room_type,status) " +
					"values('" + reservation.getFromDate() + "', '" + reservation.getToDate() + "', " + reservation.getTotal_price() + " , " + reservation.getUserId() + 
					" , " + reservation.getHotelId() + " , " + reservation.getRoomCount() + " , '" + reservation.getRoomType() + "' , '" + reservation.getStatus() + "');" ;
			int returned = statement.executeUpdate(updateQuery) ;
			return returned ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
			return 0 ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	
	public int deleteReservation(Reservation reservation) {
		try {
			connectToDB() ; 
			statement = connection.createStatement() ;
			String deleteQuery = "update reservation set status='inactive' where reservation_id=" + reservation.getId() + ";" ;
			int returned = statement.executeUpdate(deleteQuery) ;
			return returned ;
		}
		catch(SQLException e) {
			e.printStackTrace() ; return 0 ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	public ArrayList<Reservation> getAllReservations(User user) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			connectToDB() ;
			String query = "SELECT * from reservation where user_id=" + user.getUserId() + ";" ;
			PreparedStatement statement = connection.prepareStatement(query) ;
			resultSet = statement.executeQuery() ;
			while(resultSet.next()) {
				Reservation newReservation = new Reservation() ;
				newReservation.setId(resultSet.getInt("reservation_id")) ;
				newReservation.setFromDate(resultSet.getString("from_date")) ;
				newReservation.setToDate(resultSet.getString("to_date")) ;
				newReservation.setTotal_price(resultSet.getInt("total_price")) ;
				newReservation.setUserId(resultSet.getInt("user_id"));
				newReservation.setHotelId(resultSet.getInt("hotel_id")) ;
				newReservation.setRoomCount(resultSet.getInt("room_count")) ;
				newReservation.setRoomType(resultSet.getString("room_type"));
				newReservation.setStatus(resultSet.getString("status")) ;
				reservations.add(newReservation) ;
			}
			return reservations ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
			return null ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	public ArrayList<Reservation> getBookings(UpdateId updateId) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			connectToDB() ;
			String query = "SELECT * from reservation where hotel_id=" + updateId.getHotelId() + ";" ;
			PreparedStatement statement = connection.prepareStatement(query) ;
			resultSet = statement.executeQuery() ;
			while(resultSet.next()) {
				Reservation newReservation = new Reservation() ;
				newReservation.setId(resultSet.getInt("reservation_id")) ;
				newReservation.setFromDate(resultSet.getString("from_date")) ;
				newReservation.setToDate(resultSet.getString("to_date")) ;
				newReservation.setTotal_price(resultSet.getInt("total_price")) ;
				newReservation.setUserId(resultSet.getInt("user_id"));
				newReservation.setHotelId(resultSet.getInt("hotel_id")) ;
				newReservation.setRoomCount(resultSet.getInt("room_count")) ;
				newReservation.setRoomType(resultSet.getString("room_type"));
				newReservation.setStatus(resultSet.getString("status")) ;
				reservations.add(newReservation) ;
			}
			return reservations ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
			return null ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	
}
