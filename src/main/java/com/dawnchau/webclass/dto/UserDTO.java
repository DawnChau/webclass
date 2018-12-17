package com.dawnchau.webclass.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private Integer role;
    private Boolean disabled;
    private String account;
    private String password;
    private String email;
}
