package ebaza.codejava.innerdomain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerHouse {

	private String price;
	private String bedrooms;
	private String rooms;
	private String type;
	private UUID ownerUuid;
	private String bidEndDate;
	private String bidStartDate;

}
