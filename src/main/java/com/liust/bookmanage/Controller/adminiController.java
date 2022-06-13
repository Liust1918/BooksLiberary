package com.liust.bookmanage.Controller;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import com.liust.bookmanage.POJO.DO.administrator;
import com.liust.bookmanage.Service.MyService.adminiADService;
import com.liust.bookmanage.Service.adminiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liuyulong
 * @create 2022-06-06 16:53
 * @create 2022-六月  星期一
 * @project BookManage
 */
@RestController
@RequestMapping("/admin")
public class adminiController {

    @Resource
    private adminiService adminiService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(String username,String password) {

        String content = "";
        if(username==null||username.equals("")){
            content = "用户名不能为空";
        }
        if(password==null||password.equals("")){
            content = "密码不能为空";
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        administrator login = adminiService.login(username, password);
        if(login==null){
            R r = R.setResult("用户名或密码错误", MyHttpState.Login_fail);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult("登陆成功", MyHttpState.Login_Successful);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    /**
     * 管理员注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity register(String username, String password) {

        String content = "";
        if(username==null||username.equals("")){
            content = "用户名不能为空";
        }
        if(password==null||password.equals("")){
            content = "密码不能为空";
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        administrator register = adminiService.register(username, password);
        if(register==null){
            R r = R.setResult("用户名重复或注册失败", MyHttpState.Register_fail);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult(register, MyHttpState.Register_Successful);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    /**
     * 修改管理员信息
     * @param administrator
     * @return
     */
    @PatchMapping ("/updateadmin")
    public ResponseEntity updateAdmin(@RequestBody administrator administrator) {

        String username = administrator.getAccount();
        String password = administrator.getPassword();
        Integer id = administrator.getId();
        String content = "";
        if(username==null||username.equals("")){
            content = "用户名不能为空";
        }
        if(password==null||password.equals("")){
            content = "密码不能为空";
        }
        if(id==null||id.equals("")){
            content = "管理员id不能为空";
        }
        if(!content.equals("")){
            R r = R.setResult(content, MyHttpState.Drop_content);
            return new ResponseEntity(r, HttpStatus.OK);
        }

        Integer integer = adminiService.updateAdmin(administrator, administrator.getId());
        if(integer.equals(0)){
            R r = R.setResult("管理员必须包含id", MyHttpState.Update_fail);
            return new ResponseEntity(r, HttpStatus.OK);
        }
        R r = R.setResult("修改成功", MyHttpState.Successful_Run);
        return new ResponseEntity(r, HttpStatus.OK);
    }


}
