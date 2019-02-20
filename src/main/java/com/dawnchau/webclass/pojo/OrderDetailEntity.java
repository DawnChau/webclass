package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_detail", schema = "webclass", catalog = "")
public class OrderDetailEntity {
    private int id;
    private int orderid;
    private String bookName;
    private String bookAuthor;
    private String bookIsbn;
    private int bookStock;
    private BigDecimal bookPrice;
    private int quantity;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  // 主键自增
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "book_author")
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "book_isbn")
    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Basic
    @Column(name = "book_stock")
    public int getBookStock() {
        return bookStock;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    @Basic
    @Column(name = "book_price")
    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return id == that.id &&
                orderid == that.orderid &&
                bookStock == that.bookStock &&
                quantity == that.quantity &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(bookAuthor, that.bookAuthor) &&
                Objects.equals(bookIsbn, that.bookIsbn) &&
                Objects.equals(bookPrice, that.bookPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderid, bookName, bookAuthor, bookIsbn, bookStock, bookPrice, quantity);
    }
}
