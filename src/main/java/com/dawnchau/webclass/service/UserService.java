package com.dawnchau.webclass.service;


import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.exception.UserDisabledException;
import com.dawnchau.webclass.security.AccountCredentials;
import com.dawnchau.webclass.vo.ResultVO;
import com.dawnchau.webclass.vo.UserConsumeVo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface UserService {

    ResultVO<UserDTO> disableUser(Integer id);

    ResultVO<UserDTO> register(UserDTO userDTO);

    boolean isPasswordCorrect(String name, String password);

    boolean isUserDisabled(String name);

    ResultVO<UserDTO> getUserInfo(String username);

    boolean isAdmin(String name);

    ResultVO<Set<UserConsumeVo>> listUserConsume(Timestamp start, Timestamp end);

    ResultVO<List<UserDTO>> getAllUsers();
}
