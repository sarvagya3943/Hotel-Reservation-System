package com.springmvc.model;

public class SearchForAdmin{
	String name;
	String city;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "SearchForAdmin [name=" + name + ", city=" + city + "]";
	}
}