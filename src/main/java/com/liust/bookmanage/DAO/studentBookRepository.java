package com.liust.bookmanage.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DTO.studentBookDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-09 14:28
 * @create 2022-六月  星期四
 * @project BookManage
 */
public interface studentBookRepository extends BaseMapper<studentBook> {
    @Select("SELECT b.id,b.bookname,b.author,b.company,b.booknumber,b.bookid,b.picname,b.lenddate,b.returndate,b.price,s.studentname,sb.`status` FROM books as b, student_book as sb, students as s where b.id=sb.book_id and s.id=sb.student_id and s.account=#{account_name}")
    List<studentBookDTO> getAllsbDTO(String account_name);
}
