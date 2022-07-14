package com.manifestcorp.techreads.service;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    protected BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book updateBookById(Book book, Long bookId) {
        Book foundBook = bookRepository.findById(bookId).get();

        if (book.getTitle() != null) {
            foundBook.setTitle(book.getTitle());
        }

        if (book.getCover() != null) {
            foundBook.setCover(book.getCover());
        }

        if (book.getAuthor() != null) {
            foundBook.setAuthor(book.getAuthor());
        }

        if (book.getRating() != null) {
            foundBook.setRating(book.getRating());
        }

        return bookRepository.save(foundBook);
    }

    @Override
    public List<Book> updateBooksByTitle(Book book, String bookTitle) {
        List<Book> foundBooks = getBooksByTitle(bookTitle);

        for (int i = 0; i < foundBooks.size(); i++) {
            updateBookById(book, foundBooks.get(i).getId());
        }

        return foundBooks;
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void deleteBookByTitle(String bookTitle) {
        bookRepository.deleteByTitle(bookTitle);
    }
}
