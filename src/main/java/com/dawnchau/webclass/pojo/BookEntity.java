package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "webclass", catalog = "")
public class BookEntity {
    private int id;
    private String name;
    private String author;
    private byte[] cover;
    private String isbn;
    private int stock;
    private BigDecimal price;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "cover")
    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                stock == that.stock &&
                Objects.equals(name, that.name) &&
                Objects.equals(author, that.author) &&
                Arrays.equals(cover, that.cover) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, author, isbn, stock, price);
        result = 31 * result + Arrays.hashCode(cover);
        return result;
    }
}
