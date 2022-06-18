package ebaza.codejava.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ebaza.codejava.domain.User;
import ebaza.codejava.innerdomain.Userlogin;
import ebaza.codejava.repository.IUserRepository;
import ebaza.codejava.security.CurrentUser;
import ebaza.codejava.security.UserPrincipal;
import ebaza.codejava.service.IUserService;
import ebaza.codejava.utility.IUserMessage;
import ebaza.codejava.utility.ResourceNotFoundException;
import ebaza.codejava.utility.ResponseBean;

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
