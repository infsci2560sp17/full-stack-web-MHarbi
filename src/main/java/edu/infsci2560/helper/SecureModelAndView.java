package edu.infsci2560.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import edu.infsci2560.services.UserServiceImp;
import javax.annotation.PostConstruct;

public class SecureModelAndView extends ModelAndView {

    @Autowired
	private UserServiceImp userService;

    public SecureModelAndView(String viewName) {
        super(viewName);
    }

    public SecureModelAndView(String viewName, String modelName, Object modelObject) {
        
        super(viewName, modelName, modelObject);
    }

    @PostConstruct
    public void init() {
        if(userService.isAuthenticated()) {
            super.addObject("isAuthenticated", true);
        }
        if(userService.isCurrentUserAdmin()) {
            super.addObject("isAdmin", true);
        }
    }

}