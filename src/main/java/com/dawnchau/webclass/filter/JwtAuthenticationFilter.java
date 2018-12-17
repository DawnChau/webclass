package com.dawnchau.webclass.filter;

import com.dawnchau.webclass.constants.TokenHeaderConstants;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenHeaderConstants.HEADER_AUTHORIZATION);

        if(header == null || !header.startsWith(TokenHeaderConstants.HEADER_AUTHENTICATION)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    /**
     * 验证 请求头中是否有token
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(TokenHeaderConstants.HEADER_AUTHORIZATION);
        if(null != token){
            String user = Jwts.parser()
                    .setSigningKey(TokenHeaderConstants.HEADER_SECRET)
                    .parseClaimsJws(token.replace(TokenHeaderConstants.HEADER_AUTHENTICATION,""))
                    .getBody()
                    .getSubject();
            if( null != user){
                return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
            }
        }

        return null;
    }
}
