package edu.infsci2560.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicController {
    
    @RequestMapping(value = {"/", "/home"})
	public ModelAndView index() {
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/about")
	public ModelAndView about() {
        return new ModelAndView("about");
    }
    
    @RequestMapping(value = "/contact-us")
	public ModelAndView contact() {
        return new ModelAndView("contact-us");
    }
}
