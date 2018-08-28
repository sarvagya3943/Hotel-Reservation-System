package com.springmvc.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.model.Hotel;
import com.springmvc.model.HotelInfo;
import com.springmvc.model.SearchObject;
import com.springmvc.model.*;
import com.springmvc.service.HotelService;
import com.springmvc.service.HotelServiceImpl;

@RestController
public class SearchController {
	
	@RequestMapping(value="/setSearch" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setSearchObject(@RequestBody SearchObject searchObj , HttpServletRequest req) {
		req.getSession().setAttribute("searchObject", searchObj) ;
		//System.out.println(req.getSession().getAttribute("searchObject")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/clearSearch" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> clearSearchObject(HttpServletRequest req) {
		//System.out.println(req.getSession().getAttribute("searchObject")) ;
		req.getSession().removeAttribute("searchObject") ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getSearch" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchObject> getSearchObject(HttpServletRequest req) {
		//System.out.println("Current Request Object is " + req.getSession().getAttribute("searchObject")) ;
		SearchObject searchObj = (SearchObject) req.getSession().getAttribute("searchObject") ;
		if(searchObj == null) return new ResponseEntity<SearchObject>(searchObj, HttpStatus.NOT_FOUND) ; 
		return new ResponseEntity<SearchObject>(searchObj , HttpStatus.OK) ;
	}
	
	
	@RequestMapping(value="/setSearchHotels" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setSearchHotels(@RequestBody ArrayList<HotelInfo> hotelInfoArr, HttpServletRequest req) {
		req.getSession().setAttribute("searchHotels", hotelInfoArr) ;
		System.out.println("Search Hotel list : " + req.getSession().getAttribute("searchHotels")) ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/clearSearchHotels" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> clearSearchHotels(HttpServletRequest req) {
		//System.out.println(req.getSession().getAttribute("searchHotels")) ;
		req.getSession().removeAttribute("searchHotels") ;
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/getSearchHotels" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<HotelInfo>> getSearchHotels(HttpServletRequest req) {
		System.out.println("Current Search hotel Object is " + req.getSession().getAttribute("searchHotels")) ;
		ArrayList<HotelInfo> searchHotels = (ArrayList<HotelInfo>) req.getSession().getAttribute("searchHotels") ;
		if(searchHotels == null) return new ResponseEntity<ArrayList<HotelInfo>>(searchHotels, HttpStatus.NOT_FOUND) ; 
		return new ResponseEntity<ArrayList<HotelInfo>>(searchHotels, HttpStatus.OK) ;
	}
}
