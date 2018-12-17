package com.dawnchau.webclass.service;

import com.dawnchau.webclass.dto.HobbyUserDTO;
import com.dawnchau.webclass.vo.ResultVO;

import java.util.List;

public interface HobbyUserService {

    ResultVO<HobbyUserDTO> addHobbyForUser(Integer userId, List<Integer> hobbyIds);
}
