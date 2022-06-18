package ebaza.codejava.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebaza.codejava.domain.Location;
import ebaza.codejava.service.ILocationService;
import ebaza.codejava.utility.IConstants;

@RestController
@RequestMapping("api/locations")
public class LocationController {

	@Autowired
	ILocationService locationservice;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all_(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Location> locations = locationservice.findAll(pageRequest);
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	@GetMapping(value = "parent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLocationByParentId(@PathVariable("id") UUID id,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return new ResponseEntity<>(locationservice.findByParent(id, pageRequest), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLocationById(HttpServletRequest request, @PathVariable("id") UUID id) {
		return new ResponseEntity<>(locationservice.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveLocation(@RequestBody Location location) {
		return new ResponseEntity<>(locationservice.create(location), HttpStatus.OK);
	}

	@GetMapping(value = "/findProvince", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProvinceLocation(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return new ResponseEntity<>(locationservice.findProvinceLocation(pageRequest), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteLocation(HttpServletRequest request, @PathVariable("id") UUID id) {
		locationservice.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
