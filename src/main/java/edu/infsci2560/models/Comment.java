package edu.infsci2560.models;

import java.util.List;
import java.time.LocalDateTime;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Recipe recipe;
    private String text;
    // private Integer rate;
    private int recommended;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;
    // private String updated;

    public Comment() {}

    public Comment(String text, int recommended) {

        this.text = text;
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return String.format("Comment[id=%d, text='%s', recommended='%s']", 
                        getId(), getText(), getRecommended());
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

    /**
     * @return the created
     */
    public LocalDateTime getCreated() {
		return created;
	}

    /**
     * @param created the created to set
     */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
