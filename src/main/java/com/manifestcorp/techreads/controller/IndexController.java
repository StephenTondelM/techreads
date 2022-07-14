package com.manifestcorp.techreads.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class IndexController {

    @RequestMapping("/")
    public RedirectView index() {
        return new RedirectView("/books");
    }
}
