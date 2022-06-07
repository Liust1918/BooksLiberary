package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.studentRepository;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.Service.MyService.sutdentsADService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author liuyulong
 * @create 2022-06-06 16:58
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
public class studentsService implements sutdentsADService {


    @Resource
    private studentRepository studentRepository;

    @Override
    public Integer addStudent(students students) {
        students.setSchooldata(String.valueOf(new Date().getTime()));
        students.setStatus("1");
        int insert = studentRepository.insert(students);
        return insert;
    }

    @Override
    public Integer deleteStudentByName(String username) {
        students oneStudent = getOneStudent(username);
        int i = studentRepository.deleteById(oneStudent.getId());
        return i;
    }

    @Override
    public Integer deleteStudentById(Integer user_id) {
        int i = studentRepository.deleteById(user_id);
        return i;
    }

    @Override
    public Integer updataStudent(Integer user_id, students students) {
        students.setId(user_id);
        int i = studentRepository.updateById(students);
        return i;
    }

    @Override
    public students getOneStudent(String name) {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        students students = studentRepository.selectOne(qw);
        return students;
    }

    @Override
    public List<students> getAllStudents() {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        List<students> students = studentRepository.selectList(qw);
        return students;
    }
}
