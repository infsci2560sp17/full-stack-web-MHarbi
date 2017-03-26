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
import edu.infsci2560.repositories.CommentRepository;
import edu.infsci2560.repositories.RecipeRepository;

import edu.infsci2560.models.Comment;
import edu.infsci2560.models.Recipe;

@Controller
@RequestMapping("/")
public class CommentController {
	
	@Autowired
    private CommentRepository repository;
	@Autowired
    private RecipeRepository recipeRepository;

	@RequestMapping(value = "comments/delete/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		Comment com = repository.findById(id);
		Long recipe_id = com.getRecipe().getId();
		repository.deleteById(id);
		return new ModelAndView("redirect:/recipes/view/" + recipe_id);
	}

	@RequestMapping(value = "comments/add/{id}", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@PathVariable("id") Long id, @Valid Comment comment, BindingResult result) {

		Recipe recipe = recipeRepository.findById(id);
		comment.setRecipe(recipe);

        repository.save(comment);
        return new ModelAndView("redirect:/recipes/view/" + recipe.getId());
    }

}