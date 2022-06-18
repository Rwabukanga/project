package com.projectt.projectts.service;

import java.util.Optional;
import java.util.UUID;

import com.projectt.projectts.domain.User;
import com.projectt.projectts.innerdomain.Userlogin;



public interface IUserService {

	public void processOAuthPostLogin(String username);

	public User create(User user);

	public User findByUsernameAndPassword(String username, String password);

	public String resetPassword(User systemUser);

	public String update(User systemUser);

	public Optional<User> findById(UUID id);

	public Optional<User> findByEmail(String email);

	public void deleteRegistrar(UUID id);

	public User findByCategory(String cat);

	public void forgotpassword(User user);

	public void setNewPassword(User userr, UUID id);

	public void resetpasswordd(Userlogin user);

}
