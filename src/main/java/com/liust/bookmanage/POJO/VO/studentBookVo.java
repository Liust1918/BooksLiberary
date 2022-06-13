package com.liust.bookmanage.POJO.VO;

import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.DTO.studentBookDTO;
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
 * @create 2022-06-09 14:52
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class studentBookVo {

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
    //图书价格
    private String price;

    private String studentname;

    private String status;


    public void studentBookDTOtostudentBookVo(studentBookDTO studentBookDTO) {

        String lenddate = studentBookDTO.getLenddate();
        String returndate = studentBookDTO.getReturndate();

        LocalDateTime lendlocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(lenddate)), ZoneOffset.of("+8"));
        String lenddateformat = lendlocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.setLenddate(lenddateformat);

        LocalDateTime returndatelocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(returndate)), ZoneOffset.of("+8"));
        String returndatedateformat = returndatelocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.setReturndate(returndatedateformat);

        this.setStatus(studentBookDTO.getStatus().equals("0")?"未归还":"已归还");

        this.setId(studentBookDTO.getId());
        this.setBookname(studentBookDTO.getBookname());
        this.setAuthor(studentBookDTO.getAuthor());
        this.setCompany(studentBookDTO.getCompany());
        this.setBooknumber(studentBookDTO.getBooknumber());
        this.setBookid(studentBookDTO.getBookid());
        this.setPicname(studentBookDTO.getPicname());
        this.setPrice(studentBookDTO.getPrice());
        this.setStudentname(studentBookDTO.getStudentname());
    }


}
