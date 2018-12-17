package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepo extends JpaRepository<HobbyEntity,Integer> {
}
