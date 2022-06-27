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
	
	@ManyToOne
	private BiddingRequest request;
    
	@ManyToOne
    private User client;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approval_status;

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public BiddingRequest getRequest() {
		return request;
	}

	public void setRequest(BiddingRequest request) {
		this.request = request;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public ApprovalStatus getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(ApprovalStatus approval_status) {
		this.approval_status = approval_status;
	}

	
    
}
