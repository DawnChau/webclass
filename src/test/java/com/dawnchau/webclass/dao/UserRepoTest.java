package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

    @Autowired
    public UserRepo userRepo;

    @Test
    public void save(){
        UserEntity userEntity = new UserEntity();
        userEntity.setDisabled((byte) 0);
        userEntity.setAccount("dawnchau");
        userEntity.setRole(0);
        userEntity.setPassword("20151111");
        userEntity.setEmail("czcacqcc@qq.com");

        userRepo.save(userEntity);
    }


    @Test
    public void findOneByAccount(){
        log.info("{}",userRepo.findOneByAccount("dawnchau").get().getAccount());
    }
}