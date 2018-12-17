package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findOneByAccount(String account);

    boolean existsByAccount(String account);
}
