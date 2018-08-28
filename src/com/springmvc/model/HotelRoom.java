package com.springmvc.model;
public class HotelRoom{
	
	private String city;
	private String roomType;
	private int price;
	private String name;
	private int capacity;
	private int extraGuestPrice;
	private int maxExtraGuests;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "HotelRoom [city=" + city + ", roomType=" + roomType
				+ ", price=" + price + ", name=" + name + ", capacity="
				+ capacity + ", extraGuestPrice=" + extraGuestPrice
				+ ", maxExtraGuests=" + maxExtraGuests + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + extraGuestPrice;
		result = prime * result + maxExtraGuests;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		HotelRoom other = (HotelRoom) obj;
		if (capacity != other.capacity)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (extraGuestPrice != other.extraGuestPrice)
			return false;
		if (maxExtraGuests != other.maxExtraGuests)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
	
	
	