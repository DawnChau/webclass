package com.dawnchau.webclass.vo;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 用于图书销量
 */
@Data
public class BookStatisticsVO {
    public int bookId;
    public String bookName;
    public int sales;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStatisticsVO that = (BookStatisticsVO) o;
        return bookId == that.bookId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookId);
    }

    public static void main(String[] args) {
        BookStatisticsVO a1 = new BookStatisticsVO();
        a1.setBookId(4);
        BookStatisticsVO a2 = new BookStatisticsVO();
        a2.setBookId(4);
        Set<BookStatisticsVO> set = new HashSet<>();
        set.add(a1);
        System.out.println(set.contains(a2));

    }
}
