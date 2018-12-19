package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.BookRepo;
import com.dawnchau.webclass.dao.CartRepo;
import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.pojo.CartEntity;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.service.CartService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private BookService bookService;


    /**
     * 将书籍加入购物车
     * @param cartDTO
     * @return
     */
    @Transactional
    @Override
    public ResultVO<CartDTO> addBookToCart(CartDTO cartDTO) {
        ResultVO<CartDTO> resultVO = new ResultVO<>();
        BookDTO bookDTO = null;
        CartEntity entity = Dto2EntityUtils.cartDto2Entity(cartDTO);
        try {
            bookDTO = bookService.findBookById(cartDTO.getBookid());
            entity = cartRepo.save(entity);  // 会抛出DataIntegrityViolationException
            cartDTO.setId(entity.getId());
        } catch (Exception e) {
            resultVO.setMsg(ResultMsgConstants.BOOK_NOT_EXIST);
            return resultVO;
        }
        cartDTO.setBookDetail(bookDTO);
        resultVO.setMsg(ResultMsgConstants.CART_ADD_SUCCESS);
        resultVO.setData(cartDTO);
        return resultVO;
    }
}
