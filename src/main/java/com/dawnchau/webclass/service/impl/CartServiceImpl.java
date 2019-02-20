package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.CartRepo;
import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.pojo.CartEntity;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.service.CartService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.utils.Entity2DtoUtils;
import com.dawnchau.webclass.vo.ResultVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        CartEntity res = cartRepo.findByUseridAndBookid(cartDTO.getUserid(),cartDTO.getBookid());
        // 先判断该用户是否已经加入了该书籍
        if( res != null){
            cartDTO.setQuantity(cartDTO.getQuantity()+res.getQuantity());
        }

        ResultVO<CartDTO> resultVO = new ResultVO<>();
        BookDTO bookDTO = null;
        CartEntity entity = Dto2EntityUtils.cartDto2Entity(cartDTO);
        try {
            bookDTO = bookService.findBookById(cartDTO.getBookid());


            // 之前有加入的话，更新
            if(res != null ){
                entity.setId(res.getId());
                entity = cartRepo.save(entity);
            }else{
                entity = cartRepo.save(entity);  // 会抛出DataIntegrityViolationException
            }
            cartDTO.setId(entity.getId());
        } catch (Exception e) {
            resultVO.setMsg(ResultMsgConstants.BOOK_NOT_EXIST);
            return resultVO;
        }
        cartDTO.setTotalPrice(bookDTO.getPrice().multiply(new BigDecimal(cartDTO.getQuantity())));
        cartDTO.setBookDetail(bookDTO);
        resultVO.setMsg(ResultMsgConstants.CART_ADD_SUCCESS);
        resultVO.setData(cartDTO);
        return resultVO;
    }

    /**
     * 列举购物车中的所有图书
     * @param userId
     * @return
     */
    public ResultVO<List<CartDTO>> listAllBooksInCart(Integer userId){
        ResultVO<List<CartDTO>> resultVO = new ResultVO<>();
        List<CartDTO> cartDTOS = new ArrayList<>();
        List<CartEntity> cartEntities = cartRepo.findByUserid(userId);
        for(int i = 0;i<cartEntities.size();i++){
            try {
                CartDTO cartDTO = Entity2DtoUtils.cartEntity2CartDto(cartEntities.get(i));
                BookDTO bookDTO = bookService.findBookById(cartDTO.getBookid());
                cartDTO.setTotalPrice(bookDTO.getPrice().multiply(new BigDecimal(cartDTO.getQuantity())));
                cartDTO.setBookDetail(bookDTO);
                cartDTOS.add(cartDTO);
            } catch (BookNotExistException e) {
                resultVO.setMsg(ResultMsgConstants.BOOK_NOT_EXIST);
                return resultVO;
            }
        }
        resultVO.setMsg(ResultMsgConstants.LIST_ALL_BOOKS_SUCCESS);
        resultVO.setData(cartDTOS);
        return resultVO;

    }

    public void deleteByBookIdAndUserId(Integer bookId, Integer userId){
        cartRepo.deleteCartEntitiesByBookidAndUserid(bookId,userId);
    }

}
