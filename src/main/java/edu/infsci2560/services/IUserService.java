package edu.infsci2560.services;

import java.util.List;

import edu.infsci2560.models.User;
import edu.infsci2560.models.User.Role;

public interface IUserService {
    
    public User findUserByEmail(String email);
    public User saveUser(User user);
    public User saveUser(User user, List<Role> roles);
    public String getCurrentUsername();
    public User getCurrentUser();
    public Boolean isCurrentUserAdmin();
    public Boolean isAuthenticated();
    public Boolean isAnonymous();
}