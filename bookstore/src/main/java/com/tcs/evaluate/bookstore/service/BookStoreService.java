package com.tcs.evaluate.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.evaluate.bookstore.model.Book;

@Service
public interface BookStoreService {
	public List<Book> findByPartialTitleOfTheBook(String partialTitle);

	public Book findByIDOfTheBook(int id);

	public List<Book> returnAllTheBooksInTheStore();

	public void add(Book book);

	public Book updateBookById(Book book);

	public Book partiallyUpdateBookById(Book bookThatWantsPartialUpdate);

	public boolean removeBookById(int id);

	public List<Book> findByTextSearch(String q);
}
