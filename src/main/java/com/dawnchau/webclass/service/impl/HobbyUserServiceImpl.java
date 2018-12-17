package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.HobbyUserRepo;
import com.dawnchau.webclass.dto.HobbyUserDTO;
import com.dawnchau.webclass.pojo.HobbyUserEntity;
import com.dawnchau.webclass.service.HobbyUserService;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HobbyUserServiceImpl implements HobbyUserService {

    @Autowired
    private HobbyUserRepo hobbyUserRepo;

    /**
     * 为用户添加习惯
     * @param userId
     * @param hobbyIds
     */
    @Transactional
    @Override
    public ResultVO<HobbyUserDTO> addHobbyForUser(Integer userId, List<Integer> hobbyIds) {
        HobbyUserDTO hobbyUserDTO = new HobbyUserDTO();
        hobbyUserDTO.setUserId(userId);
        hobbyUserDTO.setHobbies(hobbyIds);
        ResultVO<HobbyUserDTO> resultVO = new ResultVO<>(ResultMsgConstants.HOBBY_ADD_SUCCESS,hobbyUserDTO);
        HobbyUserEntity hobbyUserEntity = null;
        for(Integer i : hobbyIds){
            hobbyUserEntity = new HobbyUserEntity();
            hobbyUserEntity.setUserid(userId);
            hobbyUserEntity.setHobbyid(i);
            hobbyUserRepo.save(hobbyUserEntity);
        }
        return resultVO;
    }
}
