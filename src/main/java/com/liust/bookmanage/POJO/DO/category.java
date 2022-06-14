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
 * @create 2022-06-13 20:23
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("category")
public class category {

    @TableId(type = IdType.AUTO)
    private Long cateId;

    private String cateName;
}
