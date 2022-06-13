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
 * @create 2022-06-06 16:33
 * @create 2022-六月  星期一
 * @project BookManage
 */
@TableName("students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class students {


    @TableId(type = IdType.AUTO)
    private Integer id;

    //用户名
    private String account;

    //用户密码
    private String password;

    //用户名
    private String studentname;

    //用户班级
    private String studentclass;

    //用户加入时间(时间戳)
    private String schooldate;

    //用户性别
    private String gender;

    //账号状态
    private String status;




}
