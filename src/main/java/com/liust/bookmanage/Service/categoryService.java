package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.categoryRepository;
import com.liust.bookmanage.POJO.DO.category;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-13 20:27
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
public class categoryService {

    @Resource
    private categoryRepository categoryRepository;

    public Integer addCategory(String name) {
        category category = new category();
        category.setCateName(name);
        int insert = categoryRepository.insert(category);
        return insert;
    }

    public Integer updateCategory(category category){
        int i = categoryRepository.updateById(category);
        return i;
    }

    public Integer deleteCategory(String name){
        LambdaQueryWrapper<category> qw= new LambdaQueryWrapper<>();
        qw.eq(category::getCateName, name);
        int delete = categoryRepository.delete(qw);
        return delete;
    }

    public List getAll(){
        LambdaQueryWrapper<category> qw = new LambdaQueryWrapper<>();
        List<category> categories = categoryRepository.selectList(qw);
        return categories;
    }

    public category getOneById(String id){
        LambdaQueryWrapper<category> qw = new LambdaQueryWrapper<>();
        qw.eq(category::getCateId, id);
        category category = categoryRepository.selectOne(qw);
        return category;
    }
    public category getOneByName(String name){
        LambdaQueryWrapper<category> qw = new LambdaQueryWrapper<>();
        qw.eq(category::getCateName, name);
        category category = categoryRepository.selectOne(qw);
        return category;
    }

    public String getCateNameByBookId(String book_id){
        String nameByBookId = categoryRepository.getNameByBookId(book_id);
        return nameByBookId;
    }

}
