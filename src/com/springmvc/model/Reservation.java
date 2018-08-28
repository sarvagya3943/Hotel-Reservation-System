package com.springmvc.model;

public class Reservation {
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", total_price=" + total_price + ", userId="
				+ userId + ", hotelId=" + hotelId + ", roomCount=" + roomCount
				+ ", roomType=" + roomType + ", status=" + status
				+ ", paymentType=" + paymentType + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	private int id ;
	private String fromDate ;
	private String toDate ;
	private int total_price ;
	private int userId ;
	private int hotelId ;
	private int roomCount ;
	private String roomType ;
	private String status ;
	private String paymentType ;
}
