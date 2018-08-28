package com.springmvc.controllers;

import java.util.ArrayList;

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

import com.mysql.fabric.xmlrpc.base.Array;
import com.springmvc.model.*;
import com.springmvc.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService ;
	
	@RequestMapping(value="/book" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> doReservations(@RequestBody ArrayList<Reservation> reservations) {
		int allOk = 1; 
		for(Reservation reservation : reservations) {
			allOk &= reservationService.makeReservation(reservation) ;
		}
		if(allOk == 1) return new ResponseEntity<Void>(HttpStatus.CREATED) ;
		return new ResponseEntity<Void>(HttpStatus.CONFLICT) ;
	}
	@RequestMapping(value="/getBookings" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reservation>> getReservations(@RequestBody UpdateId updateId) {
		ArrayList<Reservation> reservations = reservationService.getBookings(updateId) ;
		System.out.println(updateId.getHotelId());
		System.out.println(reservations);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ArrayList<Reservation>>(reservations, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getBookingsByUser" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reservation>> getReservations(@RequestBody User user) {
		ArrayList<Reservation> reservations = reservationService.getAllReservations(user) ;
		System.out.println(user);
		System.out.println(reservations);
		return new ResponseEntity<ArrayList<Reservation>>(reservations, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getJsonInfo" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<HotelInfo>> getJsonInfo(HttpServletRequest req) {
		//System.out.println("Current Search hotel Object is " + req.getSession().getAttribute("searchHotels")) ;
		ArrayList<HotelInfo> jsonInfo = (ArrayList<HotelInfo>) req.getSession().getAttribute("jsonInfo") ;
		if(jsonInfo == null) return new ResponseEntity<ArrayList<HotelInfo>>(jsonInfo, HttpStatus.NOT_FOUND) ; 
		return new ResponseEntity<ArrayList<HotelInfo>>(jsonInfo, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/setJsonInfo" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setJsonInfo(@RequestBody ArrayList<HotelInfo> hotelInfoArr, HttpServletRequest req) {
		req.getSession().setAttribute("jsonInfo", hotelInfoArr) ;
		System.out.println("Search Hotel list : " + req.getSession().getAttribute("jsonInfo")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getBookingInfo" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<BookingInfo>> getBookingInfo(HttpServletRequest req) {
		//System.out.println("Current Search hotel Object is " + req.getSession().getAttribute("searchHotels")) ;
		ArrayList<BookingInfo> bookingInfo = (ArrayList<BookingInfo>) req.getSession().getAttribute("bookingInfo") ;
		if(bookingInfo == null) return new ResponseEntity<ArrayList<BookingInfo>>(bookingInfo, HttpStatus.NOT_FOUND) ; 
		return new ResponseEntity<ArrayList<BookingInfo>>(bookingInfo, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/setBookingInfo" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setBookingInfo(@RequestBody ArrayList<BookingInfo> BookingInfoArr, HttpServletRequest req) {
		req.getSession().setAttribute("bookingInfo", BookingInfoArr) ;
		System.out.println("Booking Info list : " + req.getSession().getAttribute("bookingInfo")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/deleteBooking" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteBooking(@RequestBody Reservation reservation) {
		int returned = reservationService.deleteReservation(reservation) ;
		if(returned == 0) return new ResponseEntity<Void>(HttpStatus.CONFLICT) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	/*@RequestMapping(value="/test" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Integer>> testing(@RequestBody ArrayList<Reservation> reservations) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; ++i) arr.add(i) ;		
		return new ResponseEntity<ArrayList<Integer>>(arr,HttpStatus.CONFLICT) ;
	}*/
	/*@RequestMapping(value="/book" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Integer>> doReservations(@RequestBody ArrayList<Reservation> reservations) {
		ArrayList<Integer> reservationIds = new ArrayList<Integer>(); 
		int allOk = 1; 
		for(Reservation reservation : reservations) {
			allOk &= reservationService.makeReservation(reservation) ;
		}
		if(allOk == 1) return new ResponseEntity<ArrayList<Integer>>(HttpStatus.CREATED) ;
		return new ResponseEntity<ArrayList<Integer>>(HttpStatus.CONFLICT) ;
	}*/
}
