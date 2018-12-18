package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.BookRepo;
import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.pojo.BookEntity;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.utils.Entity2DtoUtils;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    /**
     * 列举所有的书籍，只有管理员才有权限
     * @return
     */
    @Override
    public String listAllBooks() {
        List<BookEntity> list = bookRepo.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(BookEntity bookEntity:list){
            bookDTOList.add(Entity2DtoUtils.bookEntity2BookDto(bookEntity));
        }
        return ResultVO.fillResultString(ResultMsgConstants.BOOKS_FIND_SUCCESS_ADMIN,bookDTOList);
    }

    /**
     * 管理员修改某本书
     * @param bookDTO
     * @return
     */
    @Transactional
    @Override
    public ResultVO<BookDTO> updateBooks(BookDTO bookDTO) {
        BookEntity bookEntity = Dto2EntityUtils.bookDto2Entity(bookDTO);
        bookRepo.save(bookEntity);
        return new ResultVO<>(ResultMsgConstants.BOOK_UPDATE_SUCCESS_ADMIN,
                Entity2DtoUtils.bookEntity2BookDto(
                        bookRepo.findById(bookEntity.getId()).get()));
    }


}
