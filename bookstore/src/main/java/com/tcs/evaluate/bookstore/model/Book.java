package com.tcs.evaluate.bookstore.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Book {

	@NotNull
	private String title;

	@NotNull
	private int id;
	private String publisher;

	@Pattern(regexp = "^[0-9]{4}$")
	private String year;

	@Pattern(regexp = "(\\d+\\.\\d{1,2})")
	private String price;
	private Author author;

	public Book(@NotNull String title, @NotNull int id, String publisher, @Pattern(regexp = "^[0-9]{4}$") String year,
			@Pattern(regexp = "(\\d+\\.\\d{1,2})") String price, Author author) {
		super();
		this.title = title;
		this.id = id;
		this.publisher = publisher;
		this.year = year;
		this.price = price;
		this.author = author;
	}

	public Book() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", id=" + id + ", publisher=" + publisher + ", year=" + year + ", price="
				+ price + ", author=" + author + "]";
	}

}
