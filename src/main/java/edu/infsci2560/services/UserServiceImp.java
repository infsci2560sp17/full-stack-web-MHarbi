package edu.infsci2560.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.infsci2560.models.User;
import edu.infsci2560.models.User.Role;
import edu.infsci2560.repositories.UserRepository;

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
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(new ArrayList<Role>(){{
            add(Role.USER);
        }});
		userRepository.save(user);
	}

	@Override
	public void saveUser(User user, List<Role> roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(roles);
		userRepository.save(user);
	}

}