package com.projectt.projectts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BidAmountConfig extends DomainDTO {
	
	@Column(name="Amount")
	private double Amount;
    
	 @Enumerated(EnumType.STRING)
	    private ConfigType type;


}
