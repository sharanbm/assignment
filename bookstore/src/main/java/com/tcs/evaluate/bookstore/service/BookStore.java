package com.tcs.evaluate.bookstore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

import com.tcs.evaluate.bookstore.model.Author;
import com.tcs.evaluate.bookstore.model.Book;
import com.tcs.evaluate.bookstore.model.BookConflictException;
import com.tcs.evaluate.bookstore.model.BookNotFoundException;

@Repository
public class BookStore implements BookStoreService {

	private static Map<Integer, Book> bookStoreMap = new LinkedHashMap<>();
	private static Map<Integer, Author> authorMap = new LinkedHashMap<>();

	public void cleanUp() {
		bookStoreMap.clear();
		authorMap.clear();
	}

	public List<Book> findByPartialTitleOfTheBook(String partialTitle) {
		List<Book> resultList = new ArrayList<>();

		bookStoreMap.forEach((k, v) -> {
			if (v.getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
				resultList.add(v);
			}
		});

		return resultList;
	}

	public Book findByIDOfTheBook(int id) {
		if (bookStoreMap.containsKey(id)) {
			return bookStoreMap.get(id);
		} else {
			throw new BookNotFoundException(id);
		}

	}

	public List<Book> returnAllTheBooksInTheStore() {
		return bookStoreMap.values().stream().collect(Collectors.toList());
	}

	public void add(Book book) {
		if (!authorMap.containsKey(book.getAuthor().getId())) {
			authorMap.put(book.getAuthor().getId(), book.getAuthor());
		}
		if (!bookStoreMap.containsKey(book.getId())) {
			bookStoreMap.put(book.getId(), book);
		} else {
			throw new BookConflictException(book.getId());
		}
	}

	public Book updateBookById(Book book) {
		if (bookStoreMap.containsKey(book.getId())) {
			bookStoreMap.remove(book.getId());
			bookStoreMap.put(book.getId(), book);
			return bookStoreMap.get(book.getId());
		} else {
			throw new BookNotFoundException(book.getId());
		}

	}

	public Book partiallyUpdateBookById(Book bookThatWantsPartialUpdate) {
		// TODO Auto-generated method stub
		if (bookStoreMap.containsKey(bookThatWantsPartialUpdate.getId())) {
			Book bookFromTheStore = findByIDOfTheBook(bookThatWantsPartialUpdate.getId());
			BeanUtils.copyProperties(bookThatWantsPartialUpdate, bookFromTheStore,
					getNullPropertyNames(bookThatWantsPartialUpdate));
			BeanUtils.copyProperties(bookThatWantsPartialUpdate.getAuthor(), bookFromTheStore.getAuthor(),
					getNullPropertyNames(bookThatWantsPartialUpdate.getAuthor()));
			bookStoreMap.remove(bookThatWantsPartialUpdate.getId());
			bookStoreMap.put(bookFromTheStore.getId(), bookFromTheStore);
			authorMap.remove(bookThatWantsPartialUpdate.getAuthor().getId());
			authorMap.put(bookFromTheStore.getAuthor().getId(), bookFromTheStore.getAuthor());
			return bookFromTheStore;
		} else {
			throw new BookNotFoundException(bookThatWantsPartialUpdate.getId());
		}

	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null || String.valueOf(srcValue).isEmpty())
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public boolean removeBookById(int id) {

		boolean isRemovable = false;
		if (bookStoreMap.containsKey(id)) {
			isRemovable = true;
			bookStoreMap.remove(id);
		} else {
			throw new BookNotFoundException(id);
		}
		return isRemovable;
	}

	public List<Book> findByTextSearch(String q) {
		return bookStoreMap.values().stream().collect(Collectors.toList()).stream()
				.filter(book -> book.getTitle().toLowerCase().contains(q.toLowerCase())
						|| book.getPublisher().toLowerCase().contains(q.toLowerCase())
						|| book.getAuthor().getName().toLowerCase().contains(q.toLowerCase()))
				.collect(Collectors.toList());

	}

}
