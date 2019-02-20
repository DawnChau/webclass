package com.dawnchau.webclass.utils;

import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.dto.HobbyDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.pojo.BookEntity;
import com.dawnchau.webclass.pojo.CartEntity;
import com.dawnchau.webclass.pojo.HobbyEntity;
import com.dawnchau.webclass.pojo.UserEntity;

public class Entity2DtoUtils {

    /**
     * 用户
     * @param entity
     * @return
     */
    public static UserDTO userEntity2UserDto(UserEntity entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(entity.getAccount());
        userDTO.setDisabled(entity.getDisabled()==0?false:true);
        userDTO.setEmail(entity.getEmail());
        userDTO.setId(entity.getId());
        userDTO.setRole(entity.getRole());
        userDTO.setPassword(entity.getPassword());
        return  userDTO;
    }

    /**
     * 爱好
     * @param hobbyEntity
     * @return
     */
    public static HobbyDTO hobbyEntity2HobbyDto(HobbyEntity hobbyEntity){
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(hobbyEntity.getId());
        hobbyDTO.setHobby(hobbyEntity.getHobby());
        return hobbyDTO;
    }

    /**
     * 图书
     * @param bookEntity
     * @return
     */
    public static BookDTO bookEntity2BookDto(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setCover(bookEntity.getCover());
        bookDTO.setId(bookEntity.getId());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setName(bookEntity.getName());
        bookDTO.setPrice(bookEntity.getPrice());
        bookDTO.setStock(bookEntity.getStock());
        return bookDTO;
    }

    /**
     * 购物车项
     * @param entity
     * @return
     */
    public static CartDTO cartEntity2CartDto(CartEntity entity){
        CartDTO res = new CartDTO();
        res.setQuantity(entity.getQuantity());
        res.setBookid(entity.getBookid());
        res.setId(entity.getId());
        res.setUserid(entity.getUserid());
        return res;
    }
}
