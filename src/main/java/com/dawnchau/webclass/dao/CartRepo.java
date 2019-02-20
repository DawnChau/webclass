package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.CartEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,Integer>{
    List<CartEntity> findByUserid(Integer userId);

    CartEntity findByUseridAndBookid(Integer userId, Integer bookId);

    void deleteCartEntitiesByBookidAndUserid(Integer bookId, Integer userId);
}
