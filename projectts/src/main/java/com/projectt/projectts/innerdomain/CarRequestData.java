package com.projectt.projectts.innerdomain;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestData {
	
	private String name;
	private String modelType;
	private String plateNumber;
	private int numberOfSeat;
	private String color;
	private String model;
	private double price;
	private Date bidStartdDate;
	private Date bidEndDate;
	
	
	
}
