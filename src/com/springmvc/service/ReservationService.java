package com.springmvc.service;

import java.util.ArrayList;

import com.springmvc.model.Reservation;
import com.springmvc.model.*;

public interface ReservationService {
	
	public int makeReservation(Reservation reservation) ;
	public int deleteReservation(Reservation reservation) ;
	public ArrayList<Reservation> getAllReservations(User user) ;
	public ArrayList<Reservation> getBookings(UpdateId updateId) ;
}
