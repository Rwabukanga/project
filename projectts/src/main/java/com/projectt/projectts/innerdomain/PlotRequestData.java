package com.projectt.projectts.innerdomain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlotRequestData {
	private double width;
	private double length;
	private String plotNumber;
    private double price;
    private Date bidStartdDate;
    private Date bidEndDate;
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public String getPlotNumber() {
		return plotNumber;
	}
	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
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
