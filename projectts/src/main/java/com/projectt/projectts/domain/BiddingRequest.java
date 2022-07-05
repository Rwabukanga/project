package com.projectt.projectts.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BiddingRequest  extends DomainDTO {
	
	@Column(name="UUID")
	private String uuid=UUID.randomUUID().toString();
	
	@Column(name="minAmount")
	private double minAmount;
    
    @NotEmpty(message= "{referenceId.notNull}")
   	@Column(name = "referenceId")
    private String referenceId;
    
    @NotEmpty(message= "{referenceName.notNull}")
   	@Column(name = "referenceName")
    private PropertyType  referenceName;
    
    @NotEmpty(message= "{requestNumber.notNull}")
   	@Column(name = "requestNumber")
    private String requestNumber;
    
    
	@ManyToOne
    private User user;
    
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public double getMinAmount() {
		return minAmount;
	}


	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}


	public String getReferenceId() {
		return referenceId;
	}


	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}


	


	public String getRequestNumber() {
		return requestNumber;
	}


	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	public PropertyType getReferenceName() {
		return referenceName;
	}


	public void setReferenceName(PropertyType referenceName) {
		this.referenceName = referenceName;
	}


	
    
    

}
