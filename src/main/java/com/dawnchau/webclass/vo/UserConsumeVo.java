package com.dawnchau.webclass.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 用来统计用户消费
 */
@Data
public class UserConsumeVo {
    private int userId;
    private BigDecimal totalConsume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConsumeVo that = (UserConsumeVo) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }
}
