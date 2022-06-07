package com.liust.bookmanage.Service.MyService;

import com.liust.bookmanage.POJO.DO.students;

import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-06 17:02
 * @create 2022-六月  星期一
 * @project BookManage
 */
public interface sutdentsADService {

    Integer addStudent(students students);

    Integer deleteStudentByName(String username);

    Integer deleteStudentById(Integer user_id);

    Integer updataStudent(Integer user_id, students students);

    students getOneStudent(String name);

    List<students> getAllStudents();
}
