package com.dawnchau.webclass.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDTO {
    private Integer id;
    private Integer bookid;
    private Integer userid;
    private Integer quantity;
    private BookDTO bookDetail;
    private BigDecimal totalPrice;
}
