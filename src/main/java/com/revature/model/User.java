package com.revature.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

	// no args
	public User() {
		super();
	}
	
	
	// all args
	public User(int id, @Length(min = 2) String firstName, String lastName,
			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String username,
			@NotBlank String password, @Email String email, Set<Artwork> artworks) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.artworks = artworks;
	}

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 2)
	private String firstName;
	private String lastName;

	@NotBlank
	@Length(min = 5)
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
	@Column(unique=true)
	private String username;

	@NotBlank
	private String password;

	@Email
	private String email;

	// Declaring that User owns the relationship between User and Artwork
	// by defining the joins table on the parent class
	@ManyToMany
	@JoinTable(name="users_artwork", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns=@JoinColumn(name = "artwork_id")) 
	private Set<Artwork> artworks;

	public User(@Length(min = 2) String firstName, String lastName,
			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String username,
			@NotBlank String password, @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Artwork> getArtworks() {
		return artworks;
	}


	public void setArtworks(Set<Artwork> artworks) {
		this.artworks = artworks;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", artworks=" + artworks + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(artworks, email, firstName, id, lastName, password, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(artworks, other.artworks) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	
}
