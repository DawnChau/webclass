package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,Integer>{
}
