package ebaza.codejava.innerdomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
	private String locationLevel;
	private String name;
	private String code;
	private String parentId;
}
