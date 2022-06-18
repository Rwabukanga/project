package ebaza.codejava.security;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebaza.codejava.domain.User;
import ebaza.codejava.repository.IUserRepository;
import ebaza.codejava.utility.IUserMessage;
import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class CustomUserDetailsService extends AbstractService implements UserDetailsService {

	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent())
			throw new RequestException(IUserMessage.USER_NOT_FOUND);
		return UserPrincipal.create(user.get());
	}

	public UserDetails loadUserById(UUID id) {
		Optional<User> users = userRepository.findById(id);
		if (!users.isPresent())
			throw new RequestException(IUserMessage.USER_NOT_FOUND);
		return UserPrincipal.create(users.get());
	}
}