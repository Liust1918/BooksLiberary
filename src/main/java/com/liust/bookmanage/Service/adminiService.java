package com.liust.bookmanage.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liust.bookmanage.DAO.administratorRepository;
import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.administrator;
import com.liust.bookmanage.Service.MyService.adminiADService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author liuyulong
 * @create 2022-06-06 16:57
 * @create 2022-六月  星期一
 * @project BookManage
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class adminiService implements adminiADService {


    @Resource
    private administratorRepository administratorRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addAdmin(administrator administrator) {
        int insert = administratorRepository.insert(administrator);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateAdmin(administrator administrator, Integer admini_id) {
        administrator.setId(admini_id);
        int i = administratorRepository.updateById(administrator);
        return i;
    }

    @Override
    public administrator getOneAdmin(String admini_name) {
        LambdaQueryWrapper<administrator> qw = new LambdaQueryWrapper<>();
        qw.eq(administrator::getAccount, admini_name);
        administrator administrator = administratorRepository.selectOne(qw);
        return administrator;
    }


    @Override
    public administrator login(String username, String password) {
        administrator oneAdmin = getOneAdmin(username);
        if(ObjectUtils.isEmpty(oneAdmin)){
            return null;
        }
        boolean equals = oneAdmin.getPassword().equals(password);
        if(!equals){
            return null;
        }
        return oneAdmin;
    }

    @Override
    public administrator register(String username, String password) {
        administrator oneAdmin = getOneAdmin(username);
        if(!ObjectUtils.isEmpty(oneAdmin)){
            return null;
        }
        administrator administrator = new administrator();
        administrator.setAccount(username);
        administrator.setPassword(password);
        Integer integer = addAdmin(administrator);
        if(integer.equals(0)){
            return null;
        }
        return administrator;
    }
}
