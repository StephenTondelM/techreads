package com.manifestcorp.techreads.service;

import com.manifestcorp.techreads.model.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByTitle(String bookTitle);

    Book getBookById(Long bookId);

    Book updateBookById(Book book, Long bookId);

    List<Book> updateBooksByTitle(Book book, String bookTitle);

    void deleteBookById(Long bookId);

    void deleteBookByTitle(String bookTitle);
}
