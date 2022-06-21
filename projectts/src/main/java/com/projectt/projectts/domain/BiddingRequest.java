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
    private String referenceName;
    
    @NotEmpty(message= "{requestNumber.notNull}")
   	@Column(name = "requestNumber")
    private String requestNumber;
    
    
	@ManyToOne
    private User user;
    
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus Estatus;
	
    
	
    
    
    

}
