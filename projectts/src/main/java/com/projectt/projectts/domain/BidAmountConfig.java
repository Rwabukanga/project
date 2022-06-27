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

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public ConfigType getType() {
		return type;
	}

	public void setType(ConfigType type) {
		this.type = type;
	}

	 
}
