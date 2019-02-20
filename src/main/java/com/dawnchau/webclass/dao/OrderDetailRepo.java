package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetailEntity,Integer>{
}
