package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.UserRepo;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.pojo.OrderDetailEntity;
import com.dawnchau.webclass.pojo.UserEntity;
import com.dawnchau.webclass.service.OrderService;
import com.dawnchau.webclass.service.UserService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.utils.Entity2DtoUtils;
import com.dawnchau.webclass.vo.BookStatisticsVO;
import com.dawnchau.webclass.vo.ResultVO;
import com.dawnchau.webclass.vo.UserConsumeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderService orderService;


    /**
     *  禁用用户
     * @return
     */
    @Transactional
    @Override
    public ResultVO<UserDTO> disableUser(Integer id){
        ResultVO<UserDTO> resultVO = null;
        Optional<UserEntity> entity = userRepo.findById(id);

        if(!entity.isPresent()){
            // 如果用户不存在
            resultVO = new ResultVO<>(ResultMsgConstants.USER_NOT_EXIST,null);
        }else{
            UserDTO userDTO = Entity2DtoUtils.userEntity2UserDto(entity.get());

            if(entity.get().getDisabled()==1){
                // 解禁用户
                entity.get().setDisabled((byte) 0);
                userRepo.save(entity.get());
                userDTO.setDisabled(entity.get().getDisabled()==0?false:true);

                resultVO = new ResultVO<>(ResultMsgConstants.USER_UNDISABLE_SUCCESS,userDTO);
            }else{
                // 禁用用户
                entity.get().setDisabled((byte) 1);
                userRepo.save(entity.get());
                userDTO.setDisabled(entity.get().getDisabled()==0?false:true);

                resultVO = new ResultVO<>(ResultMsgConstants.USER_DISABLE_SUCCESS,userDTO);
            }

        }

        return resultVO;
    }


    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    @Transactional
    @Override
    public ResultVO<UserDTO> register(UserDTO userDTO) {
        ResultVO<UserDTO> resultVO = null;
        Optional<UserEntity> entity = userRepo.findOneByAccount(userDTO.getAccount());

        if(entity.isPresent()){
            resultVO = new ResultVO<>(ResultMsgConstants.USER_EXIST,null);
        }else{
            UserEntity userEntity = userRepo.save(Dto2EntityUtils.userDto2Entity(userDTO));
            userDTO.setId(userEntity.getId());
            resultVO = new ResultVO<>(ResultMsgConstants.USER_SAVE_SUCCESS,userDTO);
        }

        return resultVO;
    }


    /**
     * 判断密码是否正确
     * @param name
     * @param password
     * @return
     */
    @Override
    public boolean isPasswordCorrect(String name, String password) throws UsernameNotFoundException{
        if(!userRepo.existsByAccount(name)){
            throw new UsernameNotFoundException(ResultMsgConstants.USER_NOT_EXIST);
        }
        UserDTO userDTO = Entity2DtoUtils.userEntity2UserDto(userRepo.findOneByAccount(name).get());
        return userDTO.getPassword().equals(password);
    }

    /**
     * 判断用户是否被封
     * @param name
     * @return
     */
    @Override
    public boolean isUserDisabled(String name){
        return userRepo.findOneByAccount(name).get().getDisabled()==1?true:false;
    }


    /**
     * 获取用户信息
     * @param username
     * @return
     */
    @Override
    public ResultVO<UserDTO> getUserInfo(String username) {
        ResultVO<UserDTO> resultVO = null;
        Optional<UserEntity> entity = userRepo.findOneByAccount(username);


        UserDTO userDTO = Entity2DtoUtils.userEntity2UserDto(entity.get());
        resultVO = new ResultVO<>(ResultMsgConstants.USER_INFO_SUCCESS,userDTO);

        return resultVO;
    }

    /**
     * 判断用户是否是管理员
     * @param name
     * @return
     */
    @Override
    public boolean isAdmin(String name) {
        return userRepo.findOneByAccount(name).get().getRole()==1;
    }


    /**
     * 统计指定时间内的用户消费
     * @param start
     * @param end
     * @return
     */
    public ResultVO<Set<UserConsumeVo>> listUserConsume(Timestamp start, Timestamp end){
        ResultVO<List<OrderDTO>> res = orderService.findOrderBetween(start, end);
        List<OrderDTO> orderDTOS = res.getData();

        HashMap<Integer,UserConsumeVo> userConsumeVoHashMap = new HashMap<>();
        for(OrderDTO orderDTO:orderDTOS){
                UserConsumeVo userConsumeVo = new UserConsumeVo();
                userConsumeVo.setUserId(orderDTO.getUserid());
                if(userConsumeVoHashMap.containsKey(userConsumeVo.getUserId())){
                    userConsumeVo.setTotalConsume(userConsumeVoHashMap.get(userConsumeVo.getUserId()).getTotalConsume()
                            .add(orderDTO.getTotalPrice()));
                    userConsumeVoHashMap.replace(userConsumeVo.getUserId(),userConsumeVo);
                }else{
                    userConsumeVo.setTotalConsume(orderDTO.getTotalPrice());
                }
                userConsumeVoHashMap.put(userConsumeVo.getUserId(),userConsumeVo);
        }
        ResultVO<Set<UserConsumeVo>> resultVO = new ResultVO<>();
        Iterator<Integer> it = userConsumeVoHashMap.keySet().iterator();
        Set<UserConsumeVo> userConsumeVoHashSet = new HashSet<>();
        while(it.hasNext()){
            userConsumeVoHashSet.add(userConsumeVoHashMap.get(it.next()));
        }
        resultVO.setData(userConsumeVoHashSet);
        resultVO.setMsg(ResultMsgConstants.LIST_USER_CONSUME_SUCCESS);
        return resultVO;
    }
}
