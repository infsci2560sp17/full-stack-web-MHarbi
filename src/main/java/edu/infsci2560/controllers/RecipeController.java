package edu.infsci2560.controllers;

import edu.infsci2560.models.Recipe;
import edu.infsci2560.models.Ingredient;
import edu.infsci2560.models.Nutrition;
import edu.infsci2560.models.Comment;
import edu.infsci2560.models.User;
import edu.infsci2560.repositories.RecipeRepository;
import edu.infsci2560.repositories.IngredientRepository;
import edu.infsci2560.repositories.NutritionRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.ArrayList;

import edu.infsci2560.storage.StorageService;
import edu.infsci2560.services.UserServiceImp;

@Controller
public class RecipeController {
    
    private final StorageService storageService;

    @Autowired
    public RecipeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private RecipeRepository repository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private NutritionRepository nutritionRepository;
    @Autowired
	private UserServiceImp userService;
    
    @RequestMapping(value = {"recipes", "recipes/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        
        if(userService.isCurrentUserAdmin()) {
            model.addAttribute("recipes", repository.findAll(new Sort(Sort.Direction.ASC, "id")));
        }
        else {
            List<Recipe> recipes = repository.findByUserId(userService.getCurrentUser().getId());
            model.addAttribute("recipes", recipes);
        }
        
        return new ModelAndView("recipe/index");
    }

    @RequestMapping(value = "/public/recipes/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView("recipe/view");
        Recipe recipe = repository.findById(id);
        mav.addObject("recipe", recipe);
        mav.addObject("images", storageService.lookup(id.toString()));
        Comment comment = new Comment();
        mav.addObject("comment", comment);

        return mav;
    }

    @RequestMapping(value = "recipes/delete/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
		String message = "", message_type = "", message_title = "";

        if(userService.isCurrentUserAdmin() || 
            repository.findByIdAndUserId(id, userService.getCurrentUser().getId()) != null) {
            try {
                repository.deleteById(id);
                message = "Your recipe has been deleted successfully!";
                message_type = " success";
                message_title = "Success!\n";
            }
            catch (Exception e) {
                message = "Failed to save the recipe!\n" + message;
                message_type = "";
                message_title = "Error!\n";
            }
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("message_type", message_type);
            redirectAttributes.addFlashAttribute("message_title", message_title);

            return new ModelAndView("redirect:/recipes");
        }
        return new ModelAndView("redirect:/");
	}

    @RequestMapping(value = "recipes/add", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView mav = new ModelAndView("recipe/create");
        return mav;
    }
    
    @RequestMapping(value = "recipes/add", method = RequestMethod.POST, consumes="multipart/form-data", produces = "application/json")
    public String create(@RequestParam("images") MultipartFile[] images, 
                                @ModelAttribute @Valid Recipe recipe, BindingResult result,
                                RedirectAttributes redirectAttributes) {

        recipe.getNutrition().setRecipe(recipe);
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe); 
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
        recipe.setUser(user);

        Recipe storedRecipe = repository.saveAndFlush(recipe);

        String message = storageService.store(images, storedRecipe.getId().toString());
        String message_type = "";
        String message_title = "";
        if(message == "") {
            message = "Your recipe has been added successfully!";
            message_type = " success";
            message_title = "Success!\n";
        }
        else {
            repository.deleteById(storedRecipe.getId());
            message = "Failed to save the recipe!\n" + message;
            message_type = "";
            message_title = "Error!\n";
        }
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("message_type", message_type);
        redirectAttributes.addFlashAttribute("message_title", message_title);

        return "redirect:/recipes";
    }
    
    @RequestMapping(value = "/recipes/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView("recipe/update");
        Recipe recipe = repository.findById(id);
        mav.addObject("recipe", recipe);
        mav.addObject("images", storageService.lookup(id.toString()));
        return mav;
    }

    @RequestMapping(value = {"/recipes/update/{id}"}, method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id, 
                                @RequestParam("images") MultipartFile[] images, 
                                @Valid Recipe recipe, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, String[] cimages) {

        String message = "", message_type = "", message_title = "";
		ModelAndView modelAndView = new ModelAndView();

        if(userService.isCurrentUserAdmin() || 
            repository.findByIdAndUserId(id, userService.getCurrentUser().getId()) != null) {
		
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("recipe/update");
            } 
            else {
                ingredientRepository.deleteByRecipeId(recipe.getId());

                Nutrition nutrition = nutritionRepository.findByRecipeId(recipe.getId());
                    nutrition.setCalories(recipe.getNutrition().getCalories());
                    nutrition.setFat(recipe.getNutrition().getFat());
                    nutrition.setCarbs(recipe.getNutrition().getCarbs());
                    nutrition.setProtein(recipe.getNutrition().getProtein());
                    nutrition.setCholesterol(recipe.getNutrition().getCholesterol());
                    nutrition.setSodium(recipe.getNutrition().getSodium());
                    nutrition.setCalories(recipe.getNutrition().getCalories());
                nutritionRepository.save(nutrition);
                
                for (Ingredient ingredient : recipe.getIngredients()) {
                    ingredient.setRecipe(recipe); 
                }
                recipe.setNutrition(null);
                recipe.setUser(repository.findById(recipe.getId()).getUser());
                repository.save(recipe);
                 ArrayList<String> dfilenames = new ArrayList<>();
                // find images to delete
                for(String filename : storageService.lookup(id.toString())) {
                    Boolean flag = true;
                    for(String img : cimages) {
                        if(filename.equals(img)) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        dfilenames.add(filename);
                    }
                }
                // delete images
                for(String filename : dfilenames) {
                    storageService.delete(filename);
                }
                // save new images
                message = storageService.store(images, recipe.getId().toString());
                if(message == "") {
                    message = "Your recipe has been added successfully!";
                    message_type = " success";
                    message_title = "Success!\n";
                }
                else {
                    message = "Failed to save the recipe!\n" + message;
                    message_type = "";
                    message_title = "Error!\n";
                }

                redirectAttributes.addFlashAttribute("message", message);
                redirectAttributes.addFlashAttribute("message_type", message_type);
                redirectAttributes.addFlashAttribute("message_title", message_title);

                modelAndView.setViewName("redirect:/recipes");
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/");
		return modelAndView;

	}
}
