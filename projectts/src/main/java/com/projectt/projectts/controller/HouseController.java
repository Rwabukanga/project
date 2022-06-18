package ebaza.codejava.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.EPropertyType;
import ebaza.codejava.domain.House;
import ebaza.codejava.domain.Registrar;
import ebaza.codejava.innerdomain.InnerHouse;
import ebaza.codejava.service.IGalleryService;
import ebaza.codejava.service.IHouseService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.utility.IConstants;

@RestController
@RequestMapping("/houses")
public class HouseController {

	@Autowired
	IHouseService houseservice;

	@Autowired
	IRegistarService regservice;

	@Autowired
	IGalleryService galleryservice;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<House> houses = houseservice.findAll(pageRequest);
		return new ResponseEntity<Object>(houses, HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "registrant/{uuid}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByRegistrant(@PathVariable("uuid") String uuid) {	
		return new ResponseEntity<Object>(houseservice.findByRegistrant(uuid), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getHouseById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(houseservice.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterCar(@RequestBody InnerHouse params, HttpSession session,
			@RequestPart("files") MultipartFile[] files) throws ParseException {

		// saving product
	    House h=new House();
	    h.setPrice(Double.parseDouble(params.getPrice()));
	    h.setBedrooms(Integer.parseInt(params.getBedrooms()));
	    h.setRooms(Integer.parseInt(params.getRooms()));
	    h.setType(EPropertyType.valueOf(params.getType()));
	    Registrar r=regservice.findById(params.getOwnerUuid());
	    h.setOwner(r);
	    h.setBidEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.getBidEndDate()));
	    h.setBidStartdDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.getBidStartDate()));
	    
		return new ResponseEntity<>(houseservice.createWithImages(h, files), HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteHouse(HttpServletRequest request, @PathVariable String uuid) {
		houseservice.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
