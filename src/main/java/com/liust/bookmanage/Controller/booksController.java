package com.liust.bookmanage.Controller;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.DO.category;
import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.DTO.bookAddDTO;
import com.liust.bookmanage.POJO.VO.booksVO;
import com.liust.bookmanage.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyulong
 * @create 2022-06-06 16:52
 * @create 2022-六月  星期一
 * @project BookManage
 */
@RestController
@RequestMapping("/books")
public class booksController {

    @Resource
    private booksService booksService;

    @Resource
    private categoryService categoryService;

    @Resource
    private bookCateService bookCateService;

    private String drop = "不能为空";

    /**
     * 添加书
     * @param books
     * @return
     */
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody bookAddDTO books) {

        String content = "";
        String bookname = books.getBookname();
        String author = books.getAuthor();
        String company = books.getCompany();
        String booknumber = books.getBooknumber();
        String bookid = books.getBookid();
        String picname = books.getPicname();
        String price = books.getPrice();
        String cateName = books.getCateName();

        if(bookname==null||bookname.equals("")){
            content = "名字"+drop;
        }
        if(author==null||author.equals("")){
            content = "作者" + drop;
        }
        if(company==null||company.equals("")){
            content = "出版社" + drop;
        }
        if(booknumber==null||booknumber.equals("")){
            content = "书区域编号" + drop;
        }
        if(bookid==null||bookid.equals("")){
            content = "书区域编号中的号" + drop;
        }
        if(picname==null||picname.equals("")){
            content = "书图片" + drop;
        }
        if(price==null||price.equals("")){
            content = "价格" + drop;
        }
        if(cateName==null||cateName.equals("")){
            content = "分类名" + drop;
        }

        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }



        Integer integer = booksService.addBook(books.toBooks());
        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        category oneByName = categoryService.getOneByName(books.getCateName());
        if(ObjectUtils.isEmpty(oneByName)){
            R r = R.setResult("分类名不存在", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        Integer integer1 = bookCateService.addbookCate(String.valueOf(integer), String.valueOf(oneByName.getCateId()));
        if(integer1.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        R r = R.setResult(integer1, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    /**
     * 删除书
     *
     * @param book_id
     * @return
     */
    @DeleteMapping("/deleteBook")
    public ResponseEntity deleteBooks(String book_id) {

        String content = "";
        if (book_id == null || book_id.equals("")) {
            content = "书的id不能为空";
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        Integer integer = booksService.deleteBook(Integer.valueOf(book_id));
        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult(integer, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


    /**
     * 修改书内容
     * @param books
     * @return
     */
    @PatchMapping("/updateBook")
    public ResponseEntity updateBook(@RequestBody books books){

        Integer id = books.getId();
        String content = "";
        String bookname = books.getBookname();
        String author = books.getAuthor();
        String company = books.getCompany();
        String booknumber = books.getBooknumber();
        String bookid = books.getBookid();
        String picname = books.getPicname();
        String price = books.getPrice();

        if(id==null||id.equals(0)){
            content = "书的id" + drop;
        }
        if(bookname==null||bookname.equals("")){
            content = "名字"+drop;
        }
        if(author==null||author.equals("")){
            content = "作者" + drop;
        }
        if(company==null||company.equals("")){
            content = "出版社" + drop;
        }
        if(booknumber==null||booknumber.equals("")){
            content = "书区域编号" + drop;
        }
        if(bookid==null||bookid.equals("")){
            content = "书区域编号中的号" + drop;
        }
        if(picname==null||picname.equals("")){
            content = "书图片" + drop;
        }
        if(price==null||price.equals("")){
            content = "价格" + drop;
        }

        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }




        //防止影响借阅功能
        books oneBookById = booksService.getOneBookById(books.getId());
        if(ObjectUtils.isEmpty(oneBookById)){
            R r = R.setResult("书籍不存在", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        books.setLenddate(oneBookById.getLenddate());
        books.setReturndate(oneBookById.getReturndate());
        books.setLenduserid(oneBookById.getLenduserid());
        books.setStatus(oneBookById.getStatus());

        Integer integer = booksService.updateBook(books, books.getId());
        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        R r = R.setResult(integer, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


    /**
     * 获取所有书内容
     * @return
     */
    @GetMapping("/getAllBooks")
    public ResponseEntity getAllBoos() {
        List<booksVO> allBooks = booksService.getAllBooks();
        R r = R.setResult(allBooks, MyHttpState.Successful_Run);
        return new ResponseEntity(r,HttpStatus.OK);
    }

    /**
     * 借书
     * @param book_id
     * @param account_name
     * @param lend_day
     * @return
     */
    @PostMapping("/lend")
    public ResponseEntity lendBook(String book_id, String account_name, String lend_day) {

        String content = "";
        if (book_id == null || book_id.equals("")) {
            content = "书的id" + drop;
        }
        if(account_name==null||account_name.equals("")){
            content = "借阅的账户" + drop;
        }
        if(lend_day==null||lend_day.equals("")){
            content = "借阅天数" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        if(!(Integer.valueOf(lend_day)>0)){
            R r = R.setResult("输入借阅天数不符合", MyHttpState.Error_Content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        books oneBookById = booksService.getOneBookById(Integer.valueOf(book_id));
        if(ObjectUtils.isEmpty(oneBookById)){
            R r = R.setResult("查找不到该书", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        Integer status = oneBookById.getStatus();
        if(status.equals(1)){
            R r = R.setResult("书籍已经被借出", MyHttpState.Successful_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        Integer integer = booksService.lendBook(oneBookById, account_name, lend_day);
        if(integer==null){
            R r = R.setResult("找不到这个账户", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        if(integer.equals(0)){
            R r = R.setResult("借出失败", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }


        R r = R.setResult("借出成功", MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


    /**
     * 还书
     *
     * @param book_id
     * @param account_name
     * @return
     */
    @PostMapping("/returnBook")
    public ResponseEntity returnBook(String book_id, String account_name) {

        String content = "";
        if(book_id==null||book_id.equals("")){
            content = "书的id" + drop;
        }
        if(account_name==null||account_name.equals("")){
            content = "用户名" + drop;
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        books oneBookById = booksService.getOneBookById(Integer.valueOf(book_id));
        if(ObjectUtils.isEmpty(oneBookById)){
            R r = R.setResult("不存在这本书", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        if (oneBookById.getStatus().equals(0)) {
            R r = R.setResult("该书籍未借出", MyHttpState.Successful_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        Integer integer = booksService.returnBook(oneBookById, account_name);
        if(integer==null){
            R r = R.setResult("找不到这个用户", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        if(integer.equals(0)){
            R r = R.setResult("还书失败", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        R r = R.setResult("还书成功", MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    /**
     * 查看学生借出的书
     * @param account_name
     * @return
     */
    @GetMapping("/lendBookOfUser")
    public ResponseEntity getLendBookOfUser(String account_name) {
        String content = "";
        if(account_name==null||account_name.equals("")){
            content = "学生用户不能为空";
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }


        List<books> lendBooksByUserName = booksService.getLendBooksByUserAccount(account_name);
        if(lendBooksByUserName==null){
            R r = R.setResult("找不到这个用户", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        List<booksVO> collect = lendBooksByUserName.stream().map(books -> {
            booksVO booksVO = new booksVO();
            String cateNameByBookId = categoryService.getCateNameByBookId(String.valueOf(books.getId()));
            booksVO.toBookVOByBooks(books, account_name,cateNameByBookId);
            return booksVO;
        }).collect(Collectors.toList());
        R r = R.setResult(collect, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


}
