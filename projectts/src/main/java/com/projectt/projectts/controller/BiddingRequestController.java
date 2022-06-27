package com.projectt.projectts.controller;

import java.text.ParseException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


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
import org.springframework.web.bind.annotation.RestController;


import com.projectt.projectts.domain.BiddingRequest;
import com.projectt.projectts.service.IBiddingRequestService;
import com.projectt.projectts.service.IRegistarService;
import com.projectt.projectts.utility.IConstants;

@RestController
@CrossOrigin
@RequestMapping("/api/bid_requests")
public class BiddingRequestController {
	@Autowired
	IBiddingRequestService requestservice;
	
	
	@Autowired
	IRegistarService regservice;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<BiddingRequest> bids = requestservice.findAll(pageRequest);
	return new ResponseEntity<Object>(bids, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "user/{uuid}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByRegistrant(HttpServletRequest request,@PathVariable("uuid") UUID id) {
			
		return new ResponseEntity<Object>(requestservice.findByUser(id), HttpStatus.OK);
 	}
	

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBiddingById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(requestservice.findById(id), HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCar(HttpServletRequest request, @PathVariable String uuid) {
		requestservice.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
