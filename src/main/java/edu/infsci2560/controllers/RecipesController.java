/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infsci2560.controllers;

import edu.infsci2560.models.Recipe;
import edu.infsci2560.models.Ingredient;
import edu.infsci2560.repositories.RecipeRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kolobj
 */
@Controller
public class RecipesController {
    @Autowired
    private RecipeRepository repository;
    
    @RequestMapping(value = "recipes", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("recipes", "recipes", repository.findAll());
    }
    
    @RequestMapping(value = "recipes/add", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@ModelAttribute Recipe recipe, BindingResult result) {
        recipe.getNutrition().setRecipe(recipe);
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }
        repository.save(recipe);
        ModelAndView mav = new ModelAndView("recipes", "recipes", repository.findAll());
        // mav.addObject("msg", recipe.getIngredients().toString());
        return mav;
    }
    
}
