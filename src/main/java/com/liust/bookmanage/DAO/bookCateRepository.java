package com.liust.bookmanage.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liust.bookmanage.POJO.DO.bookCate;
import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.DO.category;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.DTO.bookcateDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-13 20:26
 * @create 2022-六月  星期一
 * @project BookManage
 */
public interface bookCateRepository extends BaseMapper<bookCate> {

    @Select("SELECT  b.id,b.bookname,b.author,b.company,b.booknumber,b.bookid,b.picname,b.lenddate,b.returndate,b.`status`,b.price,(SELECT s.studentname FROM students as s WHERE s.id=b.lenduserid) as username,c.cate_name FROM  books as b,category as c,book_cate as bc where  bc.book_id=b.id and bc.cate_id=c.cate_id and c.cate_name=#{cate_name}")
    List<bookcateDTO> getBookCateDTOByCateName(String cate_name);
}
