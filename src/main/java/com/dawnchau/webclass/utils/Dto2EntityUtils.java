package com.dawnchau.webclass.utils;

import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.pojo.UserEntity;

public class Dto2EntityUtils {

    public static UserEntity userDto2Entity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        userEntity.setAccount(userDTO.getAccount());
        userEntity.setDisabled((byte) (userDTO.getDisabled()?1:0));
        return userEntity;
    }
}
