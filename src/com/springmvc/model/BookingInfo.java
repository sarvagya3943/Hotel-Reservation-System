package com.springmvc.model;

public class BookingInfo {
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public BookingInfo() {
		super();
	}
	public BookingInfo(String type, int rooms, int guests) {
		super();
		this.type = type;
		this.rooms = rooms;
		this.guests = guests;
		this.cost = cost ;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public int getGuests() {
		return guests;
	}
	public void setGuests(int guests) {
		this.guests = guests;
	}
	private String type ;
	private int rooms ;
	private int guests ;
	private int cost ;
}
