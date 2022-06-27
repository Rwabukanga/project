package com.projectt.projectts.controller;

import java.text.ParseException;
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

import com.projectt.projectts.domain.BidAmountConfig;
import com.projectt.projectts.innerdomain.InnerConfigData;
import com.projectt.projectts.service.IBidConfigService;
import com.projectt.projectts.utility.IConstants;

@RestController
@CrossOrigin
@RequestMapping("/api/bid_configs")
public class BidConfigController {
	
	@Autowired
	IBidConfigService configservice;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<BidAmountConfig> requests = configservice.findAll(pageRequest);
		return new ResponseEntity<Object>(requests, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBidConfifById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(configservice.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterRequest(@RequestBody InnerConfigData params, HttpSession session) throws ParseException {

		return new ResponseEntity<>(configservice.createConfig(params), HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteBidConfiguration(HttpServletRequest request, @PathVariable String uuid) {
		configservice.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}