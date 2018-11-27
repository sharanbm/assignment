package com.tcs.evaluate.bookstore.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Author {

	@NotNull
	private String name;

	@NotNull
	private int id;
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private Date dateOfBirth;

	private String placeOfBirth;

	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private Date dateOfDeath;

	private String placeOfDeath;

	public Author(String name, int id, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath) {
		super();
		this.name = name;
		this.id = id;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.placeOfDeath = placeOfDeath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getPlaceOfDeath() {
		return placeOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

}
