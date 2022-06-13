package com.liust.bookmanage.Service.MyService;

import com.liust.bookmanage.POJO.DO.administrator;
import com.liust.bookmanage.POJO.DO.students;

import java.util.List;

/**
 * @author liuyulong
 * @create 2022-06-06 17:01
 * @create 2022-六月  星期一
 * @project BookManage
 */
public interface adminiADService {

    Integer addAdmin(administrator administrator);

    Integer updateAdmin(administrator administrator, Integer admini_id);

    administrator getOneAdmin(String admini_name);

    administrator login(String username,String password);

    administrator register(String username, String password);

}
