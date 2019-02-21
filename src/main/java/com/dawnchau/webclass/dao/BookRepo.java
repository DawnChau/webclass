package com.dawnchau.webclass.dao;

import com.dawnchau.webclass.pojo.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookEntity,Integer>{

    BookEntity findByName(String name);
}
