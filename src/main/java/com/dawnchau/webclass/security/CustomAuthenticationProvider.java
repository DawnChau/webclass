package com.dawnchau.webclass.security;


import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.exception.PasswordWrongException;
import com.dawnchau.webclass.exception.UserDisabledException;
import com.dawnchau.webclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 自定义认证管理器
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;


    /**
     * 认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // TODO 权限

        // 在这里进行用户名密码验证
        if(userService.isPasswordCorrect(name,password)){

            // 判断用户是否被封
            if(!userService.isUserDisabled(name)){
                // 生成token
                Authentication auth = new UsernamePasswordAuthenticationToken(name,password,new ArrayList<>());
                return auth;
            }
            throw new UserDisabledException(ResultMsgConstants.USER_DISABLED);
        }


        throw new PasswordWrongException(ResultMsgConstants.USER_PASSWORD_WRONG);

    }


    /**
     * 是否提供输入类型的认证服务
     * 只支持 UsernamePasswordAuthenticationToken 类型的认证服务
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
