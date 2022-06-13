package com.liust.bookmanage.Controller;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.studentBook;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.DTO.studentBookDTO;
import com.liust.bookmanage.POJO.VO.studentBookVo;
import com.liust.bookmanage.Service.studentBookService;
import com.liust.bookmanage.Service.studentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyulong
 * @create 2022-06-09 14:50
 * @create 2022-六月  星期四
 * @project BookManage
 */
@RestController
@RequestMapping("/sb")
public class studentBookeController {

    @Resource
    private studentBookService studentBookService;

    @Resource
    private studentsService studentsService;

    private String drop = "不能为空";

    @GetMapping("/getAll")
    public ResponseEntity getAll(String account_name) {
        String content = "";
        if(account_name==null||account_name.equals("")){
            content = "用户名" + drop;
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        students oneStudentByAccount = studentsService.getOneStudentByAccount(account_name);
        if(ObjectUtils.isEmpty(oneStudentByAccount)){
            R r = R.setResult("不存在此用户", MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        List<studentBookDTO> allStudentBooks = studentBookService.getAllStudentBooks(account_name);

        List<studentBookVo> collect = allStudentBooks.stream().map(studentBookDTO -> {
            studentBookVo studentBookVo = new studentBookVo();
            studentBookVo.studentBookDTOtostudentBookVo(studentBookDTO);
            return studentBookVo;
        }).collect(Collectors.toList());

        R r = R.setResult(collect, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

}
