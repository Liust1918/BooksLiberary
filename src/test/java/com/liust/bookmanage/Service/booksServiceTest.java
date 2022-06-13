package com.liust.bookmanage.Service;

import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.VO.booksVO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuyulong
 * @create 2022-06-07 15:07
 * @create 2022-六月  星期二
 * @project BookManage
 */
@SpringBootTest
class booksServiceTest {

    @Autowired
    private booksService booksService;

    @Test
    void addBook() {
        books books = new books();
        books.setAuthor("山泥若");
        books.setBookname("赌博的艺术");
        books.setBooknumber("B111");
        books.setCompany("lol出版社");
        books.setBookid("B111-1");
        books.setPicname("1.jpg");
        books.setPrice("114514");
        Integer integer = booksService.addBook(books);
        System.out.println(integer);
    }

    @Test
    void deleteBook() {
        Integer integer = booksService.deleteBook(50);
        System.out.println(integer);
    }

    @Test
    void updateBook() {
        books books = new books();
        books.setAuthor("山泥若");
        books.setBookname("赌博的艺术");
        books.setBooknumber("B111");
        books.setCompany("lol出版社");
        books.setBookid("B111-1");
        books.setPicname("1.jpg");
        books.setPrice("114514");
        booksService.addBook(books);

        books.setAuthor("uzi");
        Integer integer1 = booksService.updateBook(books, books.getId());
        System.out.println(integer1);
    }

    @Test
    void getOneBookByName() {
//        books z = booksService.getOneBookByName("赌博的艺术");
//        System.out.println(z);
    }

    @Test
    void getOneBookById() {

       books oneBookById = booksService.getOneBookById(46);
        System.out.println(oneBookById);
    }

    @Test
    void getAllBooks() {
        List<booksVO> allBooks = booksService.getAllBooks();
        System.out.println(allBooks);
    }

    @Test
    void lendBook() {
        books oneBookById = booksService.getOneBookById(46);
        Integer integer = booksService.lendBook(oneBookById, "小红", "5");
        System.out.println(integer);
    }

    @Test
    void returnBook() {
        books oneBookById = booksService.getOneBookById(46);
        Integer integer = booksService.returnBook(oneBookById, "小红");
        System.out.println(integer);
    }

    @Test
    void getLendBooksByUserName() {
        List<books> a = booksService.getLendBooksByUserAccount("小红");
        System.out.println(a);
    }
}