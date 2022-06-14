package com.liust.bookmanage.Service;

import com.liust.bookmanage.DAO.bookCateRepository;
import com.liust.bookmanage.POJO.DO.bookCate;
import com.liust.bookmanage.POJO.DTO.bookcateDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-13 20:27
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
public class bookCateService {

    @Resource
    private bookCateRepository bookCateRepository;

    public Integer addbookCate(String book_id,String cate_id){
        bookCate bookCate = new bookCate();
        bookCate.setBookId(Long.valueOf(book_id));
        bookCate.setCateId(Long.valueOf(cate_id));
        int insert = bookCateRepository.insert(bookCate);
        return insert;
    }

    public List getBookCateDTO(String cate_name){
        List<bookcateDTO> dto = bookCateRepository.getBookCateDTOByCateName(cate_name);
        return dto;
    }

}
