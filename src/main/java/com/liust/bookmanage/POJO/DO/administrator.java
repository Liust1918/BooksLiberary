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
 * @create 2022-06-06 16:14
 * @create 2022-六月  星期一
 * @project BookManage
 */
@TableName("administrator")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class administrator {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

}
