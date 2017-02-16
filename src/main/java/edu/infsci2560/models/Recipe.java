package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 *
 * @author mharbi
 */
@Entity
public class Recipe {

    private static final long serialVersionUID = 1L;

    public enum DishType {
        None,
        Bread,
        Cake,
        Candy,
        Cookie,
        Pasta,
        Pie,
        Pizza,
        Salad,
        Sandwich,
        Sauce_Stew,
        Smoothie
    }

    public enum MealType {
        None,
        Appetizer_Snack,
        Breakfast_Brunch,
        Dessert,
        Dinner,
        Drink
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String title;
    protected DishType dishType;
    protected MealType mealType;
    protected int servings;
    @OneToOne(targetEntity=Nutrition.class, mappedBy="recipe", cascade=CascadeType.ALL)
    protected Nutrition nutrition;  // Amount per serving
    protected int prepTime;         // in minute
    protected int cookTime;          // in minute

    @ElementCollection(targetClass=String.class)
    protected List<String> directions;
    
    @OneToMany(targetEntity=Ingredient.class, mappedBy="recipe", cascade=CascadeType.ALL)
    protected List<Ingredient> ingredients;


    public Recipe() {
        this.id = Long.MAX_VALUE;
        this.title = null;
        this.dishType = DishType.None;
        this.mealType = MealType.None;
    }

    public Recipe(String title, DishType dishType, MealType mealType, int servings, int prepTime, int cookTime, List<String> directions) {
        this.title = title;
        this.dishType = dishType;
        this.mealType = mealType;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.directions = directions;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Recipe[id=%d, title='%s', dishType='%s', mealType='%s', servings='%d', nutrition='%s', prepTime='%d', cookTime='%d']%n",
                this.id, this.title, this.dishType, this.mealType, this.servings, this.nutrition.toString(), this.prepTime, this.cookTime);
        if (ingredients != null) {
            for(Ingredient ingredient : ingredients) {
                result += String.format(
                        "Ingredient[ id=%d, value='%s', unit='%s', text='%s']%n",
                        ingredient.getId(), ingredient.getValue(), ingredient.getUnit(), ingredient.getText());
            }
        }

        return result;
        // return "[ id=" + this.id + ", title=" + this.title + ", dishType=" + this.dishType + ", mealType=" + this.mealType + " ]";
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * @return the name
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the name to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the dishType
     */
    public DishType getDishType() {
        return dishType;
    }

    /**
     * @param dishType the dishType to set
     */
    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    /**
     * @return the mealType
     */
    public MealType getMealType() {
        return mealType;
    }

    /**
     * @param mealType the mealType to set
     */
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

}
