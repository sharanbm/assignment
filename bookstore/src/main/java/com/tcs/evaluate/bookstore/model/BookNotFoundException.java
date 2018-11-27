package com.tcs.evaluate.bookstore.model;

public class BookNotFoundException extends RuntimeException {

	private int bookId;

	public int getBookId() {
		return bookId;
	}

	public BookNotFoundException(int bookId) {
		super("Book# " + bookId + " Doesnt not exists");
		this.bookId = bookId;
	}

}
