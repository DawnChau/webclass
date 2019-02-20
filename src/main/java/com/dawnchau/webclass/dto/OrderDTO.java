package com.dawnchau.webclass.dto;

import com.dawnchau.webclass.pojo.OrderDetailEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private BigDecimal totalPrice;
    private Integer userid;
    private String createTime;
    private List<OrderDetailEntity> detailEntities;
}
