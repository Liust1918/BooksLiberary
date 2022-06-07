package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.booksRepository;
import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.Service.MyService.booksADService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import sun.util.resources.LocaleData;


import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyulong
 * @create 2022-06-06 16:58
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
public class booksService implements booksADService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private booksRepository booksRepository;

    @Resource
    private studentsService studentsService;


    @Override
    public Integer addBook(books books) {
        books.setStatus(0);
        int insert = booksRepository.insert(books);
        return insert;
    }

    @Override
    public Integer deleteBook(Integer book_id) {
        int i = booksRepository.deleteById(book_id);
        return i;
    }

    @Override
    public Integer updateBook(books books, Integer book_id) {
        books.setBookid(String.valueOf(book_id));
        int i = booksRepository.updateById(books);
        return i;
    }


    @Override
    public books getOneBookByName(String book_name) {
        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        qw.eq(books::getBookname, book_name);
        books book = booksRepository.selectOne(qw);
        return book;
    }

    @Override
    public books getOneBookById(Integer book_id) {
        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        qw.eq(books::getBookid, book_id);
        books book = booksRepository.selectOne(qw);
        return book;
    }

    @Override
    public List<books> getAllBooks() {
        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        List<books> books = booksRepository.selectList(qw);
        return books;
    }


    /**
     * 借出书
     *
     * @param books
     * @param user_name
     * @return
     */
    @Override
    public Integer lendBook(books books, String user_name,String lend_day) {


        students oneStudent = studentsService.getOneStudent(user_name);
        if(ObjectUtils.isEmpty(oneStudent)){
            return null;
        }
        books.setLenduserid(oneStudent.getId());

        LocalDateTime now = LocalDateTime.now();
        long lenddate = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime  localDate = now.plusDays(Long.valueOf(lend_day));
        long returndate=localDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        books.setLenddate(String.valueOf(lenddate));
        books.setReturndate(String.valueOf(returndate));

        books.setStatus(1);

        int i = booksRepository.updateById(books);
        return i;
    }

    /**
     * 归还书
     * @param books
     * @param user_name
     * @return
     */
    @Override
    public Integer returnBook(books books, String user_name) {
        students oneStudent = studentsService.getOneStudent(user_name);
        if(ObjectUtils.isEmpty(oneStudent)){
            return null;
        }

        List<books> lendBooksByUserName = this.getLendBooksByUserName(user_name);
        List<books> collect = lendBooksByUserName.stream().filter(books1 ->
                books1.getBookid().equals(books.getBookid())
        ).collect(Collectors.toList());
        books books1 = collect.stream().findFirst().get();
        books1.setLenduserid(null);
        books1.setReturndate(null);
        books1.setStatus(0);
        books1.setLenduserid(null);

        Integer integer = this.updateBook(books, books.getId());
        return integer;
    }

    /**
     * 查看学生的借出的书
     * @param user_name
     * @return
     */
    @Override
    public List<books> getLendBooksByUserName(String user_name) {
        students oneStudent = studentsService.getOneStudent(user_name);
        if(ObjectUtils.isEmpty(oneStudent)){
            return null;
        }

        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        List<books> books = booksRepository.selectList(qw);
        return books;
    }
}
