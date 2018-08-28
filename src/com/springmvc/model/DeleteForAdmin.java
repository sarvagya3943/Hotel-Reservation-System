package com.springmvc.model;


public class DeleteForAdmin{
	public DeleteForAdmin(int hotel_id) {
		super();
		this.hotel_id = hotel_id;
	}

	private int hotel_id;

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotel_id;
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
		DeleteForAdmin other = (DeleteForAdmin) obj;
		if (hotel_id != other.hotel_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeleteForAdmin [hotel_id=" + hotel_id + "]";
	}
	
}