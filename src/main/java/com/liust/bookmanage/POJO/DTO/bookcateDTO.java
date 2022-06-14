package com.liust.bookmanage.POJO.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @create 2022-06-13 23:25
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class bookcateDTO {


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
    //图书状态(0是没借出,1是已经借出)
    private String status;
    //借阅用户(可null)
    private String username;
    //图书价格
    private String price;
    //  图书类型
    private String cateName;

    private String isOver;

    public void switchVO(){
        String lenddate = "";
        String returndate = "";
        String over = "";
        if(this.getStatus().equals("1")){
            LocalDateTime lendlocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(this.getLenddate())), ZoneOffset.of("+8"));
            LocalDateTime returnlocalDateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(this.getReturndate())), ZoneOffset.of("+8"));
            lenddate = lendlocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            returndate = returnlocalDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            over = returnlocalDateTime1.isAfter(LocalDateTime.now()) ? "未过期":"已经过期,请尽快归还" ;
        }

        if(this.getUsername()==null){
            this.setUsername("");
        }

        String mystatus = this.getStatus().equals("0") ? "未借出" : "已借出";
        this.setLenddate(lenddate);
        this.setReturndate(returndate);
        this.setStatus(mystatus);
        this.setIsOver(over);

    }

}
