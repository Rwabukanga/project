package ebaza.codejava.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ebaza.codejava.domain.EProvider;
import ebaza.codejava.domain.User;
import ebaza.codejava.innerdomain.Userlogin;
import ebaza.codejava.repository.IUserRepository;
import ebaza.codejava.service.IUserService;
import ebaza.codejava.utility.IUserMessage;
import ebaza.codejava.utility.ResponseBean;
import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.service.AbstractService;

@Service
public class UserServiceImplementation extends AbstractService implements IUserService {

	@Autowired
	private IUserRepository repo;

	@Autowired
	private IUserRepository userrepo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void processOAuthPostLogin(String username) {
		try {
			User existUser = repo.getUserByUsername(username);

			if (existUser == null) {
				User newUser = new User();
				newUser.setUsername(username);
				newUser.setProvider(EProvider.google);
				newUser.setEnabled(true);
				repo.save(newUser);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public User create(User user) {
		try {
			if (userrepo.findByEmail(user.getEmail()).isPresent()) {
				throw new RequestException(IUserMessage.EMAIL_ADDRESS_ALREADY_EXIT);
			}
			final String hashedPassword = encoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			return repo.save(user);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try {
			return repo.findByUsernameAndPassword(username, password);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public String resetPassword(User systemUser) {
		String message = "";
		try {

			final String hashedPassword = BCrypt.hashpw(systemUser.getPassword(), BCrypt.gensalt());
			systemUser.setPassword(hashedPassword);
			repo.save(systemUser);
			message = IUserMessage.reset;
		} catch (Exception e) {
			throw handleException(e);
		}
		return message;
	}

	@Override
	public String update(User systemUser) {
		String message = "";
		try {
			repo.save(systemUser);
			message = IUserMessage.update;
		} catch (Exception e) {
			throw handleException(e);
		}

		return message;

	}

	@Override
	public Optional<User> findByEmail(String email) {
		try {
			return repo.findByEmail(email);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public void deleteRegistrar(UUID id) {
		ResponseBean rb = new ResponseBean();
		try {
			Optional<User> reg = this.findById(id);
			User regg = reg.get();
			if (regg == null) {
				rb.setCode(IUserMessage.ERROR_CODE);
				rb.setDescription("failed to delete");
			} else {
				this.deleteRegistrar(id);
				rb.setCode(IUserMessage.SUCCESS_CODE);
				rb.setDescription("Successfully deleted");
				rb.setObject(regg);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public User findByCategory(String cat) {
		try {
			return repo.findByCategory(cat);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public Optional<User> findById(UUID id) {
		try {
			return repo.findById(id);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	

	@Override
	public void forgotpassword(User user) {

		ResponseBean rs = new ResponseBean();

		try {
			Optional<User> us = this.findByEmail(user.getEmail());
			User userr = us.get();

			if (us != null) {

				String lru = "http://104.248.22.79:9000/api/users/reset/" + userr.getId();
				SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
				passwordResetEmail.setFrom("sethfils2016@gmail.com");
				passwordResetEmail.setTo(user.getEmail());
				passwordResetEmail.setSubject("Password Reset Request");
				passwordResetEmail.setText("To reset your password, click the link below:\n" + lru);

				System.out.printf("successMessage", "A password reset link has been sent to " + user.getEmail());

				rs.setCode(IUserMessage.SUCCESS_CODE);
				rs.setDescription("password reset");
				rs.setObject(lru);
			} else {
				rs.setCode(IUserMessage.ERROR_CODE);
				rs.setDescription("Password not  successfully reset1");
				rs.setObject(null);
			}
		} catch (Exception ex) {
			throw handleException(ex);
		}

	}

	@Override
	public void setNewPassword(User userr, UUID id) {
		try {
			Optional<User> user = this.findById(id);
			if (user.isPresent()) {
				User resetUser = user.get();
				final String hashedPassword = encoder.encode(userr.getPassword());
				resetUser.setPassword(hashedPassword);
				this.create(resetUser);
			} else {
				throw new RequestException(IUserMessage.USER_NOT_FOUND);
			}
		} catch (Exception ex) {
			throw handleException(ex);
		}
	}

	@Override
	public void resetpasswordd(Userlogin user) {
		ResponseBean rs = new ResponseBean();
		try {
			if (user != null) {

				if (user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {
					User sys = userrepo.getUserByUsername(user.getUsername());

					final String hashedPassword = BCrypt.hashpw(sys.getPassword(), BCrypt.gensalt());
					sys.setPassword(hashedPassword);
					if (this.update(sys).equalsIgnoreCase(IUserMessage.update)) {
						rs.setCode(IUserMessage.SUCCESS_CODE);
						rs.setDescription("Password successfully changed");

						rs.setObject(sys);

					} else {
						rs.setCode(IUserMessage.ERROR_CODE);
						rs.setDescription("Password not  successfully changed1");
						rs.setObject(null);
					}

				} else {
					rs.setCode(IUserMessage.ERROR_CODE);
					rs.setDescription("Password not  successfully changed1");
					rs.setObject(null);
				}
			} else {
				rs.setCode(IUserMessage.ERROR_CODE);
				rs.setDescription("Password not successfully changed2");
				rs.setObject(null);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}
}