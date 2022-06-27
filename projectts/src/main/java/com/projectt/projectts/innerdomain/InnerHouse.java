package com.projectt.projectts.innerdomain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerHouse {

	private String price;
	private String bedrooms;
	private String rooms;
	private String type;
	private UUID ownerUuid;
	private String bidEndDate;
	private String bidStartDate;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
	public UUID getOwnerUuid() {
		return ownerUuid;
	}
	public void setOwnerUuid(UUID ownerUuid) {
		this.ownerUuid = ownerUuid;
	}
	public String getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	public String getBidStartDate() {
		return bidStartDate;
	}
	public void setBidStartDate(String bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	
	
}
