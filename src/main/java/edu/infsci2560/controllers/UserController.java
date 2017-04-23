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
import org.springframework.data.domain.Sort;
import javax.servlet.http.HttpServletRequest;
import edu.infsci2560.models.User;
import edu.infsci2560.services.UserServiceImp;
import edu.infsci2560.models.User.Gender;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;
	@Autowired
    private UserRepository repository;

	@RequestMapping(value = {"admin/users", "admin/users/index"}, method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("user/index", "users", repository.findAll(new Sort(Sort.Direction.ASC, "id")));
    }

	@RequestMapping(value = "admin/users/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id) {

        return new ModelAndView("user/view", "user", repository.findById(id));
    }

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

        return new ModelAndView("user/profile", "user", user);
    }

	@RequestMapping(value = "admin/users/delete/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		repository.deleteById(id);
		return new ModelAndView("redirect:/admin/users");
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
		user.setGender(Gender.Male);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("message_type", " success");
			modelAndView.addObject("message_title", "Success!\n");
			modelAndView.addObject("message", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") Long id, HttpServletRequest request) {
		
		if(userService.isCurrentUserAdmin() || userService.getCurrentUser().getId() == id) {
			List<String> allRoles = new ArrayList<String>();
			allRoles.add("MEMBER");
			allRoles.add("ADMIN");

			ModelAndView mav = new ModelAndView("user/update");
			User user = repository.findById(id);
			if (userService.isCurrentUserAdmin()) {
				mav.addObject("admin", true);
			}
			mav.addObject("allRoles", allRoles);
			mav.addObject("user", user);
			return mav;
		}
		return new ModelAndView("redirect:/");

    }

    @RequestMapping(value = {"/users/update/{id}"}, method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id, @Valid User user, BindingResult bindingResult) {

		
		if(userService.isCurrentUserAdmin() || userService.getCurrentUser().getId() == id) {
			
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
				if (userService.isCurrentUserAdmin()) {
					userService.saveUser(user, user.getRoles());
				}
				else {
					userService.saveUser(user);
				}
				modelAndView.setViewName("redirect:/admin/users");
			}
			return modelAndView;
		}
		return new ModelAndView("redirect:/");

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