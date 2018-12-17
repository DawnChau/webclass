package com.dawnchau.webclass.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


public class GrantedAuthorityImpl implements GrantedAuthority{

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    /**
     * 获取权限
     * @return
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
