package com.projectt.projectts.innerdomain;


import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseRequestData {
	private double price;
	private String bedrooms;
	private String rooms;
	private String type;
	private Date bidEndDate;
	private Date bidStartDate;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	public Date getBidStartDate() {
		return bidStartDate;
	}
	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}
	
	

	
}
