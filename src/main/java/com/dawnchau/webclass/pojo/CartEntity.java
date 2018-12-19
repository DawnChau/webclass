package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "webclass", catalog = "")
public class CartEntity {
    private int id;
    private int bookid;
    private int userid;
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
    @Column(name = "bookid")
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
        CartEntity that = (CartEntity) o;
        return id == that.id &&
                bookid == that.bookid &&
                userid == that.userid &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bookid, userid, quantity);
    }
}
