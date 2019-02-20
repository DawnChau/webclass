package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.pojo.OrderEntity;
import com.dawnchau.webclass.vo.ResultVO;

public interface OrderService {
    ResultVO<OrderDTO> saveOrder(OrderDTO orderDTO);

}
