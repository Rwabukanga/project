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
	
	

	
}
