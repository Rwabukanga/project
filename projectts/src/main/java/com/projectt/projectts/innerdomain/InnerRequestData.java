package com.projectt.projectts.innerdomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerRequestData {
	
	private String description;
	private String propertyType;
	private String locationId;
	private String clientId;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
