package com.tcs.evaluate.bookstore.model;

public class BookConflictException extends RuntimeException {
	private int bookId;

	public int getBookId() {
		return bookId;
	}

	public BookConflictException(int bookId) {
		super("Book# " + bookId + " Already exists, So it is a conflict");
		this.bookId = bookId;
	}

}
