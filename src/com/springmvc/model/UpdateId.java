package com.springmvc.model;

public class UpdateId{
	int hotelId;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelId;
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
		UpdateId other = (UpdateId) obj;
		if (hotelId != other.hotelId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateId [hotelId=" + hotelId + "]";
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
}