package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@EqualsAndHashCode(exclude={"owners"}) @ToString(exclude= {"owners"}) // prevents Hibernate from causing infinite loop
public class Address {

	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String street; // 123 Pine St.
	private String secondary;  // Apt. 2
	
	@Length(min=2, max=2)
	private String state; // MS, LA, AL
	
	private String city;
	private String zip;
	
	// one address can correspond to many people
	@ManyToMany(mappedBy="addresses") // declare the owner of the relationship by mapping it to the property of the User class
	private @NonNull Set<User> owners;

	// constructor with no id and no owners
	public Address(String street, String secondary, @Length(min = 2, max = 2) String state, String city, String zip) {
		super();
		this.street = street;
		this.secondary = secondary;
		this.state = state;
		this.city = city;
		this.zip = zip;
	}
	
	
}
