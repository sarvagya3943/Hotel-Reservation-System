package com.springmvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.model.User;
import com.springmvc.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		System.out.println("Inside User Controller") ;
		if(userService.doesUserExist(user)) {
			System.out.println("A user with email id " + user.getEmail() + " already exist") ;
			return new ResponseEntity<User>(user,HttpStatus.CONFLICT) ;
		}
		int returned = userService.saveUser(user) ;
		HttpHeaders headers = new HttpHeaders() ;
		System.out.println(user + " " + returned) ;
		if(returned == 1) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED) ;
		}
		else {
			return new ResponseEntity<User>(user ,HttpStatus.CONFLICT) ;
		}
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByEmail(@RequestBody User user) {
		System.out.println("Inside java controller " + user) ;
		User newUser = userService.findByEmail(user.getEmail()) ;
		if(newUser == null) {
			return new ResponseEntity<User>(newUser, HttpStatus.NOT_FOUND) ;
		}
		return new ResponseEntity<User>(newUser, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/setCred" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setCurrentUser(@RequestBody User user , HttpServletRequest req) {
		req.getSession().setAttribute("currentUser", user) ;
		System.out.println(req.getSession().getAttribute("currentUser")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/clearCred" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> clearCurrentUser(HttpServletRequest req) {
		System.out.println(req.getSession().getAttribute("currentUser")) ;
		req.getSession().removeAttribute("currentUser") ;
		req.getSession().removeAttribute("searchObject") ;
		req.getSession().removeAttribute("searchHotels") ;
		req.getSession().removeAttribute("jsonInfo") ;
		req.getSession().removeAttribute("bookingInfo") ;
		
		System.out.println("Logged out user is " + req.getSession().getAttribute("currentUser")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getCurrentUser" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getCurrentUser(HttpServletRequest req) {
		System.out.println("CurrentUser is " + req.getSession().getAttribute("currentUser")) ;
		User user = (User) req.getSession().getAttribute("currentUser") ;
		if(user == null) return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND) ; 
		return new ResponseEntity<User>(user , HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getByid" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByID(@RequestBody User user) {
		System.out.println("Inside java controller " + user.getUserId()) ;
		User newUser = userService.findByID(user.getUserId()) ;
		if(newUser == null) {
			return new ResponseEntity<User>(newUser, HttpStatus.NOT_FOUND) ;
		}
		return new ResponseEntity<User>(newUser, HttpStatus.OK) ;
	}
	
}
