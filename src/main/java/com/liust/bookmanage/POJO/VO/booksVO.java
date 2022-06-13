package com.liust.bookmanage.POJO.VO;

import com.liust.bookmanage.POJO.DO.books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * @author liuyulong
 * @create 2022-06-09 9:23
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class booksVO {

    private Integer id;
    //书名
    private String bookname;
    //作者名
    private String author;
    //出版社
    private String company;
    //书区域编号
    private String booknumber;
    //书区域编号中的号
    private String bookid;
    //图书存放路径(可能相对)
    private String picname;
    //借出时间
    private String lenddate;
    //归还时间
    private String returndate;
    //借阅用户
    private String lenduserusername;
    //图书状态(0是没借出,1是已经借出)
    private String status;
    //图书价格
    private String price;

    private String isOver;

    public void toBookVOByBooks(books books,String username ) {
        this.setId(books.getId());
        this.setBookname(books.getBookname());
        this.setAuthor(books.getAuthor());
        this.setCompany(books.getCompany());
        this.setBookid(books.getBookid());
        this.setPicname(books.getPicname());
        this.setPrice(books.getPrice());
        this.setBooknumber(books.getBooknumber());

        String lenddate = "";
        String returndate = "";
        String over = "";
        if(books.getStatus().equals(1)){
            LocalDateTime lendlocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(books.getLenddate())), ZoneOffset.of("+8"));
            LocalDateTime returnlocalDateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(books.getReturndate())), ZoneOffset.of("+8"));

            lenddate = lendlocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            returndate = returnlocalDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            over = returnlocalDateTime1.isAfter(LocalDateTime.now()) ? "未过期":"已经过期,请尽快归还" ;

        }

        String mystatus = books.getStatus().equals(0) ? "未借出" : "已借出";

        this.setLenddate(lenddate);
        this.setReturndate(returndate);
        this.setStatus(mystatus);
        this.setLenduserusername(username);
        this.setIsOver(over);
    }

}
