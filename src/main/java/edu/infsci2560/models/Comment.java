package edu.infsci2560.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
public class Comment {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Recipe recipe;
    private String comment;
    // private Integer rate;
    private int recommended;
    // private String created;
    // private String updated;

    public Comment() {}

    @Override
    public String toString() {
        return String.format("Comment[id=%d, comment='%s', recommended='%s']", 
                        getId(), getComment(), getRecommended());
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the recommended
     */
    public int getRecommended() {
		return recommended;
	}

    /**
     * @param recommended the recommended to set
     */
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
