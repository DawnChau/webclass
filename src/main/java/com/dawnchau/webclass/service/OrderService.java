package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.pojo.OrderEntity;
import com.dawnchau.webclass.vo.ResultVO;

import java.util.List;

public interface OrderService {
    ResultVO<OrderDTO> saveOrder(OrderDTO orderDTO);

    ResultVO<List<OrderDTO>> listUserOrders(Integer userId);

    ResultVO<List<OrderDTO>> listAllOrders();
}
