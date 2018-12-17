package com.dawnchau.webclass.security;

import lombok.Data;

/**
 * 只包含用户名和密码的用户信息验证类
 */
@Data
public class AccountCredentials {

    private String username;

    private String password;
}
