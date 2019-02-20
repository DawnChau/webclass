package com.dawnchau.webclass.dao;

import java.util.List;

import com.dawnchau.webclass.pojo.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer>{

    List<OrderEntity> findByUserid(Integer userId);
}
