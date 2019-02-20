package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer>{
}
