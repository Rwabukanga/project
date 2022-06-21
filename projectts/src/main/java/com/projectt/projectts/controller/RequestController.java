package com.projectt.projectts.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectt.projectts.domain.Car;
import com.projectt.projectts.domain.EPropertyType;
import com.projectt.projectts.domain.Registrar;
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.innerdomain.InnerRequestData;
import com.projectt.projectts.service.IRequestService;
import com.projectt.projectts.utility.IConstants;

@RestController
@CrossOrigin
@RequestMapping("/api/requests")
public class RequestController {
	
	@Autowired
	IRequestService requestService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Request> requests = requestService.findAll(pageRequest);
		return new ResponseEntity<Object>(requests, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRequestById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(requestService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterRequest(@RequestBody InnerRequestData params, HttpSession session) throws ParseException {

		return new ResponseEntity<>(requestService.createInitialRequest(params), HttpStatus.OK);
	}


	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCar(HttpServletRequest request, @PathVariable String uuid) {
		requestService.delete(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
