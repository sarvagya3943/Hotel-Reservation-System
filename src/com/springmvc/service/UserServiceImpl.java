package com.springmvc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private static ArrayList<User> users ;
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
	public boolean doesUserExist(User user) {
		if(findByEmail(user.getEmail()) != null) {
			return true ;
		}
		return false ;
	}
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		users = findAllUsers() ;
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user ;
			}
		}
		return null ;
	}

	public User findByID(int user_id) {
		// TODO Auto-generated method stub
		/*users = findAllUsers() ;
		for(User user : users) {
			if(user.getUserId() == user_id) {
				return user ;
			}
		}
		return null ;*/
		try {
			connectToDB() ;
			String query = "SELECT * from user where user_id=" + user_id + ";" ;
			statement = connection.prepareStatement(query) ;
			resultSet = statement.executeQuery() ;
			User newUser = null ;
			while(resultSet.next()) {
				newUser = new User() ;
				newUser.setUserId(resultSet.getInt("user_id")) ;
				newUser.setEmail(resultSet.getString("email")) ;
				newUser.setPassword(resultSet.getString("password")) ;
				newUser.setMobileNum(resultSet.getString("mobile_num")) ;
				newUser.setAdmin(resultSet.getBoolean("is_admin")) ;
				newUser.setLastname(resultSet.getString("last_name")) ;
				newUser.setGender(resultSet.getString("gender")) ;
				newUser.setFirstname(resultSet.getString("first_name")) ;
			}
			return newUser; 
		}
		catch(SQLException e) {
			e.printStackTrace() ; 
			return null ;
		}
		finally {
			closeDBconnection() ;
		}
	}
	
	public ArrayList<User> findAllUsers() {
		// TODO Auto-generated method stub
		connectToDB() ;
		users = new ArrayList<User>();
		try {
			String query = "SELECT * from user" ;
			PreparedStatement statement = connection.prepareStatement(query) ;
			ResultSet resultSet = statement.executeQuery() ;
			while(resultSet.next()) {
				User user = new User() ;
				user.setUserId(resultSet.getInt("user_id")) ;
				user.setEmail(resultSet.getString("email")) ;
				user.setPassword(resultSet.getString("password")) ;
				user.setMobileNum(resultSet.getString("mobile_num")) ;
				user.setFirstname(resultSet.getString("first_name")) ;
				user.setLastname(resultSet.getString("last_name")) ;
				user.setGender(resultSet.getString("gender")) ;
				user.setAdmin(resultSet.getBoolean("is_admin")) ;
				users.add(user) ;
			}
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
		finally {
			closeDBconnection() ;
		}
		return users ;
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		System.out.println(user);
		try {
			connectToDB() ;
			/*String query = "INSERT into user(email,password,mobile_num,is_admin,last_name,gender,first_name) values(?,?,?,?,?,?,?) ;" ;
			String query = "INSERT into user(email,password) values(?,?) ;" ;
			statement = connection.prepareStatement(query) ;
			statement.setString(1,user.getEmail()) ;
			statement.setString(2,user.getPassword()) ;
			statement.setString(3, user.getMobileNum()) ;
			statement.setInt(4, 0) ;
			statement.setString(5, user.getLastname()) ;
			statement.setString(6, "MALE");//user.getGender()) ;
			statement.setString(7, user.getFirstname()) ; 
			System.out.println(statement) ;
			int returned = statement.executeUpdate() ;
			if(returned == 1) users.add(user) ;
			return returned ; */
			Statement stmt = connection.createStatement() ;
			String query = "insert into user (email, first_name, last_name, password, mobile_num , is_admin, gender) values ('"+ user.getEmail()+"','"+ 
							user.getFirstname()+"','"+user.getLastname() +"','"+ user.getPassword()+"','"+
							user.getMobileNum()+"',false,'" + user.getGender() + "');" ;
			System.out.println(query) ;
			int returned = stmt.executeUpdate(query) ;
			if(returned == 1) users.add(user) ;
			return returned ;
		}
		catch(SQLException e) {
			e.printStackTrace() ; return 0 ;
		}
		finally {
			closeDBconnection() ;
		}
		
	}
	

}

