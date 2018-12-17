package com.dawnchau.webclass.dto;

import lombok.Data;

import java.util.List;

@Data
public class HobbyUserDTO {
    private Integer userId;
    private List<Integer> hobbies;
}
