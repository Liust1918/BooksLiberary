package com.liust.bookmanage.Controller;

import com.liust.bookmanage.Service.fileSerivce;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author liuyulong
 * @create 2022-06-09 13:49
 * @create 2022-六月  星期四
 * @project BookManage
 */
@RestController
@RequestMapping("/upload")
public class uploadController {

    @Resource
    private fileSerivce service;

    @PostMapping("/file")
    public ResponseEntity uploadVideo(@RequestParam(name = "data", required = false) MultipartFile file) throws IOException {
        return service.upload(file);
    }

}
