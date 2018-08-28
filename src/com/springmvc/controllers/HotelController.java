package com.springmvc.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.model.*;
import com.springmvc.service.HotelService;
import com.springmvc.service.HotelServiceImpl;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService ;

	@RequestMapping(value="/hotels", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Hotel>> getHotels() {
		System.out.println("Inside the controller");
		ArrayList<Hotel> allHotels = hotelService.getAllHotels() ;
		return new ResponseEntity<ArrayList<Hotel>>(allHotels,HttpStatus.OK) ;
	}

	@RequestMapping(value="/search" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<HotelInfo>> getHotelsBySearch(@RequestBody SearchObject searchObj
			, HttpServletRequest req) {
		System.out.println(searchObj) ;
		ArrayList<HotelInfo> allHotels = hotelService.getAllHotelsBySearch(searchObj) ;
		req.getSession().setAttribute("search", searchObj) ;
		return new ResponseEntity<ArrayList<HotelInfo>>(allHotels,HttpStatus.OK) ; 
	}
	@RequestMapping(value="/admin" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Hotel>> getHotelsForAdmin(@RequestBody SearchForAdmin searchForAdmin) {
		System.out.println(searchForAdmin) ;
		ArrayList<Hotel> allHotels = hotelService.getHotelsForAdmin(searchForAdmin) ;
		System.out.println(allHotels);
		return new ResponseEntity<ArrayList<Hotel>>(allHotels,HttpStatus.OK) ; 
	}
	@RequestMapping(value ="/admin/deleteHotel" , method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> deleteHotels(@RequestBody Hotel hotel){
//		HttpSession session = request.getSession();
//		if(session.getAttribute("email") !=null){
			System.out.println("Inside Admin of delete Controller Admin");
			int returned = hotelService.deleteHotelById(hotel);
			HttpHeaders headers = new HttpHeaders() ;
			Hotel newHotel = hotel;
			if(returned == 1)return new ResponseEntity<Hotel>(newHotel,HttpStatus.NO_CONTENT);
			else{return null;}
	}
	@RequestMapping(value="/admin/addHotel" , method = RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelRoom> addHotels(@RequestBody HotelRoom hotelRoom){
		System.out.println("Indisde Hotel COntroller");
		if(hotelService.getHotelByNameCity(hotelRoom)==1) {
			System.out.println("A hotel with name " + hotelRoom.getName() + "and city " +hotelRoom.getCity()+ " already exist") ;
			return new ResponseEntity<HotelRoom>(hotelRoom,HttpStatus.CONFLICT) ;
		}
		int returned = hotelService.addHotels(hotelRoom);
		HttpHeaders headers = new HttpHeaders() ;
		if(returned == 1) {
			return new ResponseEntity<HotelRoom>(hotelRoom, HttpStatus.CREATED) ;
		}
		else {
			return new ResponseEntity<HotelRoom>(hotelRoom ,HttpStatus.CONFLICT) ;
		}
		
	}
	@RequestMapping(value="/update/updateHotel" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<UpdateHotel>> getHotelForUpdate(@RequestBody UpdateId updateId) {
		System.out.println(updateId.getHotelId());
		HttpHeaders headers = new HttpHeaders();
		ArrayList<UpdateHotel> newUpdate = hotelService.getHotelForUpdate(updateId);
		System.out.println("At controller" +newUpdate);
		return new ResponseEntity<ArrayList<UpdateHotel>>(newUpdate , HttpStatus.OK);
		
	}
	@RequestMapping(value="/update/changeRoom" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> changeRoom(@RequestBody Room room) {
		System.out.println(room.getHotelId());
		HttpHeaders headers = new HttpHeaders();
		Room newRoom = hotelService.changeRoom(room);
		System.out.println("At controller" +newRoom);
		return new ResponseEntity<Room>(newRoom , HttpStatus.OK);
	}
}
