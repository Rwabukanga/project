package ebaza.codejava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Gallery extends DomainDTO {
	
	 
	    
	    @NotEmpty(message= "{path.notNull}")
	    @Column(name = "path")
	    private String path;
	    
	    @NotEmpty(message= "{referenceId.notNull}")
	    @Column(name = "referenceId")
	    private String referenceId;
	    
	    @NotEmpty(message= "{referenceName.notNull}")
	    @Column(name = "referenceName")
	    private String referenceName;
	    
	    
		
	    
}
