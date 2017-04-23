package edu.infsci2560.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;
import edu.infsci2560.services.UserServiceImp;
import org.springframework.ui.Model;

//@ControllerAdvice(annotations = Controller.class)
@ControllerAdvice("edu.infsci2560.controllers")
public class CommonAdvice {

    @Autowired
	private UserServiceImp userService;

    @ModelAttribute
    public void addSecurity(Model model) {
        
        if(userService.isAuthenticated()) {
            model.addAttribute("isAuthenticated", true);
        
            if(userService.isCurrentUserAdmin()) {
                model.addAttribute("isAdmin", true);
            }
        }
        if(userService.isAnonymous()) {
            model.addAttribute("isAnonymous", true);
        }
    }
}