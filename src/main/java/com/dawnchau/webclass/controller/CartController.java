package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.pojo.OrderDetailEntity;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.service.CartService;
import com.dawnchau.webclass.service.OrderService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.vo.ResultVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Transactional
    @GetMapping("/buybook")
    public ResultVO<OrderDTO> buyBook(@RequestParam("userId") Integer userId){
        ResultVO<OrderDTO> res = new ResultVO<>();
        List<CartDTO> list = cartService.listAllBooksInCart(userId).getData();
        // 先判断库存够不够,库存够的话，减去库存
        for(int i = 0;i<list.size();i++){
            try {
                if(list.get(i).getQuantity()> bookService.findBookById(list.get(i).getBookid()).getStock()){
                    res.setMsg(ResultMsgConstants.STOCK_DEFICIENT);
                    return res;
                }else{
                    BookDTO bookDTO = bookService.findBookById(list.get(i).getBookid());
                    bookDTO.setStock(bookDTO.getStock()-list.get(i).getQuantity());
                    bookService.updateBooks(bookDTO);
                }
            } catch (BookNotExistException e) {
                res.setMsg(ResultMsgConstants.BOOK_NOT_EXIST);
                return res;
            }
        }

        BigDecimal totalPrice = new BigDecimal(0);
        List<OrderDetailEntity> detailEntities = new ArrayList<>();
        // 计算总价
        for(int i = 0;i<list.size();i++){
            totalPrice = totalPrice.add(list.get(i).getTotalPrice());
            OrderDetailEntity detailEntity = new OrderDetailEntity();
            BookDTO bookDTO = list.get(i).getBookDetail();
            detailEntity.setBookAuthor(bookDTO.getAuthor());
            detailEntity.setBookIsbn(bookDTO.getIsbn());
            detailEntity.setBookName(bookDTO.getName());
            detailEntity.setBookPrice(bookDTO.getPrice());
            detailEntity.setBookStock(bookDTO.getStock());
            detailEntity.setQuantity(list.get(i).getQuantity());
            detailEntities.add(detailEntity);
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalPrice(totalPrice);
        orderDTO.setUserid(userId);
        orderDTO.setDetailEntities(detailEntities);

        // 存订单
        res = orderService.saveOrder(orderDTO);

        // 清空购物车
        for(int i = 0;i<list.size();i++){
            cartService.deleteByBookIdAndUserId(list.get(i).getBookid(),list.get(i).getUserid());
        }

        res.setMsg(ResultMsgConstants.BUY_BOOK_SUCCESS);
        return res;

    }
}
