package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.service.CartService;
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
public class CartServiceImplTest {

    @Autowired
    private CartService cartService;

    @Test
    public void addBookToCart(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserid(1);
        cartDTO.setBookid(4);
        cartDTO.setQuantity(2);
        log.info("{}",cartService.addBookToCart(cartDTO));
    }

}