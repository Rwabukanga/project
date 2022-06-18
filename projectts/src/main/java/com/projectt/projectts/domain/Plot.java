package ebaza.codejava.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ebaza.framework.persistance.domain.DomainDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Plot")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Plot extends DomainDTO {
	
//	 private static final long serialVersionUID = 1L;
//
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private long id;
//	    
    private String uuid = UUID.randomUUID().toString();
	    
	    @NotEmpty(message= "{width.notNull}")
	    @Column(name = "width", nullable = false)
	    private double width;
	    
	    @NotEmpty(message= "{length.notNull}")
	    @Column(name = "length", nullable = false)
	    private double length;
	    
	    @NotEmpty(message= "{plotNumber.notNull}")
	    @Column(name = "plotNumber", nullable = false)
	    private String plotNumber;
	    
	    @NotEmpty(message= "{price.notNull}")
	    @Column(name = "price", nullable = false)
	    private double price;
	    
	    @Enumerated(EnumType.STRING)
	    @NotEmpty(message= "{type.notNull}")
	    @Column(name = "type", nullable = false)
	    private EPropertyType type;
	    
	    @NotEmpty(message= "{primary_image.notNull}")
	    @Column(name = "primary_image")
	    private String  primary_image;
	    
	    @ManyToOne
	    @JoinColumn(name = "OWNER_ID", nullable = false)
	    private Registrar owner;
	    
	    @NotEmpty(message= "{bidStartdDate.notNull}")
	    @Column(name = "bidStartdDate", nullable = false)
	    private Date bidStartdDate;
	    
	    @NotEmpty(message= "{bidEndDate.notNull}")
	    @Column(name = "bidEndDate", nullable = false)
	    private Date bidEndDate;
	    
	    @OneToOne
	    @JoinColumn(name = "LOCATION_ID", nullable = false)
	    private Location location;
	    
	    
		
	    
	    
	    
	    

}
