package com.springmvc.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class InitController {

	@RequestMapping(value="/" , method=RequestMethod.GET)
	public String getHome() {
		System.out.println("Inside Init Controller");
		return "template" ;
	}


}
