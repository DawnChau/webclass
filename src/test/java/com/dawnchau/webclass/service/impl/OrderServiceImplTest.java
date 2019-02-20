package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void saveOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserid(1);
        orderDTO.setTotalPrice(new BigDecimal(10));
        log.info("{}",orderService.saveOrder(orderDTO));
    }
    
}