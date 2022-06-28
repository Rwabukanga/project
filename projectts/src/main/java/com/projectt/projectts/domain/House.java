package com.projectt.projectts.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "HOUSE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class House extends DomainDTO {
	
	
		@Column(name = "CODE", nullable = false)
		@Include
		private String code;

	    @NotEmpty(message= "{price.notNull}")
	    @Column(name = "price")
	    private double price;
	    
	    @NotEmpty(message= "{rooms.notNull}")
	    @Column(name = "rooms")
	    private int rooms;
	    
	    @NotEmpty(message= "{bedrooms.notNull }")
	    @Column(name = "bedrooms")
	    private int bedrooms;
	    
	    
	    @Enumerated(EnumType.STRING)
	    @NotEmpty(message= "{type.notNull }")
	    @Column(name = "type")
	    private EPropertyType type;
	    
	    @NotEmpty(message= "{primary_image.notNull  }")
	    @Column(name = "primary_image")
	    private String  primary_image;
	    
	    @ManyToOne
	    private Registrar owner;
	    
	    @NotEmpty(message= "{bidStartdDate.notNull}")
	    @Column(name = "bidStartdDate")
	    private Date bidStartdDate;
	    
	    @NotEmpty(message= "{bidEndDate.notNull}")
	    @Column(name = "bidEndDate")
	    private Date bidEndDate;
	    
	    @OneToOne
	    private Location location;

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getRooms() {
			return rooms;
		}

		public void setRooms(int rooms) {
			this.rooms = rooms;
		}

		public int getBedrooms() {
			return bedrooms;
		}

		public void setBedrooms(int bedrooms) {
			this.bedrooms = bedrooms;
		}

		public EPropertyType getType() {
			return type;
		}

		public void setType(EPropertyType type) {
			this.type = type;
		}

		public String getPrimary_image() {
			return primary_image;
		}

		public void setPrimary_image(String primary_image) {
			this.primary_image = primary_image;
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
