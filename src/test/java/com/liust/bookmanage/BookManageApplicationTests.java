package com.liust.bookmanage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.booksRepository;
import com.liust.bookmanage.POJO.DO.books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookManageApplicationTests {

    @Autowired
    private booksRepository booksRepository;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        List<books> books = booksRepository.selectList(qw);
       books.stream().forEach(books1 -> System.out.println(books));

    }

}
