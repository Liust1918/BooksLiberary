package com.liust.bookmanage.POJO.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liuyulong
 * @create 2022-06-06 16:17
 * @create 2022-六月  星期一
 * @project BookManage
 */
@TableName("books")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class books {

    @TableId(type = IdType.AUTO)
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
}
