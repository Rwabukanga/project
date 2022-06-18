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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebaza.codejava.domain.ECategory;
import ebaza.codejava.domain.Location;
import ebaza.codejava.domain.Registrar;
import ebaza.codejava.domain.User;
import ebaza.codejava.innerdomain.innerRegistrar;
import ebaza.codejava.service.ILocationService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.service.IUserService;
import ebaza.codejava.utility.IConstants;

@RestController
@RequestMapping("/api")
public class RegistrarController {

	@Autowired
	private IRegistarService regservice;
	
	@Autowired
	private ILocationService locationservice;

	@Autowired
	private IUserService sysservice;

	@CrossOrigin
	@RequestMapping(value = "/registrar/user/save/agentuser/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createRegistrar(HttpServletRequest request, @RequestBody innerRegistrar regg, @PathVariable("id") UUID id) {
		Registrar reg = new Registrar();
		Location location = locationservice.findById(id);
		reg.setLocation(location);
		User user = new User();

		user.setName(regg.getFirstname() + " " + regg.getLastname());
		user.setPassword(regg.getPassword());
		user.setUsername(regg.getUsername());
		user.setCategory(ECategory.AGENT_USER);
		user.setEmail(regg.getEmail());
		sysservice.create(user);
	
		reg.setFirstname(regg.getFirstname());
		reg.setLastname(regg.getLastname());
		reg.setGender(regg.getGender());
		reg.setNid(regg.getNid());
		reg.setEmail(regg.getEmail());
		reg.setPhonenumber(regg.getPhonenumber());
		reg.setUser(user);
		regservice.createregistraruser(reg,user );

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(user.getEmail());
		regservice.sendSimpleMessage(user.getEmail(), "This is your Username:" + " " + user.getUsername(),
				"Hello this is your Username  :" + " " + user.getUsername() + "   " + "this is Password:" + " "
						+ user.getPassword());

		return new ResponseEntity<>(regservice.createregistraruser(reg,user), HttpStatus.OK);


	}

	@CrossOrigin
	@RequestMapping(value = "/registrar/user/save/userproperty/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createRegistraruserproperty(HttpServletRequest request, @RequestBody Registrar reg,
			@PathVariable("id") UUID id) {

		Location location = locationservice.findById(id);
		reg.setLocation(location);
		reg.setCategory(ECategory.USER_PROPERTY);
		User user = new User();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(user.getEmail());
		regservice.sendSimpleMessage(user.getEmail(), "This is your Username:" + " " + user.getUsername(),
				"Hello this is your Username  :" + " " + user.getUsername() + "   " + "this is Password:" + " "
						+ user.getPassword());

		return new ResponseEntity<>(regservice.createregistraruser(reg,user), HttpStatus.OK);


	}

	@CrossOrigin
	@RequestMapping(value = "/registrar/user/save/client/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createRegistrarclient(HttpServletRequest request, @RequestBody Registrar reg,
			@PathVariable("id") UUID id) {

		Location location = locationservice.findById(id);
		reg.setLocation(location);
		reg.setCategory(ECategory.CLIENT);
		User user = new User();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(user.getEmail());
		regservice.sendSimpleMessage(user.getEmail(), "This is your Username:" + " " + user.getUsername(),
				"Hello this is your Username  :" + " " + user.getUsername() + "   " + "this is Password:" + " "
						+ user.getPassword());

		return new ResponseEntity<>(regservice.createregistraruser(reg,user), HttpStatus.OK);


	}

	@CrossOrigin
	@RequestMapping(value = "/registrar/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> alluser(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Registrar> car = regservice.findAll(pageRequest);
		return new ResponseEntity<Object>(car, HttpStatus.OK);
	}



	@CrossOrigin
	@RequestMapping(value = "/registrarr/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable("id") UUID id) {

		return new ResponseEntity<>(regservice.findById(id), HttpStatus.OK);
	}



	@CrossOrigin
	@RequestMapping(value = "/reg/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteuserById(HttpServletRequest request, @PathVariable("id") UUID id) {
		regservice.deleteRegistrar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
