package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "my_order", schema = "webclass", catalog = "")
public class OrderEntity {
    private int id;
    private BigDecimal totalPrice;
    private int userid;

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
    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                userid == that.userid &&
                Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, totalPrice, userid);
    }
}
