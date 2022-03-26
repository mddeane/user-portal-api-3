package com.revature.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "artworks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Artwork {

	
	// columns

	@Id
	@Column(name = "artwork_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private int artic_id; // data.id

	private String image_id; // data.image_id

	private String image_config; // config.iiif_url

	private String title; // data.title
	private String artist; // data.artist_title
	private String origin; // data.place_of_origin
	private String date; // data.date_display
	private String description; // data.alt_text

	// one artwork can correspond to many people
	@ManyToMany(mappedBy = "artworks") // declare the owner of the relationship by mapping it to the property of the
										// User class
	private @NonNull Set<User> owners;

	// no args constructor
	public Artwork() {
		super();
	}
	
	// all args constructor
	public Artwork(int id, int artic_id, String image_id, String image_config, String title, String artist,
			String origin, String date, String description, Set<User> owners) {
		super();
		this.id = id;
		this.artic_id = artic_id;
		this.image_id = image_id;
		this.image_config = image_config;
		this.title = title;
		this.artist = artist;
		this.origin = origin;
		this.date = date;
		this.description = description;
		this.owners = owners;
	}

	// constructor with no id and no owners
	public Artwork(int artic_id, String image_id, String image_config, String title, String artist, String origin,
			String date, String description) {
		super();
		this.artic_id = artic_id;
		this.image_id = image_id;
		this.image_config = image_config;
		this.title = title;
		this.artist = artist;
		this.origin = origin;
		this.date = date;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArtic_id() {
		return artic_id;
	}

	public void setArtic_id(int artic_id) {
		this.artic_id = artic_id;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getImage_config() {
		return image_config;
	}

	public void setImage_config(String image_config) {
		this.image_config = image_config;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getOwners() {
		return owners;
	}

	public void setOwners(Set<User> owners) {
		this.owners = owners;
	}

	@Override
	public String toString() {
		return "Artwork [id=" + id + ", artic_id=" + artic_id + ", image_id=" + image_id + ", image_config="
				+ image_config + ", title=" + title + ", artist=" + artist + ", origin=" + origin + ", date=" + date
				+ ", description=" + description + ", owners=" + owners + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(artic_id, artist, date, description, id, image_config, image_id, origin, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artwork other = (Artwork) obj;
		return artic_id == other.artic_id && Objects.equals(artist, other.artist) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(image_config, other.image_config) && Objects.equals(image_id, other.image_id)
				&& Objects.equals(origin, other.origin) && Objects.equals(title, other.title);
	}
	
}
