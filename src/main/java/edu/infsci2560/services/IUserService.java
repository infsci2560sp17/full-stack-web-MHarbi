package edu.infsci2560.services;

import java.util.List;

import edu.infsci2560.models.User;
import edu.infsci2560.models.User.Role;

public interface IUserService {
    
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public void saveUser(User user, List<Role> roles);
    public String getCurrentUsername();
}