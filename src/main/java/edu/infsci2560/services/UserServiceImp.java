package edu.infsci2560.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import edu.infsci2560.models.User;
import edu.infsci2560.models.User.Role;
import edu.infsci2560.repositories.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(new ArrayList<Role>(){{
            add(Role.MEMBER);
        }});
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User saveUser(User user, List<Role> roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(roles);
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String getCurrentUsername() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); //get logged in username
	}

	@Override
	public User getCurrentUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return findUserByEmail(auth.getName());
	}

	@Override
	public Boolean isCurrentUserAdmin()	{
		User usr = userRepository.findByEmail(getCurrentUsername());
		for(Role role : usr.getRoles()) {
			if(role == Role.ADMIN) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isAuthenticated() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
    		/* The user is logged in :) */
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean isAnonymous() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth instanceof AnonymousAuthenticationToken)) {
    		/* The user is NOT logged in :) */
			return true;
		}
		else {
			return false;
		}
	}

}