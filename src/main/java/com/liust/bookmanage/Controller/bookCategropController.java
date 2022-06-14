package com.liust.bookmanage.Controller;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.category;
import com.liust.bookmanage.POJO.DTO.bookcateDTO;
import com.liust.bookmanage.Service.bookCateService;
import com.liust.bookmanage.Service.categoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.RowSetEvent;
import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-13 20:37
 * @create 2022-六月  星期一
 * @project BookManage
 */
@RestController
@RequestMapping("/cate")
public class bookCategropController {

    @Resource
    private bookCateService bookCateService;

    @Resource
    private categoryService categoryService;

    private String drop = "不存在";

    @PostMapping("/add")
    public ResponseEntity addCate(String name) {
        String content = "";
        if(name==null||name.equals("")){
            content = "分类名" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        category oneByName = categoryService.getOneByName(name);
        if(!ObjectUtils.isEmpty(oneByName)){
            R r = R.setResult("该名字已存在", MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }

        Integer integer = categoryService.addCategory(name);
        if(integer.equals(0)){
            content = "添加失败";
            R r = R.setResult(content, MyHttpState.Fail_Run);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        content = "添加成功";
        R r = R.setResult(content, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletCate(String name) {
        String content = "";
        if(name==null||name.equals("")){
            content = "分类名" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        Integer integer = categoryService.deleteCategory(name);
        if(integer.equals(0)){
            content = "删除失败";
            R r = R.setResult(content, MyHttpState.Fail_Run);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        content = "删除成功";
        R r = R.setResult(content, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @PatchMapping("/date")
    public ResponseEntity dateCate(@RequestBody category category) {
        String content = "";
        if(ObjectUtils.isEmpty(category)){
            content = "对象"+drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        if(category.getCateName()==null||category.getCateName().equals("")){
            content = "名字" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }

        category oneById = categoryService.getOneById(String.valueOf(category.getCateId()));
        if(ObjectUtils.isEmpty(oneById)){
            R r = R.setResult("不存在", MyHttpState.Fail_Run);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        if(oneById.getCateName().equals(category.getCateName())){
            R r = R.setResult("名字重复存在", MyHttpState.Fail_Run);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }

        Integer integer = categoryService.updateCategory(category);
        if(integer.equals(0)){
            R r = R.setResult("修改失败", MyHttpState.Update_fail);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        R r = R.setResult("修改成功", MyHttpState.Successful_Run);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }


    @GetMapping("/getall")
    public ResponseEntity getAllCate() {
        List all = categoryService.getAll();
        R r = R.setResult(all, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @GetMapping("/getAllBookByCate")
    public ResponseEntity getAllBookByCate(String cate_name){
        String content = "";
        if(cate_name==null||cate_name.equals("")){
            content = "分类名" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        category oneByName = categoryService.getOneByName(cate_name);
        if(ObjectUtils.isEmpty(oneByName)){
            content = "不存在类型名";
            R r = R.setResult(content, MyHttpState.Fail_Run);
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        List<bookcateDTO> bookCateDTO = bookCateService.getBookCateDTO(cate_name);
        bookCateDTO.stream().forEach(bookcateDTO -> {
            bookcateDTO.switchVO();
        });
        R r = R.setResult(bookCateDTO, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


}
