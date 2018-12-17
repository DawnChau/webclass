package com.dawnchau.webclass.utils;

import com.dawnchau.webclass.dto.HobbyDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.pojo.HobbyEntity;
import com.dawnchau.webclass.pojo.UserEntity;

public class Entity2DtoUtils {

    /**
     * 用户
     * @param entity
     * @return
     */
    public static UserDTO UserEntity2UserDto(UserEntity entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(entity.getAccount());
        userDTO.setDisabled(entity.getDisabled()==0?false:true);
        userDTO.setEmail(entity.getEmail());
        userDTO.setId(entity.getId());
        userDTO.setRole(entity.getRole());
        userDTO.setPassword(entity.getPassword());
        return  userDTO;
    }

    /**
     * 爱好
     * @param hobbyEntity
     * @return
     */
    public static HobbyDTO HobbyEntity2HobbyDto(HobbyEntity hobbyEntity){
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(hobbyEntity.getId());
        hobbyDTO.setHobby(hobbyEntity.getHobby());
        return hobbyDTO;
    }
}
