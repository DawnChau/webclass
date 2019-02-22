package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.service.UserService;
import com.dawnchau.webclass.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    public UserService userService;

    @Test
    public void disableUser() {
        log.info("{}",userService.disableUser(1));
    }


    @Test
    public void registerUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("123456");
        userDTO.setRole(0);
        userDTO.setAccount("hello");
        userDTO.setEmail("czcacqcc@163.com");
        userDTO.setDisabled(false);
        log.info("{}",userService.register(userDTO));
    }

    @Test
    public void getUserInfo(){
        log.info("{}",userService.getUserInfo("dawnchau"));
    }
}