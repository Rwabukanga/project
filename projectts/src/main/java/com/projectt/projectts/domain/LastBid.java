package com.projectt.projectts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LastBid")
@Getter
@Setter
public class LastBid extends DomainDTO {
	

	@Column(name="Amount")
	private double Amount;
	
	@OneToOne
	private BiddingRequest request;
    
	@ManyToOne
    private User client;

}
