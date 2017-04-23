package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Ingredient {

    public enum Unit {
        None,
        Pound,
        Tablespoon,
        Teaspoon,
        Ounce,
        Cup,
        Dash
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String value;
    protected Unit unit;
    protected String text;

    @ManyToOne
    protected Recipe recipe;

    public Ingredient() {

    }

    public Ingredient(String value, Unit unit, String text, Recipe recipe) {
        this.value = value;
        this.unit = unit;
        this.text = text;
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "[ id=" + this.id + ", value=" + this.value + ", unit=" + this.unit + ", text=" + this.text + " ]";
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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /*public Recipe getRecipe() {
        return recipe;
    }*/

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}