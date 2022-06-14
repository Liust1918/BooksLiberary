package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.studentRepository;
import com.liust.bookmanage.POJO.DO.students;
import com.liust.bookmanage.Service.MyService.sutdentsADService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class studentsService implements sutdentsADService {


    @Resource
    private studentRepository studentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addStudent(students students) {
        LocalDateTime now = LocalDateTime.now();
        long time = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        students.setSchooldate(String.valueOf(time));
        students.setStatus("0");
        int insert = studentRepository.insert(students);
        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteStudentByName(String username) {
        students oneStudent = getOneStudentByName(username);
        int i = studentRepository.deleteById(oneStudent.getId());
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteStudentById(Integer user_id) {
        int i = studentRepository.deleteById(user_id);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updataStudent(Integer user_id, students students) {
        students.setId(user_id);
        int i = studentRepository.updateById(students);
        return i;
    }

    @Override
    public students getOneStudentByName(String name) {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        qw.eq(students::getStudentname, name);
        students students = studentRepository.selectOne(qw);
        return students;
    }

    @Override
    public students  getOneStudentByID(String student_id) {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        qw.eq(students::getId, student_id);
        students students = studentRepository.selectOne(qw);
        return students;
    }

    @Override
    public students getOneStudentByAccount(String account) {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        qw.eq(students::getAccount, account);
        students students = studentRepository.selectOne(qw);
        return students;
    }

    @Override
    public List<students> getAllStudents() {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        List<students> students = studentRepository.selectList(qw);
        return students;
    }


    public Boolean studentLogin(String account,String password){
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        qw.eq(students::getAccount, account);
        students students = studentRepository.selectOne(qw);
        if (ObjectUtils.isEmpty(students)) {
            return null;
        }
        if(students.getPassword().equals(password)){
            return true;
        }
        return false;
    }


    public Integer studentDatePassword(String account, String password) {
        LambdaQueryWrapper<students> qw = new LambdaQueryWrapper<>();
        qw.eq(students::getAccount, account);
        students students = studentRepository.selectOne(qw);
        if (ObjectUtils.isEmpty(students)) {
            return null;
        }
        students.setPassword(password);
        int i = studentRepository.updateById(students);
        return i;
    }


}
