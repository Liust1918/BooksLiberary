package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.studentBookRepository;
import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.DTO.studentBookDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-09 14:28
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Service
public class studentBookService {

    @Resource
    private studentBookRepository studentBookRepository;


    @Resource
    private studentsService studentsService;


    public Integer addStudentBook(studentBook studentBook) {
        studentBook.setStatus(0);
        int insert = studentBookRepository.insert(studentBook);
        return insert;
    }

    public Integer returnStudentBook(studentBook book) {
        LambdaQueryWrapper<studentBook> qw = new LambdaQueryWrapper<>();
        qw.eq(studentBook::getBookId, book.getBookId());
        qw.eq(studentBook::getStudentId, book.getStudentId());
        qw.eq(studentBook::getLendTime,book.getLendTime());
        qw.eq(studentBook::getReturnTime, book.getReturnTime());
        book.setStatus(1);
        int update = studentBookRepository.update(book, qw);
        return update;
    }

    public List<studentBookDTO> getAllStudentBooks(String account_name) {
        List<studentBookDTO> allsbDTO = studentBookRepository.getAllsbDTO(account_name);
        return allsbDTO;
    }

}
