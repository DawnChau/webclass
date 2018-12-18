package com.dawnchau.webclass.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {
    private Integer id;
    private String name;
    private String author;
    private byte[] cover;
    private String isbn;
    private Integer stock;
    private BigDecimal price;
}
