package com.manifestcorp.techreads.controller;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepository bookRepository;

    Book testBook = new Book("Test Book", "Test Author");

    @BeforeEach
    void setup() {
        bookRepository.save(testBook);
    }

    void tearDown() {
        bookRepository.deleteById(testBook.getId());
    }

    @Test
    void testRetrieveAll() throws Exception {
        mockMvc.perform(get("/api/books")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/books/" + testBook.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateBook() throws Exception {
        mockMvc.perform(post("/api/books/").contentType(
                MediaType.APPLICATION_JSON).content("{\"title\": \"A Test Book\"}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateBookById() throws Exception {
        mockMvc.perform(put("/api/books/" + testBook.getId()).contentType(
                MediaType.APPLICATION_JSON).content("{\"title\": \"A Modified Book\"}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/books/" + testBook.getId())).andDo(print()).andExpect(status().isOk());
    }
}
