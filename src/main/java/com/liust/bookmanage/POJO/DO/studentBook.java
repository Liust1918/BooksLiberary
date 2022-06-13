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
 * @create 2022-06-09 14:26
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("student_book")
public class studentBook {

    @TableId(type = IdType.AUTO)
    private Integer sbId;
//    借阅者id
    private Integer studentId;
//    书籍id
    private Integer bookId;
//    借阅时间(时间戳)
    private String lendTime;
//    归还时间(时间戳)
    private String returnTime;
//    归还情况(0为未归还,1未归还)
    private Integer status;
}
