package ebaza.codejava.domain;

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
	
	
    
    @NotEmpty(message= "{FirstName.notNull}")
	@Column(name = "firstName")
    private String firstName;
    
    @NotEmpty(message= "{LastName.notNull}")
	@Column(name = "lastName")
    private String lastName;
    
    @NotEmpty(message= "{PhoneNumber.notNull}")
	@Column(name = "phoneNumber")
    private String phoneNumber;
    
    @NotEmpty(message= "{Email.notNull}")
	@Column(name = "email")
    private String email;
    
    @NotEmpty(message= "{idNumber.notNull}")
   	@Column(name = "idNumber")
    private String idNumber;
    
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
