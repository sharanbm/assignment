package com.tcs.evaluate.bookstore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.tcs.evaluate.bookstore.model.Author;
import com.tcs.evaluate.bookstore.model.Book;
import com.tcs.evaluate.bookstore.service.BookStore;

public class BookStoreTests {

	private final Author author = new Author("James", 11, new Date(), "London", new Date(), "");
	private final Book bookJava8 = new Book("Java 8", 41, "Pearson", "2017", "123.45", author);
	private final Book bookLearnPythonIn7Days = new Book("Learn Python in 7 days", 21, "Pearson", "2017", "123.45",
			author);
	private final Book bookLearnAWSIn6Hours = new Book("Learn AWS in 6 hours", 31, "Pearson", "2017", "123.45", author);
	private final BookStore bookStore = new BookStore();

	@Before
	public void setUp() {
		bookStore.add(bookJava8);
		bookStore.add(bookLearnPythonIn7Days);
		bookStore.add(bookLearnAWSIn6Hours);
	}

	@Test
	public void returnsNoResultsWhenNoBooksPartiallyMatchSearch() {
		BookStore bookStore = new BookStore();
		List<Book> books = bookStore.findByPartialTitleOfTheBook("abc");
		assertThat(books.size(), is(0));
		bookStore.cleanUp();
	}

	@Test
	public void findsABookWhenTitlePartiallyMatch() {
		List<Book> books = bookStore.findByPartialTitleOfTheBook("Python");
		assertThat(books.size(), is(1));
		assertThat(books, Matchers.contains(bookLearnPythonIn7Days));
		bookStore.cleanUp();
	}

	@Test
	public void findsMultipleBooksWhenTitlePartiallyMatch() {
		List<Book> books = bookStore.findByPartialTitleOfTheBook("learn");
		assertThat(books.size(), is(2));
		assertThat(books, Matchers.containsInAnyOrder(bookLearnPythonIn7Days, bookLearnAWSIn6Hours));
		bookStore.cleanUp();
	}

	@Test
	public void findsMultipleBooksWhenTitleOrAuthorOrPublisherPartiallyMatch() {
		List<Book> books = bookStore.findByTextSearch("learn");
		assertThat(books.size(), is(2));
		assertThat(books, Matchers.containsInAnyOrder(bookLearnPythonIn7Days, bookLearnAWSIn6Hours));
		bookStore.cleanUp();
	}
}
