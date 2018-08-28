package com.springmvc.model;

public class UpdateHotel{
	private String roomType;
	private int price;
	private int extraGuestPrice;
	private int maxExtraGuests;
	
	public UpdateHotel(String roomType, int price, int extraGuestPrice,
			int maxExtraGuests) {
		super();
		this.roomType = roomType;
		this.price = price;
		this.extraGuestPrice = extraGuestPrice;
		this.maxExtraGuests = maxExtraGuests;
	}
	@Override
	public String toString() {
		return "UpdateHotel [roomType=" + roomType + ", price=" + price
				+ ", extraGuestPrice=" + extraGuestPrice + ", maxExtraGuests="
				+ maxExtraGuests + "]";
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + extraGuestPrice;
		result = prime * result + maxExtraGuests;
		result = prime * result + price;
		result = prime * result
				+ ((roomType == null) ? 0 : roomType.hashCode());
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
		UpdateHotel other = (UpdateHotel) obj;
		if (extraGuestPrice != other.extraGuestPrice)
			return false;
		if (maxExtraGuests != other.maxExtraGuests)
			return false;
		if (price != other.price)
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}
}