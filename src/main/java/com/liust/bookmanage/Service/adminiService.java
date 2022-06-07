package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.administratorRepository;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.administrator;
import com.liust.bookmanage.Service.MyService.adminiADService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyulong
 * @create 2022-06-06 16:57
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
public class adminiService implements adminiADService {


    @Resource
    private administratorRepository administratorRepository;

    @Override
    public Integer addAdmin(administrator administrator) {
        int insert = administratorRepository.insert(administrator);
        return insert;
    }

    @Override
    public Integer updateAdmin(administrator administrator, Integer admini_id) {
        administrator.setId(admini_id);
        int i = administratorRepository.updateById(administrator);
        return i;
    }

    @Override
    public administrator getOneAdmin(String admini_name) {
        LambdaQueryWrapper<administrator> qw = new LambdaQueryWrapper<>();
        administrator administrator = administratorRepository.selectOne(qw);
        return administrator;
    }
}
