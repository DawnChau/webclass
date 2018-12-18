package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @RequestMapping("/all")
    public String listAllHobbies(){
        return hobbyService.listAllHobbies();
    }
}
