package com.liust.bookmanage.Controller;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.POJO.VO.studentsVO;
import com.liust.bookmanage.Service.studentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyulong
 * @create 2022-06-06 16:53
 * @create 2022-六月  星期一
 * @project BookManage
 */
@RestController
@RequestMapping("/students")
public class studentsController {

    @Resource
    private studentsService studentsService;

    private String drop = "不能为空";

    /**
     * 添加学生
     *
     * @param students
     * @return
     */
    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody students students) {

        String content = "";
        String account = students.getAccount();
        String password = students.getPassword();
        String studentname = students.getStudentname();
        String studentclass = students.getStudentclass();
        String gender = students.getGender();
        if (account == null || account.equals("")) {
            content = "账户" + drop;
        }
        if(password==null|| password.equals("")){
            content = "密码" + drop;
        }
        if (studentname == null || studentclass.equals("")) {
            content = "学生名" + drop;
        }
        if (studentclass == null || studentclass.equals("")) {
            content = "学生班级" + drop;
        }
        if (gender == null || gender.equals("")) {
            content = "性别" + drop;
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        students oneStudentByAccount = studentsService.getOneStudentByAccount(account);
        if (!ObjectUtils.isEmpty(oneStudentByAccount)) {
            R r = R.setResult("用户名已经存在", MyHttpState.Error_Content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        Integer integer = studentsService.addStudent(students);
        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult(integer, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


    /**
     * 修改学生信息
     * @param students
     * @return
     */
    @PatchMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestBody students students) {
        String content = "";
        Integer id = students.getId();
        String account = students.getAccount();
        String password = students.getPassword();
        String studentname = students.getStudentname();
        String studentclass = students.getStudentclass();
        String gender = students.getGender();
        if(id==null||id.equals(0)){
            content = "用户id" + drop;
        }
//        if (account == null || account.equals("")) {
//            content = "账户" + drop;
//        }
        if(password==null|| password.equals("")){
            content = "密码" + drop;
        }
        if (studentname == null || studentclass.equals("")) {
            content = "学生名" + drop;
        }
        if (studentclass == null || studentclass.equals("")) {
            content = "学生班级" + drop;
        }
        if (gender == null || gender.equals("")) {
            content = "性别" + drop;
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        /*students oneStudentByAccount = studentsService.getOneStudentByAccount(account);
        if (!ObjectUtils.isEmpty(oneStudentByAccount)) {
            R r = R.setResult("用户名已经存在", MyHttpState.Error_Content);
            return new ResponseEntity(r, HttpStatus.OK);
        }*/

        students oneStudentByID = studentsService.getOneStudentByID(String.valueOf(id));
        if(ObjectUtils.isEmpty(oneStudentByID)){
            R r = R.setResult("用户为空", MyHttpState.Update_fail);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        students.setSchooldate(oneStudentByID.getSchooldate());
        students.setStatus(oneStudentByID.getStatus());
        Integer integer = studentsService.updataStudent(id, students);

        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult(integer, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


    /**
     * 删除学生
     *
     * @param student_id
     * @return
     */
    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudentByName(String student_id) {

        String content = "";
        if (student_id == null || student_id.equals("")) {
            content = "学生id" + drop;
        }
        if (!content.equals("")) {
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        Integer integer = studentsService.deleteStudentById(Integer.valueOf(student_id));
        if(integer.equals(0)){
            R r = R.setResult(integer, MyHttpState.Fail_Run);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult(integer, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    /**
     * 查询所有学生
     *
     * @return
     */
    @GetMapping("/getAllStudent")
    public ResponseEntity getAllStudent() {
        List<students> allStudents = studentsService.getAllStudents();
        List<studentsVO> collect = allStudents.stream().map(students -> {
            studentsVO studentsVO = new studentsVO();
            studentsVO.toStudentVOFormStudents(students);
            return studentsVO;
        }).collect(Collectors.toList());
        R r = R.setResult(collect, MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


}
