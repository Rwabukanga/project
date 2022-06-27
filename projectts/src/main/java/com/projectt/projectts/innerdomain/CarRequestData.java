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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getNumberOfSeat() {
		return numberOfSeat;
	}
	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getBidStartdDate() {
		return bidStartdDate;
	}
	public void setBidStartdDate(Date bidStartdDate) {
		this.bidStartdDate = bidStartdDate;
	}
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	
	
	
}
