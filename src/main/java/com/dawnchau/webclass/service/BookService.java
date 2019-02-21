package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.vo.BookStatisticsVO;
import com.dawnchau.webclass.vo.ResultVO;
import java.util.List;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

public interface BookService {

    String listAllBooks();

    ResultVO<BookDTO> updateBooks(BookDTO bookDTO);

    ResultVO<BookDTO> addBook(BookDTO bookDTO);

    ResultVO<BookDTO> deleteBook(Integer id);

    BookDTO findBookById(Integer id) throws BookNotExistException;

    ResultVO<Set<BookStatisticsVO>> bookStatistics(Timestamp start, Timestamp end);
}
