package edu.infsci2560;

import edu.infsci2560.models.Customer;
import edu.infsci2560.models.Recipe;
import edu.infsci2560.models.Recipe.DishType;
import edu.infsci2560.models.Recipe.MealType;
import edu.infsci2560.models.Ingredient;
import edu.infsci2560.models.Ingredient.Unit;
import edu.infsci2560.models.Nutrition;
import edu.infsci2560.repositories.CustomerRepository;
import edu.infsci2560.repositories.RecipeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@SpringBootApplication
public class FullStackWebApplication {

    private static final Logger log = LoggerFactory.getLogger(FullStackWebApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FullStackWebApplication.class, args);
        
        RecipeRepository repository = ctx.getBean(RecipeRepository.class);

        Recipe recipe1 = new Recipe( 
            "Maryland Crab Cakes II", 
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "Preheat oven broiler.",
                "Mix together crabmeat, bread crumbs, parsley, salt and pepper.",
                "Beat together egg, mayonnaise, hot sauce and mustard. Combine with other ingredients and mix well. Form into patties and place on a lightly greased broiler pan or baking sheet.",
                "Broil for 10 to 15 minutes, until lightly brown." 
        }));
        Nutrition nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe1);
        recipe1.setNutrition(nutrition);
        List<Ingredient> ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1", Unit.Pound, "crabmeat, shredded", recipe1));
            add(new Ingredient("1 1/2", Unit.Tablespoon, "dry bread crumbs", recipe1));
            add(new Ingredient("2", Unit.Tablespoon, "chopped fresh parsle", recipe1));
            add(new Ingredient("", Unit.None, "salt and pepper to taste", recipe1));
            add(new Ingredient("1", Unit.None, "egg", recipe1));
            add(new Ingredient("1 1/2", Unit.Pound, "mayonnaise", recipe1));
            add(new Ingredient("1/2", Unit.Pound, "ground dry mustard", recipe1));
            add(new Ingredient("1", Unit.Dash, "hot pepper sauce", recipe1));
        }};
        recipe1.setIngredients(ingredients);
                             
        repository.save(recipe1);

        Recipe recipe2 = new Recipe(
            "Maryland Crab Cakes II", 
            DishType.Cake, 
            MealType.Appetizer_Snack, 
            4, 
            15, 20,
            Arrays.asList(new String[] { 
                "Preheat oven broiler.",
                "Mix together crabmeat, bread crumbs, parsley, salt and pepper.",
                "Beat together egg, mayonnaise, hot sauce and mustard. Combine with other ingredients and mix well. Form into patties and place on a lightly greased broiler pan or baking sheet.",
                "Broil for 10 to 15 minutes, until lightly brown." 
        }));
        nutrition = new Nutrition(211f, 13f, 2.6f, 20.1f, 185f, 373f, recipe2);
        recipe2.setNutrition(nutrition);
        ingredients = new ArrayList<Ingredient>(){{
            add(new Ingredient("1", Unit.Pound, "cxxxxxxxxxxxd", recipe2));
        }};
        recipe2.setIngredients(ingredients);
                             
        repository.save(recipe2);
        log.info("[Database Demo] ------------------------------------------------------------------------");
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
