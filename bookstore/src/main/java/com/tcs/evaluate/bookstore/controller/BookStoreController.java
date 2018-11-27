package com.tcs.evaluate.bookstore.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.evaluate.bookstore.model.Book;
import com.tcs.evaluate.bookstore.service.BookStoreService;

@RestController
@EnableResourceServer
@Validated
public class BookStoreController {

	@Autowired
	BookStoreService bookStoreService;

	@GetMapping("/books")
	public List<Book> returnsListOfBooks(Principal principal) {
		return bookStoreService.returnAllTheBooksInTheStore();
	}

	@GetMapping("/books/search")
	public List<Book> search(@RequestParam(value = "q") String q) {
		return bookStoreService.findByTextSearch(q);
	}

	@GetMapping("/books/{id}")
	public Book returnsBookById(@PathVariable int id) {
		return bookStoreService.findByIDOfTheBook(id);
	}

	@PostMapping("/books")
	public void saveBooks(@Valid @RequestBody List<Book> books) {
		for (Book book : books) {
			bookStoreService.add(book);
		}
	}

	@PutMapping("/books/{id}")
	public Book saveBooks(@RequestBody Book book) {
		return bookStoreService.updateBookById(book);
	}

	@PatchMapping("/books/{id}")
	public Book partiallySaveBooks(@RequestBody Book book) {
		return bookStoreService.partiallyUpdateBookById(book);
	}

	@DeleteMapping("/books/{id}")
	public boolean deleteBook(@PathVariable int id) {
		return bookStoreService.removeBookById(id);
	}
}
