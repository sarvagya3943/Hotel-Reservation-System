package com.springmvc.model;
public class Room{
	private int hotelId;
	private int maxA;
	private int priceEA;
	private int price;
	private int	maxB;
	private int priceEB;
	private int priceB;
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getMaxA() {
		return maxA;
	}
	public void setMaxA(int maxA) {
		this.maxA = maxA;
	}
	public int getPriceEA() {
		return priceEA;
	}
	public void setPriceEA(int priceEA) {
		this.priceEA = priceEA;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMaxB() {
		return maxB;
	}
	public void setMaxB(int maxB) {
		this.maxB = maxB;
	}
	public int getPriceEB() {
		return priceEB;
	}
	public void setPriceEB(int priceEB) {
		this.priceEB = priceEB;
	}
	public int getPriceB() {
		return priceB;
	}
	public void setPriceB(int priceB) {
		this.priceB = priceB;
	}
	@Override
	public String toString() {
		return "Room [hotelId=" + hotelId + ", maxA=" + maxA + ", priceEA="
				+ priceEA + ", price=" + price + ", maxB=" + maxB
				+ ", priceEB=" + priceEB + ", priceB=" + priceB + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelId;
		result = prime * result + maxA;
		result = prime * result + maxB;
		result = prime * result + price;
		result = prime * result + priceB;
		result = prime * result + priceEA;
		result = prime * result + priceEB;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (hotelId != other.hotelId)
			return false;
		if (maxA != other.maxA)
			return false;
		if (maxB != other.maxB)
			return false;
		if (price != other.price)
			return false;
		if (priceB != other.priceB)
			return false;
		if (priceEA != other.priceEA)
			return false;
		if (priceEB != other.priceEB)
			return false;
		return true;
	}
	
}