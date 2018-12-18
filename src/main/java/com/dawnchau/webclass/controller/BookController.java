package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/admin/book/all")
    public String listAllBooks(){
        return bookService.listAllBooks();
    }
}
