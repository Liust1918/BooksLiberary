package com.liust.bookmanage.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liust.bookmanage.POJO.DO.category;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuyulong
 * @create 2022-06-13 20:26
 * @create 2022-六月  星期一
 * @project BookManage
 */
public interface categoryRepository extends BaseMapper<category> {

    @Select("SELECT c.cate_name FROM book_cate as bc,category as c WHERE bc.cate_id=c.cate_id and bc.book_id=#{book_id}")
    String getNameByBookId(String book_id);
}
