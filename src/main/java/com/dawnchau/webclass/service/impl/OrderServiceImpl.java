package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.OrderDetailRepo;
import com.dawnchau.webclass.dao.OrderRepo;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.pojo.OrderDetailEntity;
import com.dawnchau.webclass.pojo.OrderEntity;
import com.dawnchau.webclass.service.OrderService;
import com.dawnchau.webclass.vo.ResultVO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        orderEntity.setCreateTime(new Timestamp(new Date().getTime()));
        orderEntity = orderRepo.save(orderEntity);
        orderDTO.setCreateTime(orderEntity.getCreateTime().toString());
        List<OrderDetailEntity> detailEntityList = orderDTO.getDetailEntities();
        for(int i = 0;i<detailEntityList.size();i++){
            detailEntityList.get(i).setOrderid(orderEntity.getId());
            orderDetailRepo.save(detailEntityList.get(i));
        }
        res.setData(orderDTO);
        return res;
    }

    /**
     * 查询用户的所有订单
     * @param userId
     * @return
     */
    public ResultVO<List<OrderDTO>> listUserOrders(Integer userId){
        List<OrderEntity> orderEntities = orderRepo.findByUserid(userId);
        List<OrderDTO> orderDTOS = assmbleOrderDTOList(orderEntities);
        ResultVO<List<OrderDTO>> res = new ResultVO<>();
        res.setData(orderDTOS);
        res.setMsg(ResultMsgConstants.LIST_USER_ORDERS_SUCCESS);
        return res;
    }


    /**
     * 管理员列举所有的订单
     * @return
     */
    public ResultVO<List<OrderDTO>> listAllOrders(){
        List<OrderEntity> orderEntities = orderRepo.findAll();
        List<OrderDTO> orderDTOS = assmbleOrderDTOList(orderEntities);
        ResultVO<List<OrderDTO>> res = new ResultVO<>();
        res.setData(orderDTOS);
        res.setMsg(ResultMsgConstants.LIST_ORDERS_SUCCESS);
        return res;
    }

    /**
     * 组装 OrderDTO 的集合
     * @param orderEntities
     * @return
     */
    private List<OrderDTO> assmbleOrderDTOList(List<OrderEntity> orderEntities) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(int i = 0;i<orderEntities.size();i++){
            OrderDTO orderDTO = new OrderDTO();
            OrderEntity orderEntity = orderEntities.get(i);
            orderDTO.setTotalPrice(orderEntity.getTotalPrice());
            orderDTO.setUserid(orderEntity.getUserid());
            List<OrderDetailEntity> detailEntities = orderDetailRepo.findByOrderid(orderEntity.getId());
            orderDTO.setDetailEntities(detailEntities);
            orderDTO.setCreateTime(orderEntity.getCreateTime().toString());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    /**
     * 查询固定时间段内的订单
     * @param start
     * @param end
     * @return
     */
    public ResultVO<List<OrderDTO>> findOrderBetween(Timestamp start, Timestamp end){
        List<OrderEntity> orderEntities = orderRepo.findByCreateTimeBetween(start,end);
        List<OrderDTO> orderDTOS = assmbleOrderDTOList(orderEntities);
        ResultVO<List<OrderDTO>> res = new ResultVO<>();
        res.setData(orderDTOS);
        res.setMsg(ResultMsgConstants.LIST_ORDERS_BETWEEN_SUCCESS);
        return res;
    }


}
