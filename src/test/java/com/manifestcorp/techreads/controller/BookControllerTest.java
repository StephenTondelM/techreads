package com.manifestcorp.techreads.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testBookIndexPage() throws Exception {
        mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/books.jsp"));;
    }

    @Test
    void testAddIndexPage() throws Exception {
        mockMvc.perform(get("/books/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/add.jsp"));
    }

    @Test
    void testPostAddBook() throws Exception {
        mockMvc.perform(post("/books/add?title=test")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    void testRemoveIndexPage() throws Exception {
        mockMvc.perform(get("/books/remove/break")).andDo(print()).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/remove.jsp"));
    }

    @Test
    void testPostRemoveBook() throws Exception {
        mockMvc.perform(post("/books/remove?title=test")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    void testEditIndexPage() throws Exception {
        mockMvc.perform(get("/books/edit")).andDo(print()).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/edit.jsp"));
    }

    @Test
    void testPostEditBook() throws Exception {
        mockMvc.perform(post("/books/edit/test")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }
}
