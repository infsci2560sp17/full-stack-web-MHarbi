package edu.infsci2560.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Table(name = "users")
public class User {

    public enum Gender {
        Male, Female
    }

    public enum Role {
        USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String password;
    private int active;
    @ElementCollection(targetClass=Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public User() {}

    public User(String firstName, String lastName, Gender gender, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, firstName='%s', lastName='%s', gender='%s', email='%s']", 
            getId(), getFirstName(), getLastName(), getGender(), getEmail());
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password hash
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public int getActive() {
		return active;
	}

    /**
     * @param active the active to set
     */
	public void setActive(int active) {
		this.active = active;
	}

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
		return roles;
	}

    /**
     * @param roles the roles to set
     */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
