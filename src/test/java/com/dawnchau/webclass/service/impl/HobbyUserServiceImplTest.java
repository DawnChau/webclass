package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.dao.HobbyUserRepo;
import com.dawnchau.webclass.service.HobbyUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HobbyUserServiceImplTest {

    @Autowired
    HobbyUserService hobbyUserService;

    @Test
    public void addHobbyForUser(){
        Integer[] array = {1,2,3};
        List<Integer> list = Arrays.asList(array);
        hobbyUserService.addHobbyForUser(1,list);
    }
}