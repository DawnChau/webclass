package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.vo.ResultVO;

import java.util.List;

public interface CartService {
    ResultVO<CartDTO> addBookToCart(CartDTO cartDTO);

    ResultVO<List<CartDTO>> listAllBooksInCart(Integer userId);
}
