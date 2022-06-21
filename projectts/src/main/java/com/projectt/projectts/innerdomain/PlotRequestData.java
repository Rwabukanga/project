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
    
    
}
