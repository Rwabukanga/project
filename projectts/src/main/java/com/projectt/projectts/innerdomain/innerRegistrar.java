package ebaza.codejava.innerdomain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ebaza.codejava.domain.ECategory;
import ebaza.codejava.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class innerRegistrar {

	
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String nid;
	@Enumerated(EnumType.STRING)
	private Gender gender;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    
}
