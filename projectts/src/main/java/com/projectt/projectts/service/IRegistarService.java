package ebaza.codejava.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ebaza.codejava.domain.Registrar;
import ebaza.codejava.domain.User;



public interface IRegistarService {


	
	public Registrar createregistraruser(Registrar registrar, User user);
	public Registrar createregistrar(Registrar registrar);
	public void deleteRegistrar(UUID id);
	public void sendSimpleMessage(String to, String subject, String text);
//	public Optional<Registrar> findByEmail(String email);
	Registrar findById(UUID id);

	Page<Registrar> findAll(PageRequest pageRequest);
}
