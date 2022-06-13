package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.booksRepository;
import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.VO.booksVO;
import com.liust.bookmanage.Service.MyService.booksADService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyulong
 * @create 2022-06-06 16:58
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class booksService implements booksADService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private booksRepository booksRepository;

    @Resource
    private studentsService studentsService;

    @Resource
    private studentBookService studentBookService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addBook(books books) {
        books.setStatus(0);
        int insert = booksRepository.insert(books);
        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBook(Integer book_id) {
        int i = booksRepository.deleteById(book_id);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateBook(books books, Integer book_id) {
        books.setBookid(String.valueOf(book_id));
        int i = booksRepository.updateById(books);
        return i;
    }


    /**
     * 已弃用
     * @param book_name
     * @return
     */
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
        qw.eq(books::getId, book_id);
        books book = booksRepository.selectOne(qw);
        return book;
    }

    @Override
    public List<booksVO> getAllBooks() {
        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        List<books> books = booksRepository.selectList(qw);
        List<booksVO> collect = books.stream().map(a -> {
            booksVO booksVO = new booksVO();
            students oneStudentByID = studentsService.getOneStudentByID(String.valueOf(a.getLenduserid()));
            booksVO.toBookVOByBooks(a,oneStudentByID==null?"无":oneStudentByID.getStudentname());
            return booksVO;
        }).collect(Collectors.toList());
        return collect;
    }


    /**
     * 借出书
     *
     * @param books
     * @param account_name
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer lendBook(books books, String account_name,String lend_day) {
        students oneStudent = studentsService.getOneStudentByAccount(account_name);
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

        //记录用户借阅
        studentBook studentBook = new studentBook();
        studentBook.setBookId(books.getId());
        studentBook.setStudentId(oneStudent.getId());
        studentBook.setLendTime(String.valueOf(lenddate));
        studentBook.setReturnTime(String.valueOf(returndate));
        Integer integer = studentBookService.addStudentBook(studentBook);
        log.info(integer+"记录用户借阅成功 "+studentBook.toString());

        return i;
    }

    /**
     * 归还书
     * @param books
     * @param account_name
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer returnBook(books books, String account_name) {
        students oneStudent = studentsService.getOneStudentByAccount(account_name);
        if(ObjectUtils.isEmpty(oneStudent)){
            return null;
        }

        List<books> lendBooksByUserName = this.getLendBooksByUserAccount(account_name);
        List<books> collect = lendBooksByUserName.stream().filter(books1 ->
                books1.getBookid().equals(books.getBookid())
        ).collect(Collectors.toList());

        books books1 = collect.stream().findFirst().get();
        String lendtime = books1.getLenddate();
        String returntime = books1.getReturndate();

        books1.setLenddate("");
        books1.setReturndate("");
        books1.setStatus(0);
        books1.setLenduserid(-1);
        Integer integer = this.updateBook(books1, books1.getId());

        //记录归还
        studentBook studentBook = new studentBook();
        studentBook.setBookId(books.getId());
        studentBook.setStudentId(oneStudent.getId());
        studentBook.setLendTime(String.valueOf(lendtime));
        studentBook.setReturnTime(String.valueOf(returntime));
        Integer integer1 = studentBookService.returnStudentBook(studentBook);
        log.info(integer1+"记录用户归还成功 "+studentBook.toString());

        return integer;
    }

    /**
     * 查看学生的借出的书
     * @param account_name
     * @return
     */
    @Override
    public List<books> getLendBooksByUserAccount(String account_name) {
        students oneStudent = studentsService.getOneStudentByAccount(account_name);
        if(ObjectUtils.isEmpty(oneStudent)){
            return null;
        }

        LambdaQueryWrapper<books> qw = new LambdaQueryWrapper<>();
        qw.eq(books::getLenduserid, oneStudent.getId());
        List<books> books = booksRepository.selectList(qw);
        return books;
    }
}
