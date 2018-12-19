package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.vo.ResultVO;

import java.util.Optional;

public interface BookService {

    String listAllBooks();

    ResultVO<BookDTO> updateBooks(BookDTO bookDTO);

    ResultVO<BookDTO> addBook(BookDTO bookDTO);

    ResultVO<BookDTO> deleteBook(Integer id);

    BookDTO findBookById(Integer id) throws BookNotExistException;
}
