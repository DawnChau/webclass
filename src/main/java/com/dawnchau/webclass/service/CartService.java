package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.vo.ResultVO;

public interface CartService {
    ResultVO<CartDTO> addBookToCart(CartDTO cartDTO);
}
