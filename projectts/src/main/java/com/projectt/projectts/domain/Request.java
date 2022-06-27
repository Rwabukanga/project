package com.projectt.projectts.domain;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Request")
@Getter
@Setter
public class Request extends DomainDTO {
	
	
	    @OneToOne
	    @JoinColumn(name = "LOCATION_ID", nullable = false)
	    private Location location;
	    
		@ManyToOne
	    private User user;
	    
	    @Enumerated(EnumType.STRING)
	    private PropertyType propertType;
	    
	    @NotEmpty(message= "{description.notNull}")
	   	@Column(name = "description")
	    private String description;
	    
	    @NotEmpty(message= "{referenceId.notNull}")
	   	@Column(name = "referenceId")
	    private String referenceId;
	    
	    @NotEmpty(message= "{referenceName.notNull}")
	   	@Column(name = "referenceName")
	    private String referenceName;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public PropertyType getPropertType() {
			return propertType;
		}

		public void setPropertType(PropertyType propertType) {
			this.propertType = propertType;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getReferenceId() {
			return referenceId;
		}

		public void setReferenceId(String referenceId) {
			this.referenceId = referenceId;
		}

		public String getReferenceName() {
			return referenceName;
		}

		public void setReferenceName(String referenceName) {
			this.referenceName = referenceName;
		}
	    
		

}
