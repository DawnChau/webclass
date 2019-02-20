package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.dao.OrderDetailRepo;
import com.dawnchau.webclass.dao.OrderRepo;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.pojo.OrderDetailEntity;
import com.dawnchau.webclass.pojo.OrderEntity;
import com.dawnchau.webclass.service.OrderService;
import com.dawnchau.webclass.vo.ResultVO;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    /**
     * 存订单和订单细节
     * @param orderDTO
     * @return
     */
    public ResultVO<OrderDTO> saveOrder(OrderDTO orderDTO){
        ResultVO<OrderDTO> res = new ResultVO<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTotalPrice(orderDTO.getTotalPrice());
        orderEntity.setUserid(orderDTO.getUserid());
        orderEntity = orderRepo.save(orderEntity);
        List<OrderDetailEntity> detailEntityList = orderDTO.getDetailEntities();
        for(int i = 0;i<detailEntityList.size();i++){
            detailEntityList.get(i).setOrderid(orderEntity.getId());
            orderDetailRepo.save(detailEntityList.get(i));
        }
        res.setData(orderDTO);
        return res;
    }

}
