package com.projectt.projectts.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectt.projectts.domain.User;
import com.projectt.projectts.innerdomain.Userlogin;
import com.projectt.projectts.repository.IUserRepository;
import com.projectt.projectts.security.CurrentUser;
import com.projectt.projectts.security.UserPrincipal;
import com.projectt.projectts.service.IUserService;
import com.projectt.projectts.utility.ResourceNotFoundException;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService sysservice;

	@Autowired
	private IUserRepository userrepo;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userrepo.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}

	@CrossOrigin
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(HttpRequest request, @RequestBody User user) {

		return new ResponseEntity<>(sysservice.create(user), HttpStatus.OK);
		


	}

	

	@CrossOrigin
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> resetpassword(@RequestBody Userlogin user) {

		sysservice.resetpasswordd(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/forgot", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> forgotpassword(HttpServletRequest request, @RequestBody User user) {

		sysservice.forgotpassword(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}

	@CrossOrigin
	@RequestMapping(value = "/users/reset/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> setNewPassword(@RequestBody User userr, @PathVariable("id") UUID id) {

		sysservice.setNewPassword(userr, id);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/user/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findUserrr(HttpServletRequest request, @PathVariable("id") UUID id) {

		
		
		return new ResponseEntity<>(sysservice.findById(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable("id") UUID id) {
		
		sysservice.deleteRegistrar(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}




}
