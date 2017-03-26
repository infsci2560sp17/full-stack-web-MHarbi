package edu.infsci2560.controllers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import edu.infsci2560.repositories.UserRepository;

import edu.infsci2560.models.User;
import edu.infsci2560.services.UserServiceImp;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;
	@Autowired
    private UserRepository repository;

	@RequestMapping(value = {"users", "users/index"}, method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("user/index", "users", repository.findAll());
    }

	@RequestMapping(value = "users/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id) {

        return new ModelAndView("user/view", "user", repository.findById(id));
    }

	@RequestMapping(value = "users/delete/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		repository.deleteById(id);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration() {

		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") Long id) {

		List<String> allRoles = new ArrayList<String>();
		allRoles.add("USER");
		allRoles.add("ADMIN");

        ModelAndView mav = new ModelAndView("user/update");
        User user = repository.findById(id);
		mav.addObject("allRoles", allRoles);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = {"/users/update/{id}"}, method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id, @Valid User user, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null && userExists.getId() != user.getId()) {
			bindingResult
				.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("user/update");
		} 
		else {
			userService.saveUser(user, user.getRoles());
			modelAndView.setViewName("redirect:/users");
		}
		return modelAndView;

	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){

		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}