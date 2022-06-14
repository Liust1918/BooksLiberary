package com.liust.bookmanage.POJO.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liust.bookmanage.POJO.DO.books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liuyulong
 * @create 2022-06-13 21:05
 * @create 2022-六月  星期一
 * @project BookManage
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class bookAddDTO   {

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
    //借出时间戳
    private String lenddate;
    //归还时间戳
    private String returndate;
    //借阅用户(可null)
    private Integer lenduserid;
    //图书状态(0是没借出,1是已经借出)
    private Integer status;
    //图书价格
    private String price;

    private String cateName;

    public books toBooks(){
        books books = new books();
        books.setId(this.getId());
        books.setBookname(this.getBookname());
        books.setAuthor(this.getAuthor());
        books.setCompany(this.getCompany());
        books.setBooknumber(this.getBooknumber());
        books.setBookid(this.getBookid());
        books.setPicname(this.getPicname());
        books.setLenddate(this.getLenddate());
        books.setReturndate(this.getReturndate());
        books.setLenduserid(this.getLenduserid());
        books.setStatus(this.getStatus());
        books.setPrice(this.getPrice());
        return books;
    }

}
