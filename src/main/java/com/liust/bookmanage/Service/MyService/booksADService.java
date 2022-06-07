package com.liust.bookmanage.Service.MyService;

import com.liust.bookmanage.POJO.DO.books;

import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-06 17:01
 * @create 2022-六月  星期一
 * @project BookManage
 */
public interface booksADService {

    Integer addBook(books books);

    Integer deleteBook(Integer book_id);

    Integer updateBook(books books, Integer book_id);

    books getOneBookByName(String book_name);

    books getOneBookById(Integer book_id);

    List<books> getAllBooks();


    Integer lendBook(books books, String user_name,String lend_day);

    Integer returnBook(books books, String user_name);

    List<books> getLendBooksByUserName(String user_name);

}
