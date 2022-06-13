package com.liust.bookmanage.POJO.VO;

import com.liust.bookmanage.POJO.DO.books;
import com.liust.bookmanage.POJO.DO.students;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author liuyulong
 * @create 2022-06-09 13:20
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class studentsVO {

    private Integer id;

    //用户名
    private String account;

    //用户密码
    private String password;

    //用户名
    private String studentname;

    //用户班级
    private String studentclass;

    //用户加入时间(时间戳)
    private String schooldate;

    //用户性别
    private String gender;

    public void toStudentVOFormStudents(students students){
        this.setId(students.getId());
        this.setAccount(students.getAccount());
        this.setGender(students.getGender());
        this.setStudentclass(students.getStudentclass());
        this.setPassword(students.getPassword());
        this.setStudentname(students.getStudentname());
        String schooldate = students.getSchooldate();
        LocalDateTime lendlocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(schooldate)), ZoneOffset.of("+8"));
        String format = lendlocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.setSchooldate(format);

    }
}
