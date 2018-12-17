package com.dawnchau.webclass.filter;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.constants.TokenHeaderConstants;
import com.dawnchau.webclass.exception.PasswordWrongException;
import com.dawnchau.webclass.exception.UserDisabledException;
import com.dawnchau.webclass.security.AccountCredentials;
import com.dawnchau.webclass.security.GrantedAuthorityImpl;
import com.dawnchau.webclass.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);

    }


    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return super.getAuthenticationManager();
    }


    /**
     * 验证用户
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AccountCredentials user = new ObjectMapper()
                    .readValue(request.getInputStream(),AccountCredentials.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword())
            );
        } catch (IOException e) {
        }
        return null;  // TODO 抛异常后会被删除
    }

    /**
     * 成功验证用户
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                // 将角色信息，写入token中
                .claim(TokenHeaderConstants.HEADER_ROLE,
                        ((List<GrantedAuthorityImpl>) authResult.
                                getAuthorities()).get(0).getAuthority())
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis()+60*60*24*1000))
                .signWith(SignatureAlgorithm.HS512,TokenHeaderConstants.HEADER_SECRET)
                .compact();
        response.addHeader(TokenHeaderConstants.HEADER_AUTHORIZATION,
                TokenHeaderConstants.HEADER_AUTHENTICATION+token);

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(ResultVO.fillResultString(ResultMsgConstants.USER_LOGIN_SUCCESS,
                token));


    }

    /**
     * 验证用户不成功 --- 统一异常处理
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // 异常处理
        if(failed instanceof UsernameNotFoundException){
            response.getWriter().println(ResultVO.fillResultString(ResultMsgConstants.USER_NOT_EXIST,null));
        }else if(failed instanceof PasswordWrongException){
            response.getWriter().println(ResultVO.fillResultString(ResultMsgConstants.USER_PASSWORD_WRONG,null));
        }else if(failed instanceof UserDisabledException){
            response.getWriter().println(ResultVO.fillResultString(ResultMsgConstants.USER_DISABLED,null));
        }


    }
}
