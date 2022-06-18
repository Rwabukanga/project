package ebaza.codejava.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "HOUSE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class House extends DomainDTO {
	
	
	
	    @NotEmpty(message= "{price.notNull}")
	    @Column(name = "price")
	    private double price;
	    
	    @NotEmpty(message= "{rooms.notNull}")
	    @Column(name = "rooms")
	    private int rooms;
	    
	    @NotEmpty(message= "{bedrooms.notNull }")
	    @Column(name = "bedrooms")
	    private int bedrooms;
	    
	    
	    @Enumerated(EnumType.STRING)
	    @NotEmpty(message= "{type.notNull }")
	    @Column(name = "type")
	    private EPropertyType type;
	    
	    @NotEmpty(message= "{primary_image.notNull  }")
	    @Column(name = "primary_image")
	    private String  primary_image;
	    
	    @ManyToOne
	    private Registrar owner;
	    
	    @NotEmpty(message= "{bidStartdDate.notNull}")
	    @Column(name = "bidStartdDate")
	    private Date bidStartdDate;
	    
	    @NotEmpty(message= "{bidEndDate.notNull}")
	    @Column(name = "bidEndDate")
	    private Date bidEndDate;
	    
	    @OneToOne
	    private Location location;
	    
	

}
