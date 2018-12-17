package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.dto.HobbyUserDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.security.AccountCredentials;
import com.dawnchau.webclass.service.HobbyUserService;
import com.dawnchau.webclass.service.UserService;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HobbyUserService hobbyUserService;

    @RequestMapping("/admin/disable/{id}")
    public ResultVO<UserDTO> disableUser(@PathVariable Integer id){
        return userService.disableUser(id);
    }

    @RequestMapping("/user/register")
    public ResultVO<UserDTO> register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @RequestMapping("/user/info/{id}")
    public ResultVO<UserDTO> login(@PathVariable Integer id){
        return userService.getUserInfo(id);
    }

    @RequestMapping("/user/hobbies")
    public ResultVO<HobbyUserDTO> addHobbies(@RequestBody HobbyUserDTO hobbyUserDTO){
        return hobbyUserService.addHobbyForUser(hobbyUserDTO.getUserId(),hobbyUserDTO.getHobbies());
    }
}
