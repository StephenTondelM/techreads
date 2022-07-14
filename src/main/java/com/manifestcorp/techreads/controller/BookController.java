package com.manifestcorp.techreads.controller;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping({"", "/"})
    public ModelAndView books() {
        ModelAndView mav = new ModelAndView("books");
        List<Book> books = bookService.getAllBooks();
        mav.addObject("books", books);

        return mav;
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute("newBook") Book newBook, Model model) {
        return "add";
    }

    //TODO Implement input validation
    @RequestMapping(value = "/add", method = POST)
    public RedirectView addBook(Book book) {
        bookService.saveBook(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/remove")
    public String remove(@ModelAttribute("removeBook") Book book, Model model) {

        return "remove";
    }

    //TODO Correctly handle invalid remove post requests
    @RequestMapping(value = "/remove", method = POST)
    public RedirectView removeBook(Book book, Model model) {
        bookService.deleteBookByTitle(book.getTitle());

        return new RedirectView("/books");
    }

    @RequestMapping("/edit")
    public String update(@ModelAttribute("editBook") Book editBook, Model model) {
        return "edit";
    }

    //TODO Correctly handle invalid remove post requests
    @RequestMapping(value = "/edit/{title}", method = POST)
    public RedirectView updateBook(@PathVariable String title, Book editBook, Model model) {
        bookService.updateBooksByTitle(editBook, title);

        return new RedirectView("/books");
    }
}
