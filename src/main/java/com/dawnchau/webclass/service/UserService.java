package com.dawnchau.webclass.service;


import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.security.AccountCredentials;
import com.dawnchau.webclass.vo.ResultVO;

public interface UserService {

    ResultVO<UserDTO> disableUser(Integer id);

    ResultVO<UserDTO> register(UserDTO userDTO);

    boolean isPasswordCorrect(String name, String password);

    UserDTO getUserByAccount(String username);

    ResultVO<UserDTO> getUserInfo(Integer id);
}
