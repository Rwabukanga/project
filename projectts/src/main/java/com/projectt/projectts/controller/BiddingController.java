package ebaza.codejava.controller;



import java.util.Locale.Category;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebaza.codejava.domain.ApprovalStatus;
import ebaza.codejava.domain.BiddingRequest;
import ebaza.codejava.domain.Car;
import ebaza.codejava.domain.ECategory;
import ebaza.codejava.domain.House;
import ebaza.codejava.domain.Plot;
import ebaza.codejava.domain.User;
import ebaza.codejava.innerdomain.InnerApproval;
import ebaza.codejava.innerdomain.InnerRequest;
import ebaza.codejava.service.IBiddingService;
import ebaza.codejava.service.ICarService;
import ebaza.codejava.service.IHouseService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.service.IUserService;
import ebaza.codejava.service.PlotService;
import ebaza.codejava.utility.IConstants;
import ebaza.codejava.utility.IUserMessage;
import ebaza.codejava.utility.ResponseBean;



@RestController
@CrossOrigin
@RequestMapping("/api/requests")
public class BiddingController  {

	@Autowired
	IBiddingService biddingservice;
	
	@Autowired
	IUserService userservice;
	
	@Autowired
	PlotService plotservice;
	
	@Autowired
	ICarService carservice;
	
	@Autowired
	IHouseService houseservice;
	
	@Autowired
	IRegistarService regservice;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<BiddingRequest> requests = biddingservice.findAll(pageRequest);
	return new ResponseEntity<Object>(requests, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "registrant/{uuid}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByRegistrant(HttpServletRequest request,@PathVariable("uuid") UUID id) {
		
		
		return new ResponseEntity<Object>(biddingservice.findByUser(id), HttpStatus.OK);
 	}
	

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBiddingById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(biddingservice.findById(id), HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCar(HttpServletRequest request, @PathVariable String uuid) {
		biddingservice.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}








	
}
