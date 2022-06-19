package com.projectt.projectts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bidding extends DomainDTO {

	@Column(name="Amount")
	private double Amount;
	
	@OneToMany
	private BiddingRequest request;
    
	@ManyToOne
    private User client;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
}
