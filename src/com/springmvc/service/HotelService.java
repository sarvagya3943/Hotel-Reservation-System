package com.springmvc.service;

import java.util.*;
import com.springmvc.model.Hotel;
import com.springmvc.model.HotelInfo;
import com.springmvc.model.SearchObject;
import com.springmvc.model.*;

public interface HotelService {
	ArrayList<Hotel> getAllHotels() ;
	ArrayList<HotelInfo> getAllHotelsBySearch(SearchObject o) ;
	ArrayList<Hotel> getHotelsForAdmin(SearchForAdmin searchForAdmin);
	int addHotels(HotelRoom hotelRoom);
	int deleteHotelById(Hotel hotel);
	public int getHotelByNameCity(HotelRoom hotelRoom);
	ArrayList<UpdateHotel> getHotelForUpdate(UpdateId updateId);
	Room changeRoom(Room room);
}
