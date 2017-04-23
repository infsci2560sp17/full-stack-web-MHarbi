package edu.infsci2560;

import edu.infsci2560.models.Recipe;
import edu.infsci2560.models.Recipe.DishType;
import edu.infsci2560.models.Recipe.MealType;
import edu.infsci2560.models.Ingredient;
import edu.infsci2560.models.Ingredient.Unit;
import edu.infsci2560.models.Nutrition;
import edu.infsci2560.models.User;
import edu.infsci2560.models.User.Gender;
import edu.infsci2560.models.User.Role;
import edu.infsci2560.repositories.RecipeRepository;
import edu.infsci2560.repositories.UserRepository;

import edu.infsci2560.services.UserServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@SpringBootApplication
public class FullStackWebApplication {

    private static final Logger log = LoggerFactory.getLogger(FullStackWebApplication.class);

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(FullStackWebApplication.class, args);
        
        RecipeRepository recipeRepository = ctx.getBean(RecipeRepository.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        UserServiceImp userService = ctx.getBean(UserServiceImp.class);

        User user1 = new User("Mohammed", "Alharbi", Gender.Male, "user@eh.com", "password");
        user1 = userService.saveUser(user1, new ArrayList<Role>(){{
            add(Role.MEMBER);
        }});
        User user2 = new User("Mohammed", "Alharbi", Gender.Male, "admin@eh.com", "password");
        user2 = userService.saveUser(user2, new ArrayList<Role>(){{
            add(Role.ADMIN);
            add(Role.MEMBER);
        }});

        // ===================================================================================
        Recipe recipe1 = new Recipe( 
            "Kabob Marinade",
            "This is a tasty, easy to make marinade that is great for any grilled meat. It makes enough for about two pounds of uncooked meat. Hunters - try this on your deer.",
            DishType.Dish, 
            MealType.Dinner, 
            6, 
            15, 15,
            Arrays.asList(new String[] { 
                "In a large resealable plastic bag, combine the oil, soy sauce, lemon juice, Worcestershire sauce, mustard, ground black pepper, garlic, and meat tenderizer.", 
                "Mix well, and add your favorite meat. Seal the bag, and marinate in the refrigerator for 4 to 24 hours", 
        }));
        Nutrition nutrition = new Nutrition(115f, 3.6f, 21.1f, 1.4f, 0f, 1018f, recipe1);
        recipe1.setNutrition(nutrition);
        List<Ingredient> ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1", Unit.Cup, "vegetable oil", recipe1));
            add(new Ingredient("3/4", Unit.Cup, "soy sauce", recipe1));
            add(new Ingredient("1/2", Unit.Cup, "lemon juice", recipe1));
            add(new Ingredient("1/4", Unit.Cup, "Worcestershire sauce", recipe1));
            add(new Ingredient("1/4", Unit.Cup, "prepared mustard", recipe1));
            add(new Ingredient("1 1/2", Unit.Teaspoon, "oarsely cracked black pepper", recipe1));
            add(new Ingredient("2", Unit.None, "cloves garlic, minced", recipe1));
            add(new Ingredient("1", Unit.Teaspoon, "meat tenderizer (optional)", recipe1));
        }};
        recipe1.setIngredients(ingredients);
        // recipe1 = recipeRepository.saveAndFlush(recipe1);
        recipe1.setUser(user1);
        // user1.setRecipe(recipe1);
        // userRepository.save(user1);
        recipeRepository.save(recipe1);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe2 = new Recipe( 
            "Mediterranean Quinoa Salad",
            "Chicken breast cubes, feta cheese, and kalamata olives combine with quinoa in this salad that is equally good warm or cold.",
            DishType.Salad, 
            MealType.Appetizer_Snack, 
            8, 
            15, 20,
            Arrays.asList(new String[] { 
                "Bring the water, bouillon cubes, and garlic to a boil in a saucepan.",
                "Stir in the quinoa, reduce heat to medium-low, cover, and simmer until the quinoa is tender and the water has been absorbed, 15 to 20 minutes.",
                "Discard the garlic clove and scrape the quinoa into a large bowl.",
                "Gently stir the chicken, onion, bell pepper, olives, feta cheese, parsley, chives, and salt into the quinoa.",
                "Drizzle with the lemon juice, balsamic vinegar, and olive oil. Stir until evenly mixed. Serve warm or refrigerate and serve cold.",
        }));
        nutrition = new Nutrition(278f, 13.9f, 20.1f, 18.4f, 45f, 713f, recipe2);
        recipe2.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("2", Unit.Cup, "water", recipe2));
            add(new Ingredient("2", Unit.None, "cubes chicken bouillon", recipe2));
            add(new Ingredient("1", Unit.None, "clove garlic, smashed", recipe2));
            add(new Ingredient("1", Unit.Cup, "uncooked quinoa", recipe2));
            add(new Ingredient("2", Unit.None, "large cooked chicken breasts - cut into bite size pieces", recipe2));
            add(new Ingredient("1", Unit.None, "large red onion, diced", recipe2));
            add(new Ingredient("1", Unit.None, "large green bell pepper, diced", recipe2));
            add(new Ingredient("1/2", Unit.Cup, "chopped kalamata olives", recipe2));
            add(new Ingredient("1/2", Unit.Cup, "crumbled feta cheese", recipe2));
            add(new Ingredient("1/4", Unit.Cup, "chopped fresh parsley", recipe2));
            add(new Ingredient("1/4", Unit.Cup, "chopped fresh chives", recipe2));
            add(new Ingredient("1/2", Unit.Teaspoon, "salt", recipe2));
            add(new Ingredient("2/3", Unit.Cup, "fresh lemon juice", recipe2));
            add(new Ingredient("1", Unit.Tablespoon, "balsamic vinegar", recipe2));
            add(new Ingredient("1/4", Unit.Cup, "olive oil", recipe2));
        }};
        recipe2.setIngredients(ingredients);
        // recipe2 = recipeRepository.saveAndFlush(recipe2);
        recipe2.setUser(user1);
        // user1.setRecipe(recipe2);
        // userRepository.save(user1);
        recipeRepository.save(recipe2);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe3 = new Recipe( 
            "Strawberry Oatmeal Breakfast Smoothie",
            "This is a fast vegan smoothie with a deep pink color and a rich, creamy texture. VERY filling, and perfect for people in a rush in the morning. You don't have to give up a good breakfast when it's this fast to make! I use vitamin fortified soy milk.",
            DishType.Smoothie, 
            MealType.Breakfast_Brunch, 
            2, 
            5, 5,
            Arrays.asList(new String[] { 
                "In a blender, combine soy milk, oats, banana and strawberries. Add vanilla and sugar if desired. Blend until smooth. Pour into glasses and serve."
        }));
        nutrition = new Nutrition(236f, 3.7f, 44.9f, 7.6f, 0f, 65f, recipe3);
        recipe3.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1", Unit.Cup, "soy milk", recipe3));
            add(new Ingredient("1/2", Unit.Cup, "rolled oats", recipe3));
            add(new Ingredient("1", Unit.None, "banana, broken into chunks", recipe3));
            add(new Ingredient("14", Unit.None, "frozen strawberries", recipe3));
            add(new Ingredient("1/2", Unit.Teaspoon, "vanilla extract", recipe3));
            add(new Ingredient("1 1/2", Unit.Teaspoon, "white sugar", recipe3));
        }};
        recipe3.setIngredients(ingredients);
        // recipe3 = recipeRepository.saveAndFlush(recipe3);
        recipe3.setUser(user1);
        // user1.setRecipe(recipe3);
        // userRepository.save(user1);
        recipeRepository.save(recipe3);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe4 = new Recipe( 
            "Fruit-Flavored Water",
            "Make your own flavored water at home by infusing it with fresh lime and strawberries. There are endless cool and fruity combinations to try!",
            DishType.Juice, 
            MealType.Drink, 
            4, 
            5, 5,
            Arrays.asList(new String[] { 
                "Pour water into a pitcher. Add ice and strawberries. Squeeze lime slices to release some of their juice into the water before adding them in. Stir to combine flavors."
        }));
        nutrition = new Nutrition(6f, 0.1f, 1.4f, 0.1f, 0f, 11f, recipe4);
        recipe4.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("5", Unit.Cup, "water", recipe4));
            add(new Ingredient("1", Unit.Cup, "ice cubes (optional)", recipe4));
            add(new Ingredient("1/2", Unit.Cup, "small strawberries", recipe4));
            add(new Ingredient("1", Unit.None, "lime, sliced", recipe4));
        }};
        recipe4.setIngredients(ingredients);
        // recipe4 = recipeRepository.saveAndFlush(recipe4);
        recipe4.setUser(user1);
        // user1.setRecipe(recipe4);
        // userRepository.save(user1);
        recipeRepository.save(recipe4);                     
        // ===================================================================================

        // ===================================================================================
        Recipe recipe5 = new Recipe( 
            "Maple Salmon",
            "Easy baked salmon, thanks to a simple marinade starring maple syrup and soy sauce.",
            DishType.Dish, 
            MealType.Dinner, 
            4, 
            10, 20,
            Arrays.asList(new String[] { 
                "In a small bowl, mix the maple syrup, soy sauce, garlic, garlic salt, and pepper.",
                "Place salmon in a shallow glass baking dish, and coat with the maple syrup mixture. Cover the dish, and marinate salmon in the refrigerator 30 minutes, turning once.",
                "Preheat oven to 400 degrees F (200 degrees C).",
                "Place the baking dish in the preheated oven, and bake salmon uncovered 20 minutes, or until easily flaked with a fork." 
        }));
        nutrition = new Nutrition(265f, 12.4f, 14.1f, 23.2f, 67f, 633f, recipe5);
        recipe5.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1/4", Unit.Cup, "maple syrup", recipe5));
            add(new Ingredient("2", Unit.Tablespoon, "soy sauce", recipe5));
            add(new Ingredient("1", Unit.None, "clove garlic, minced", recipe5));
            add(new Ingredient("1/4", Unit.Teaspoon, "garlic salt", recipe5));
            add(new Ingredient("1/8", Unit.Teaspoon, "ground black pepper", recipe5));
            add(new Ingredient("1", Unit.Pound, "salmon", recipe5));
        }};
        recipe5.setIngredients(ingredients);
        // recipe5 = recipeRepository.saveAndFlush(recipe5);
        recipe5.setUser(user1);
        // user1.setRecipe(recipe5);
        // userRepository.save(user1);
        recipeRepository.save(recipe5);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe6 = new Recipe( 
            "Simple Lemon Herb Chicken",
            "Chicken seared in lemon and herbs proves that good taste does not always depend on a lot of ingredients.",
            DishType.Dish, 
            MealType.Dinner, 
            2, 
            10, 15,
            Arrays.asList(new String[] { 
                "Cut lemon in half, and squeeze juice from 1/2 lemon on chicken.",
                "Season with salt to taste. Let sit while you heat oil in a small skillet over medium low heat.",
                "When oil is hot, put chicken in skillet.",
                "As you saute chicken, add juice from other 1/2 lemon, pepper to taste, and oregano.",
                "Saute for 5 to 10 minutes each side, or until juices run clear.",
                "Serve with parsley for garnish."
        }));
        nutrition = new Nutrition(212f, 8.6f, 7.9f, 28.8f, 68f, 94f, recipe6);
        recipe6.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("2", Unit.None, "skinless, boneless chicken breast halves", recipe6));
            add(new Ingredient("1", Unit.None, "lemon", recipe6));
            add(new Ingredient("", Unit.None, "salt and pepper to taste", recipe6));
            add(new Ingredient("1", Unit.Tablespoon, "olive oil", recipe6));
            add(new Ingredient("1", Unit.None, "pinch dried oregano", recipe6));
            add(new Ingredient("2", Unit.None, "sprigs fresh parsley, for garnish", recipe6));
        }};
        recipe6.setIngredients(ingredients);
        // recipe6 = recipeRepository.saveAndFlush(recipe6);
        recipe6.setUser(user1);
        // user1.setRecipe(recipe6);
        // userRepository.save(user1);
        recipeRepository.save(recipe6);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe7 = new Recipe( 
            "Braised Balsamic Chicken",
            "Rich, slightly sweet balsamic vinegar intensifies the flavors of tomato and herbs in this chicken saute.",
            DishType.Dish, 
            MealType.Dinner, 
            6, 
            10, 25,
            Arrays.asList(new String[] { 
                "Season both sides of chicken breasts with garlic salt and pepper.",
                "Heat olive oil in a skillet over medium heat;",
                "cook seasoned chicken breasts until chicken is browned, 3 to 4 minutes per side.",
                "Add onion;",
                "cook and stir until onion is browned, 3 to 4 minutes.",
                "Pour diced tomatoes and balsamic vinegar over chicken;",
                "season with basil, oregano, rosemary and thyme.",
                "Simmer until chicken is no longer pink and the juices run clear, about 15 minutes.",
                "An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."
        }));
        nutrition = new Nutrition(196f, 7f, 7.6f, 23.8f, 61f, 511f, recipe7);
        recipe7.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("6", Unit.None, "skinless, boneless chicken breast halves", recipe7));
            add(new Ingredient("1", Unit.Teaspoon, "garlic salt", recipe7));
            add(new Ingredient("2", Unit.Tablespoon, "olive oil", recipe7));
            add(new Ingredient("1", Unit.None, "onion, thinly sliced", recipe7));
            add(new Ingredient("14.5", Unit.Ounce, "(1 can) diced tomatoes", recipe7));
            add(new Ingredient("1/2", Unit.Cup, "balsamic vinegar", recipe7));
            add(new Ingredient("1", Unit.Teaspoon, "dried basil", recipe7));
            add(new Ingredient("1", Unit.Teaspoon, "dried oregano", recipe7));
            add(new Ingredient("1", Unit.Teaspoon, "dried rosemary", recipe7));
            add(new Ingredient("1/2", Unit.Teaspoon, "dried thyme", recipe7));
        }};
        recipe7.setIngredients(ingredients);
        // recipe7 = recipeRepository.saveAndFlush(recipe7);
        recipe7.setUser(user2);
        // user2.setRecipe(recipe7);
        // userRepository.save(user2);
        recipeRepository.save(recipe7);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe8 = new Recipe( 
            "Exquisite Pizza Sauce",
            "Tomato paste, water, parmesan cheese, and garlic come together for this flavorful, homemade pizza sauce recipe.",
            DishType.Sauce_Stew, 
            MealType.None, 
            4, 
            10, 40,
            Arrays.asList(new String[] { 
                "In a small bowl, combine tomato paste, water, Parmesan cheese, garlic, honey, anchovy paste, onion powder, oregano, marjoram, basil, ground black pepper, cayenne pepper, red pepper flakes and salt;",
                "Mix together, breaking up any clumps of cheese.",
                "Sauce should sit for 30 minutes to blend flavors;",
                "Spread over pizza dough and prepare pizza as desired."
        }));
        nutrition = new Nutrition(89f, 1.3f, 18f, 3.7f, 4f, 511f, recipe8);
        recipe8.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("6", Unit.Ounce, "(1 can) tomato paste", recipe8));
            add(new Ingredient("6", Unit.None, "fluid ounces warm water (110 degrees F/45 degrees C)", recipe8));
            add(new Ingredient("3", Unit.Tablespoon, "grated Parmesan cheese", recipe8));
            add(new Ingredient("1", Unit.Teaspoon, "minced garlic", recipe8));
            add(new Ingredient("2", Unit.Tablespoon, "honey", recipe8));
            add(new Ingredient("1", Unit.Teaspoon, "anchovy paste (optional)", recipe8));
            add(new Ingredient("3/4", Unit.Teaspoon, "onion powder", recipe8));
            add(new Ingredient("1/4", Unit.Teaspoon, "dried oregano", recipe8));
            add(new Ingredient("1/4", Unit.Teaspoon, "dried marjoram", recipe8));
            add(new Ingredient("1/4", Unit.Teaspoon, "dried basil", recipe8));
            add(new Ingredient("1/4", Unit.Teaspoon, "ground black pepper", recipe8));
            add(new Ingredient("1/8", Unit.Teaspoon, "cayenne pepper", recipe8));
            add(new Ingredient("1/8", Unit.Teaspoon, "dried red pepper flakes", recipe8));
            add(new Ingredient("", Unit.None, "salt to taste", recipe8));
        }};
        recipe8.setIngredients(ingredients);
        // recipe8 = recipeRepository.saveAndFlush(recipe8);
        recipe8.setUser(user2);
        // user2.setRecipe(recipe8);
        // userRepository.save(user2);
        recipeRepository.save(recipe8);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe9 = new Recipe( 
            "Sarah's Applesauce",
            "Make your own applesauce at home with just apples, sugar, cinnamon, and this recipe.",
            DishType.Sauce_Stew, 
            MealType.None, 
            4, 
            10, 20,
            Arrays.asList(new String[] { 
                "In a saucepan, combine apples, water, sugar, and cinnamon.",
                "Cover, and cook over medium heat for 15 to 20 minutes, or until apples are soft.",
                "Allow to cool, then mash with a fork or potato masher."
        }));
        nutrition = new Nutrition(121f, 0.2f, 31.8f, 0.4f, 0f, 3f, recipe9);
        recipe9.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("4", Unit.None, "apples - peeled, cored and chopped", recipe9));
            add(new Ingredient("3/4", Unit.Cup, "water", recipe9));
            add(new Ingredient("1/4", Unit.Cup, "white sugar", recipe9));
            add(new Ingredient("1/2", Unit.Teaspoon, "ground cinnamon", recipe9));
        }};
        recipe9.setIngredients(ingredients);
        // recipe9 = recipeRepository.saveAndFlush(recipe9);
        recipe9.setUser(user2);
        // user2.setRecipe(recipe9);
        // userRepository.save(user2);
        recipeRepository.save(recipe9);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe10 = new Recipe( 
            "Lentil Soup",
            "Lentils are coupled with vegetables for this family-friendly lentil soup. Topped with spinach and a splash of vinegar, this is the perfect weekday dinner.",
            DishType.Sauce_Stew, 
            MealType.Dinner, 
            4, 
            15, 80,
            Arrays.asList(new String[] { 
                "In a large soup pot, heat oil over medium heat.",
                "Add onions, carrots, and celery; cook and stir until onion is tender.",
                "Stir in garlic, bay leaf, oregano, and basil; cook for 2 minutes.",
                "Stir in lentils, and add water and tomatoes.",
                "Bring to a boil.",
                "Reduce heat, and simmer for at least 1 hour.",
                "When ready to serve stir in spinach, and cook until it wilts.",
                "Stir in vinegar, and season to taste with salt and pepper, and more vinegar if desired."
        }));
        nutrition = new Nutrition(349f, 10f, 48.2f, 18.3f, 0f, 131f, recipe10);
        recipe10.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1", Unit.None, "onion, chopped", recipe10));
            add(new Ingredient("1/4", Unit.Cup, "olive oil", recipe10));
            add(new Ingredient("2", Unit.None, "carrots, diced", recipe10));
            add(new Ingredient("2", Unit.None, "stalks celery, chopped", recipe10));
            add(new Ingredient("2", Unit.None, "cloves garlic, minced", recipe10));
            add(new Ingredient("1", Unit.Teaspoon, "dried oregano", recipe10));
            add(new Ingredient("1", Unit.None, "bay leaf", recipe10));
            add(new Ingredient("1", Unit.Teaspoon, "dried basil", recipe10));
            add(new Ingredient("14.5", Unit.Ounce, "(1 can) crushed tomatoes", recipe10));
            add(new Ingredient("2", Unit.Cup, "dry lentils", recipe10));
            add(new Ingredient("8", Unit.Cup, "water", recipe10));
            add(new Ingredient("1/2", Unit.Cup, "spinach, rinsed and thinly sliced", recipe10));
            add(new Ingredient("2", Unit.Tablespoon, "vinegar", recipe10));
            add(new Ingredient("", Unit.None, "salt to taste", recipe10));
            add(new Ingredient("", Unit.None, "ground black pepper to taste", recipe10));
        }};
        recipe10.setIngredients(ingredients);
        // recipe10 = recipeRepository.saveAndFlush(recipe10);
        recipe10.setUser(user2);
        // user2.setRecipe(recipe10);
        // userRepository.save(user2);
        recipeRepository.save(recipe10);
        // ===================================================================================

        // ===================================================================================
        Recipe recipe11 = new Recipe( 
            "Playgroup Granola Bars",
            "This recipe is a wonderful start! Playgroup Granola Bars. Give it a try.",
            DishType.Cookie, 
            MealType.Appetizer_Snack, 
            24, 
            15, 35,
            Arrays.asList(new String[] { 
                "Preheat the oven to 350 degrees F (175 degrees C). Generously grease a 9x13 inch baking pan.",
                "In a large bowl, mix together the oats, brown sugar, wheat germ, cinnamon, flour, raisins and salt.",
                "Make a well in the center, and pour in the honey, egg, oil and vanilla.",
                "Mix well using your hands.",
                "Pat the mixture evenly into the prepared pan.",
                "Bake for 30 to 35 minutes in the preheated oven, until the bars begin to turn golden at the edges.",
                "Cool for 5 minutes, then cut into bars while still warm.",
                "Do not allow the bars to cool completely before cutting, or they will be too hard to cut."
        }));
        nutrition = new Nutrition(161f, 5.5f, 26.6f, 2.4f, 8f, 79f, recipe11);
        recipe11.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("2", Unit.Cup, "rolled oats", recipe11));
            add(new Ingredient("3/4", Unit.Cup, "packed brown sugar", recipe11));
            add(new Ingredient("1/2", Unit.Cup, "wheat germ", recipe11));
            add(new Ingredient("3/4", Unit.Teaspoon, "ground cinnamon", recipe11));
            add(new Ingredient("1", Unit.Cup, "all-purpose flour", recipe11));
            add(new Ingredient("3/4", Unit.Cup, "raisins (optional)", recipe11));
            add(new Ingredient("3/4", Unit.Teaspoon, "salt", recipe11));
            add(new Ingredient("1/2", Unit.Cup, "honey", recipe11));
            add(new Ingredient("1", Unit.None, "egg, beaten", recipe11));
            add(new Ingredient("1/2", Unit.Cup, "vegetable oil", recipe11));
            add(new Ingredient("2", Unit.Teaspoon, "vanilla extract", recipe11));
        }};
        recipe11.setIngredients(ingredients);
        // recipe11 = recipeRepository.saveAndFlush(recipe11);
        recipe11.setUser(user1);
        // user1.setRecipe(recipe11);
        // userRepository.save(user1);
        recipeRepository.save(recipe11);
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        // ===================================================================================
        /*Recipe recipe1 = new Recipe( 
            "",
            "",
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "",
                "",
                "",
                "" 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
            add(new Ingredient("", Unit.Pound, "", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        recipeRepository.save(recipe1);*/
        // ===================================================================================

        log.info("\n[Database Demo] ------------------------------------------------------------------------");
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }
// @Bean
// public CommandLineRunner databaseDemo(CustomerRepository repository) {
//     return (args) -> {
//         // save a couple of customers
//         repository.save(new Customer("Jack", "Bauer"));
//         repository.save(new Customer("Chloe", "O'Brian"));
//         repository.save(new Customer("Kim", "Bauer"));
//         repository.save(new Customer("David", "Palmer"));
//         repository.save(new Customer("Michelle", "Dessler"));
//         repository.save(new Customer("Billy", "Bean"));

//         // fetch all customers
//         log.info("[Database Demo] Customers found with findAll():");
//         log.info("[Database Demo] -------------------------------");
//         for (Customer customer : repository.findAll()) {
//             log.info("[Database Demo] " + customer.toString());
//         }
//         log.info("");

//         // fetch an individual customer by ID
//         Customer customer = repository.findOne(1L);
//         log.info("[Database Demo] Customer found with findOne(1L):");
//         log.info("[Database Demo] --------------------------------");
//         log.info("[Database Demo] " + customer.toString());            

//         // fetch customers by last name
//         log.info("[Database Demo] Customer found with findByLastName('Bauer'):");
//         log.info("[Database Demo] --------------------------------------------");
//         for (Customer bauer : repository.findByLastName("Bauer")) {
//             log.info("[Database Demo] " + bauer.toString());
//         }            
//     };
// }
}
