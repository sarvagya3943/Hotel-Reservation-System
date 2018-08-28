package com.springmvc.model;

public class RoomType {
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getExtraGuestPrice() {
		return extraGuestPrice;
	}
	public void setExtraGuestPrice(int extraGuestPrice) {
		this.extraGuestPrice = extraGuestPrice;
	}
	public int getMaxExtraGuests() {
		return maxExtraGuests;
	}
	public void setMaxExtraGuests(int maxExtraGuests) {
		this.maxExtraGuests = maxExtraGuests;
	}
	
	private int id ;
	private int count ;
	private int cost ;
	private int capacity ;
	private int extraGuestPrice ;
	private int maxExtraGuests ;
	private String type ;
	
}
