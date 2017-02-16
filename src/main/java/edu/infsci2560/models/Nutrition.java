package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author mharbi
 */
@Entity
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected float calories;    // kcal
    protected float fat;         // g
    protected float carbs;       // g
    protected float protein;     // g
    protected float cholesterol; // mg
    protected float sodium;      // mg

    @OneToOne
    protected Recipe recipe;

    public Nutrition() {

    }

    public Nutrition(float calories, float fat, float carbs, float protein, float cholesterol, float sodium, Recipe recipe) {
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "[ id=" + this.id + ", calories=" + this.calories + ", fat=" + this.fat + ", carbs=" + this.carbs + ", protein=" + this.protein + ", cholesterol=" + this.cholesterol + ", sodium=" + this.sodium + " ]";
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

    /**
     * @return the calories
     */
    public float getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(float calories) {
        this.calories = calories;
    }
    
    /**
     * @return the fat
     */
    public float getFat() {
        return fat;
    }

    /**
     * @param fat the fat to set
     */
    public void setFat(float fat) {
        this.fat = fat;
    }

    /**
     * @return the carbs
     */
    public float getCarbs() {
        return carbs;
    }

    /**
     * @param carbs the carbs to set
     */
    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    /**
     * @return the protein
     */
    public float getProtein() {
        return protein;
    }

    /**
     * @param protein the protein to set
     */
    public void setProtein(float protein) {
        this.protein = protein;
    }

    /**
     * @return the cholesterol
     */
    public float getCholesterol() {
        return cholesterol;
    }

    /**
     * @param cholesterol the cholesterol to set
     */
    public void setCholesterol(float cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * @return the sodium
     */
    public float getSodium() {
        return sodium;
    }

    /**
     * @param sodium the sodium to set
     */
    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    /*public Recipe getRecipe() {
        return recipe;
    }*/

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}