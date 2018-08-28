package com.springmvc.model;

public class HotelInfo {
	@Override
	public String toString() {
		return "HotelInfo [hotel=" + hotel + ", roomType=" + roomType
				+ ", availableRooms=" + availableRooms + "]";
	}
	public HotelInfo() {
		this.hotel = new Hotel() ; 
		this.roomType = new RoomType();
	}
	public int getAvailableRooms() {
		return availableRooms;
	}
	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	private Hotel hotel ;
	private RoomType roomType ;
	private int availableRooms ;
	
}
