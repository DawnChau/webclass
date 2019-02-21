package com.dawnchau.webclass.service.impl;

import com.dawnchau.webclass.constants.ResultMsgConstants;
import com.dawnchau.webclass.dao.BookRepo;
import com.dawnchau.webclass.dto.BookDTO;
import com.dawnchau.webclass.dto.OrderDTO;
import com.dawnchau.webclass.exception.BookNotExistException;
import com.dawnchau.webclass.pojo.BookEntity;
import com.dawnchau.webclass.pojo.OrderDetailEntity;
import com.dawnchau.webclass.service.BookService;
import com.dawnchau.webclass.service.OrderService;
import com.dawnchau.webclass.utils.Dto2EntityUtils;
import com.dawnchau.webclass.utils.Entity2DtoUtils;
import com.dawnchau.webclass.vo.BookStatisticsVO;
import com.dawnchau.webclass.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private OrderService orderService;

    /**
     * 列举所有的书籍，只有管理员才有权限
     * @return
     */
    @Override
    public String listAllBooks() {
        List<BookEntity> list = bookRepo.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(BookEntity bookEntity:list){
            bookDTOList.add(Entity2DtoUtils.bookEntity2BookDto(bookEntity));
        }
        return ResultVO.fillResultString(ResultMsgConstants.BOOKS_FIND_SUCCESS_ADMIN,bookDTOList);
    }

    /**
     * 管理员修改某本书
     * @param bookDTO
     * @return
     */
    @Transactional
    @Override
    public ResultVO<BookDTO> updateBooks(BookDTO bookDTO) {
        BookEntity bookEntity = Dto2EntityUtils.bookDto2Entity(bookDTO);
        bookEntity.setId(bookDTO.getId());
        bookRepo.save(bookEntity);
        return new ResultVO<>(ResultMsgConstants.BOOK_UPDATE_SUCCESS_ADMIN,
                Entity2DtoUtils.bookEntity2BookDto(
                        bookRepo.findById(bookEntity.getId()).get()));
    }


    /**
     * 管理员添加图书
     * @param bookDTO
     * @return
     */
    @Transactional
    @Override
    public ResultVO<BookDTO> addBook(BookDTO bookDTO) {
        BookEntity bookEntity = Dto2EntityUtils.bookDto2Entity(bookDTO);
        bookEntity = bookRepo.save(bookEntity);
        return new ResultVO<>(ResultMsgConstants.BOOK_ADD_SUCCESS_ADMIN,
                Entity2DtoUtils.bookEntity2BookDto(bookEntity));
    }

    /**
     * 管理员可以删除图书
     * @param id
     * @return
     */
    @Transactional
    @Override
    public ResultVO<BookDTO> deleteBook(Integer id) {
        bookRepo.deleteById(id);
        return new ResultVO<>(ResultMsgConstants.BOOK_DELETE_SUCCESS_ADMIN,null);
    }

    /**
     * 根据ID查找图书
     * @param id
     * @return
     */
    @Override
    public BookDTO findBookById(Integer id) throws BookNotExistException{
        Optional<BookEntity> entity = bookRepo.findById(id);
        if(!entity.isPresent()){
            throw new BookNotExistException(ResultMsgConstants.BOOK_NOT_EXIST);
        }else{
            return Entity2DtoUtils.bookEntity2BookDto(entity.get());
        }
    }


    /**
     * 统计指定时间内的图书销量
     * @param start
     * @param end
     * @return
     */
    public ResultVO<Set<BookStatisticsVO>> bookStatistics(Timestamp start, Timestamp end){
        ResultVO<List<OrderDTO>> res = orderService.findOrderBetween(start, end);
        List<OrderDTO> orderDTOS = res.getData();

        HashMap<Integer,BookStatisticsVO> bookStatisticsVOS = new HashMap<>();
        for(OrderDTO orderDTO:orderDTOS){
            List<OrderDetailEntity> orderDetailEntities = orderDTO.getDetailEntities();
            for(OrderDetailEntity orderDetailEntity:orderDetailEntities){
                BookStatisticsVO bookStatisticsVO = new BookStatisticsVO();
                bookStatisticsVO.setBookId(bookRepo.findByName(orderDetailEntity.getBookName()).getId());
                bookStatisticsVO.setBookName(orderDetailEntity.getBookName());
                if(bookStatisticsVOS.containsKey(bookStatisticsVO.getBookId())){
                    bookStatisticsVO.setSales(bookStatisticsVOS.get(bookStatisticsVO.getBookId()).getSales()
                            +orderDetailEntity.getQuantity());
                    bookStatisticsVOS.replace(bookStatisticsVO.getBookId(),bookStatisticsVO);
                }else{
                    bookStatisticsVO.setSales(orderDetailEntity.getQuantity());
                }
                bookStatisticsVOS.put(bookStatisticsVO.getBookId(),bookStatisticsVO);
            }
        }
        ResultVO<Set<BookStatisticsVO>> resultVO = new ResultVO<>();
        Iterator<Integer> it = bookStatisticsVOS.keySet().iterator();
        Set<BookStatisticsVO> bookStatisticsVOSet = new HashSet<>();
        while(it.hasNext()){
            bookStatisticsVOSet.add(bookStatisticsVOS.get(it.next()));
        }
        resultVO.setData(bookStatisticsVOSet);
        resultVO.setMsg(ResultMsgConstants.LIST_BOOK_STATISTICS_SUCCESS);
        return resultVO;
    }


}
