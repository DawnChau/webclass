package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void listAllBooks(){
        log.info("{}",bookService.listAllBooks());
    }
}