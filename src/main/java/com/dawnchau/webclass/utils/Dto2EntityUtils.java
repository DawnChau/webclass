package com.dawnchau.webclass.utils;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.pojo.BookEntity;
import com.dawnchau.webclass.pojo.UserEntity;

public class Dto2EntityUtils {

    /**
     * 用户
     * @param userDTO
     * @return
     */
    public static UserEntity userDto2Entity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        userEntity.setAccount(userDTO.getAccount());
        userEntity.setDisabled((byte) (userDTO.getDisabled()?1:0));
        return userEntity;
    }

    /**
     * 书
     * @param bookDTO
     * @return
     */
    public static BookEntity bookDto2Entity(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setStock(bookDTO.getStock());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setCover(bookDTO.getCover());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setName(bookDTO.getName());
        return bookEntity;
    }
}
