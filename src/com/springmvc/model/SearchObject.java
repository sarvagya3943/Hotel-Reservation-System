package com.springmvc.model;

import java.util.Date;

public class SearchObject {
	 
	
	@Override
	public String toString() {
		return "SearchObject [city=" + city + ", checkInDate=" + checkIn
				+ ", checkInStr=" + checkInStr + ", checkOutDate="
				+ checkOut + ", checkOutStr=" + checkOutStr + "]";
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCheckInDate() {
		return checkIn;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkIn = checkInDate;
	}
	public String getCheckInStr() {
		return checkInStr;
	}
	public void setCheckInStr(String checkInStr) {
		this.checkInStr = checkInStr;
	}
	public Date getCheckOutDate() {
		return checkOut;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOut = checkOutDate;
	}
	public String getCheckOutStr() {
		return checkOutStr;
	}
	public void setCheckOutStr(String checkOutStr) {
		this.checkOutStr = checkOutStr;
	}
	private String city ;
	private Date checkIn ;
	private String checkInStr ;
	private Date checkOut ;
	private String checkOutStr ; 
}
