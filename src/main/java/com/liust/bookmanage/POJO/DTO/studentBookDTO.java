package com.liust.bookmanage.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liuyulong
 * @create 2022-06-09 15:12
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class studentBookDTO {

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

}
