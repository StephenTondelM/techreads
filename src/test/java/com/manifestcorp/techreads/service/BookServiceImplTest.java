package com.manifestcorp.techreads.service;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    Book testBook;

    @BeforeEach
    void setUp() throws Exception {
        bookServiceImpl = new BookServiceImpl(bookRepository);

        testBook = new Book("Test Title", "Test Author", new URL("https:test.com"), 1.0f);
    }

    @AfterEach
    void tearDown() {
        bookServiceImpl = null;
        testBook = null;
    }

    @Test
    void testSaveBook() {
        when(bookRepository.save(testBook)).thenReturn(testBook);

        Book book = bookServiceImpl.saveBook(testBook);

        assertThat(book).returns("Test Title", from(Book::getTitle))
                        .returns("Test Author", from(Book::getAuthor));
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<Book>(Arrays.asList(testBook, new Book("Other", "Other"))));

        List<Book> books = bookServiceImpl.getAllBooks();

        Book book1 = books.get(0);

        assertThat(book1).returns("Test Title", from(Book::getTitle))
                         .returns("Test Author", from(Book::getAuthor));
        assertEquals(2, books.size());
    }

    @Test
    void testGetBooksByTitle() {
        when(bookRepository.findByTitle("Test Title")).thenReturn(new ArrayList<Book>(Arrays.asList(testBook)));

        List<Book> books = bookServiceImpl.getBooksByTitle("Test Title");

        Book book1 = books.get(0);

        assertThat(book1).returns("Test Title", from(Book::getTitle))
                         .returns("Test Author", from(Book::getAuthor));
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        Book book = bookServiceImpl.getBookById(1L);

        assertThat(book).returns("Test Title", from(Book::getTitle))
                        .returns("Test Author", from(Book::getAuthor));
    }

    @Test
    void testUpdateBookByIdAndChangeAllFields() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).then(returnsFirstArg());

        Book book = bookServiceImpl.updateBookById(new Book("New Title", "New Author", new URL("https:test2.com"), 2.0f),1L);

        assertThat(book).returns("New Title", from(Book::getTitle))
                        .returns("New Author", from(Book::getAuthor))
                        .returns(new URL("https:test2.com"), from(Book::getCover))
                        .returns(2.0f, from(Book::getRating));
    }

    @Test
    void testUpdateBookByIdAndPassInEmptyBook() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).then(returnsFirstArg());

        Book book = bookServiceImpl.updateBookById(new Book(),1L);

        assertThat(book).returns("Test Title", from(Book::getTitle))
                        .returns("Test Author", from(Book::getAuthor))
                        .returns(new URL("https:test.com"), from(Book::getCover))
                        .returns(1.0f, from(Book::getRating));
    }

    @Test
    void testUpdateBooksByTitle() throws Exception {
        Book testBook2 = new Book("Test Title", "Another Author");

        testBook.setId(1L);
        testBook2.setId(2L);

        when(bookRepository.findByTitle("Test Title")).thenReturn(new ArrayList<Book>(Arrays.asList(testBook, testBook2)));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(testBook2));
        when(bookRepository.save(any(Book.class))).then(returnsFirstArg());

        List<Book> books = bookServiceImpl.updateBooksByTitle(new Book("New Title", "New Author", new URL("https:test2.com"), 2.0f),"Test Title");

        assertThat(books.get(0)).returns("New Title", from(Book::getTitle))
                .returns("New Author", from(Book::getAuthor))
                .returns(new URL("https:test2.com"), from(Book::getCover))
                .returns(2.0f, from(Book::getRating));

        assertEquals(2, books.size());
    }

    @Test
    void testDeleteBookById() {
        doNothing().when(bookRepository).deleteById(1L);

        bookServiceImpl.deleteBookById(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBookByTitle() {
        doNothing().when(bookRepository).deleteByTitle("Test");

        bookServiceImpl.deleteBookByTitle("Test");

        verify(bookRepository, times(1)).deleteByTitle("Test");
    }
}