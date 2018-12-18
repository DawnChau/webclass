package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.vo.ResultVO;

public interface BookService {

    String listAllBooks();

    ResultVO<BookDTO> updateBooks(BookDTO bookDTO);

    ResultVO<BookDTO> addBook(BookDTO bookDTO);
}
