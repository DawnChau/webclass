package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/admin/book/edit")
    public ResultVO<BookDTO> updateBook(@RequestBody BookDTO bookDTO){
        return bookService.updateBooks(bookDTO);
    }
}
