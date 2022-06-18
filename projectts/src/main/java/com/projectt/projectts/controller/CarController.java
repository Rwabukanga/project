package ebaza.codejava.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.Car;
import ebaza.codejava.domain.EPropertyType;
import ebaza.codejava.domain.Registrar;
import ebaza.codejava.service.IGalleryService;
import ebaza.codejava.service.ICarService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.utility.IConstants;
import ebaza.codejava.utility.IUserMessage;
import ebaza.codejava.utility.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")
public class CarController {

	@Autowired
	ICarService carservice;

	@Autowired
	IRegistarService regservice;

	@Autowired
	IGalleryService galleryservice;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Car> car = carservice.findAll(pageRequest);
		return new ResponseEntity<Object>(car, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCarById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(carservice.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterCar(@RequestParam Map<String, String> params, HttpSession session,
			@RequestPart("files") MultipartFile[] files, @PathVariable("id") UUID id) throws ParseException {

		// saving product
		Car c = new Car();
		c.setPrice(Double.parseDouble(params.get("price")));
		c.setName(params.get("name"));
		c.setModel(params.get("model"));
		c.setPlateNumber(params.get("plateNumber"));
		c.setNumberOfSeat(Integer.parseInt(params.get("numberOfSeat")));
		c.setColor(params.get("color"));
		c.setModel(params.get("model"));

		c.setType(EPropertyType.valueOf(params.get("type")));
		Registrar r = regservice.findById(id);
		c.setOwner(r);
		c.setBidEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.get("bid_end_date")));
		c.setBidStartdDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.get("bid_start_date")));
		return new ResponseEntity<>(carservice.createWithImages(c, files), HttpStatus.OK);
	}



	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCar(HttpServletRequest request, @PathVariable String uuid) {
		carservice.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
