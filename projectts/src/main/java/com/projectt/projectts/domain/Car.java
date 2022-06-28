package com.projectt.projectts.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CAR")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Car extends DomainDTO {

	@Column(name = "CODE", nullable = false)
	@Include
	private String code;

	
	@NotEmpty(message = "{Name.notNull}")
	@Column(name = "NAME", nullable = false)
	@Include
	private String name;

	@NotEmpty(message = "{modelType.notNull}")
	@Column(name = "MODEL_TYPE", nullable = false)
	private String modelType;

	@NotEmpty(message = "{plateNumber.notNull}")
	@Column(name = "PLATE_NUMBER", nullable = false)
	private String plateNumber;

	@NotEmpty(message = "{numberOfSeat.notNull}")
	@Column(name = "NUMBER_OF_SEAT", nullable = false)
	private int numberOfSeat;

	@NotEmpty(message = "{color.notNull}")
	@Column(name = "COLOR", nullable = false)
	private String color;

	@NotEmpty(message = "{model.notNull}")
	@Column(name = "MODEL", nullable = false)
	private String model;

	@NotEmpty(message = "{price.notNull}")
	@Column(name = "price", nullable = false)
	private double price;

	@Enumerated(EnumType.STRING)
	@NotEmpty(message = "{type.notNull }")
	@Column(name = "type", nullable = false)
	private EPropertyType type;

	@NotEmpty(message = "{primary_image.notNull }")
	@Column(name = "primary_image")
	private String primaryImage;

	@ManyToOne
	@JoinColumn(name = "OWNER_ID", nullable = false)
	private Registrar owner;

	@NotEmpty(message = "{bidStartdDate.notNull }")
	@Column(name = "bid_start_date", nullable = false)
	private Date bidStartdDate;

	@NotEmpty(message = "{bidEndDate.notNull}")
	@Column(name = "bidEndDate", nullable = false)
	private Date bidEndDate;

	@OneToOne
	@JoinColumn(name = "LOCATION_ID", nullable = false)
	private Location location;
	
	

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

	public EPropertyType getType() {
		return type;
	}

	public void setType(EPropertyType type) {
		this.type = type;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	public Registrar getOwner() {
		return owner;
	}

	public void setOwner(Registrar owner) {
		this.owner = owner;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
