package com.dawnchau.webclass.controller;

import com.dawnchau.webclass.dto.CartDTO;
import com.dawnchau.webclass.dto.HobbyUserDTO;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.dto.UserDTO;
import com.dawnchau.webclass.service.*;
import com.dawnchau.webclass.vo.BookStatisticsVO;
import com.dawnchau.webclass.vo.ResultVO;
import com.dawnchau.webclass.vo.UserConsumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HobbyUserService hobbyUserService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/admin/disable/{id}")
    public ResultVO<UserDTO> disableUser(@PathVariable Integer id){
        return userService.disableUser(id);
    }

    @RequestMapping("/user/register")
    public ResultVO<UserDTO> register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @RequestMapping("/user/info/{id}")
    public ResultVO<UserDTO> login(@PathVariable Integer id){
        return userService.getUserInfo(id);
    }

    @RequestMapping("/user/hobbies")
    public ResultVO<HobbyUserDTO> addHobbies(@RequestBody HobbyUserDTO hobbyUserDTO){
        return hobbyUserService.addHobbyForUser(hobbyUserDTO.getUserId(),hobbyUserDTO.getHobbies());
    }

    @RequestMapping("/user/addcart")
    public ResultVO<CartDTO> addBookTocart(@RequestBody CartDTO cartDTO){
        return cartService.addBookToCart(cartDTO);
    }

    @GetMapping("/user/listallcartbooks")
    public ResultVO<List<CartDTO>> listAllBooksInCart(@RequestParam("userId") Integer usrtId){
        return cartService.listAllBooksInCart(usrtId);
    }

    @GetMapping("/user/listorders")
    public ResultVO<List<OrderDTO>> listOrders(@RequestParam("userId") Integer usrtId){
        return orderService.listUserOrders(usrtId);
    }

    @GetMapping("/admin/listallorders")
    public ResultVO<List<OrderDTO>> listAllOrders(){
        return orderService.listAllOrders();
    }

    @GetMapping("/user/listordersbetween")
    public ResultVO<List<OrderDTO>> listOrdersBetween(@RequestParam("start")String start,@RequestParam("end")String end){

        return orderService.findOrderBetween(new Timestamp(new Date(Long.parseLong(start)*1000L).getTime()),
                new Timestamp(new Date(Long.parseLong(end)*1000L).getTime()));
    }

    @GetMapping("/admin/statistics")
    public ResultVO<Set<BookStatisticsVO>> listStatistics(@RequestParam("start")String start,@RequestParam("end")String end){
        return bookService.bookStatistics(new Timestamp(new Date(Long.parseLong(start)*1000L).getTime()),
                new Timestamp(new Date(Long.parseLong(end)*1000L).getTime()));
    }

    @GetMapping("/admin/userconsume")
    public ResultVO<Set<UserConsumeVo>> listUserConsume(@RequestParam("start")String start, @RequestParam("end")String end){
        return userService.listUserConsume(new Timestamp(new Date(Long.parseLong(start)*1000L).getTime()),
                new Timestamp(new Date(Long.parseLong(end)*1000L).getTime()));
    }
}
