package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.HobbyRepo;
import com.dawnchau.webclass.dto.HobbyDTO;
import com.dawnchau.webclass.pojo.HobbyEntity;
import com.dawnchau.webclass.service.HobbyService;
import com.dawnchau.webclass.utils.Entity2DtoUtils;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HobbyServiceImpl implements HobbyService {

    @Autowired
    private HobbyRepo hobbyRepo;

    @Override
    public String listAllHobbies() {
        List<HobbyEntity> list = hobbyRepo.findAll();
        List<HobbyDTO> hobbyDTOS = new ArrayList<>();
        for(HobbyEntity hobbyEntity: list){
            hobbyDTOS.add(Entity2DtoUtils.HobbyEntity2HobbyDto(hobbyEntity));
        }
        return ResultVO.fillResultString(ResultMsgConstants.HOBBY_FIND_SUCCESS,hobbyDTOS);
    }
}
