package com.projectt.projectts.serviceImplementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.Registrar;
import com.projectt.projectts.domain.User;
import com.projectt.projectts.repository.RegistrarRepostiory;
import com.projectt.projectts.service.IRegistarService;
import com.projectt.projectts.service.IUserService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
public class RegistrarImplementation extends AbstractService implements IRegistarService {

	@Autowired
	private RegistrarRepostiory regrepo;
	
	@Autowired
	private IUserService sysservice;

	
	
	@Override
	public Registrar findById(UUID id) {
		
		try {
			Optional<Registrar> p = regrepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (p.isPresent()) {
				return p.get();
			} else {
				throw new RequestException(IUserMessage.OBJECT_NOT_FOUND);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}


	public Registrar createregistraruser(Registrar registrar, User user) {
		
		try {
			
			sysservice.create(user);
			registrar.setUser(user);
			regrepo.save(registrar);
			return registrar;
		}catch(Exception ex) {
			throw handleException(ex);
		}
		
	}

	@Override
	public Page<Registrar> findAll(PageRequest pageRequest) {
		try {
			return regrepo.findAll(pageRequest);
		}catch(Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public void deleteRegistrar(UUID id) {
		try {
			 Optional<Registrar> p =regrepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (p.isPresent()) {
				Registrar pl = p.get();
				pl.setStatus(EStatus.INACTIVE);
				 this.createregistrar(pl);
			}
			
		}catch(Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
    try {
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sethfils2016@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		//mailSender.send(message);
    }catch(Exception e) {
    	throw handleException(e);
    }
		

	}



	@Override
	public Registrar createregistrar(Registrar registrar) {
		try {
			regrepo.save(registrar);
		}catch(Exception ex) {
			throw handleException(ex);
		}
		return registrar;
	}


	
}
