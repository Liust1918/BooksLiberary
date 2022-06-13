package com.liust.bookmanage.Service;

import com.liust.bookmanage.MyStatusCode.MyHttpState;
import com.liust.bookmanage.MyStatusCode.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author liuyulong
 * @create 2022-06-09 13:43
 * @create 2022-六月  星期四
 * @project BookManage
 */
@Service
public class fileSerivce {

    @Value("${file.path}")
    private String StaticPath;

    public ResponseEntity upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            R result = R.setResult("请输入数据", MyHttpState.Drop_content);
            return new ResponseEntity(result, HttpStatus.OK);
        }

        // 文件上传
        String imageName = file.getOriginalFilename();
        String newCompanyImagePath = "";
        String newFileName = "";
        Integer which_id = null;
        String ext = "";
        if (!file.isEmpty()) {
            try {
                newCompanyImagePath = "/static" ;

                // 获取文件后缀
                ext = "." + imageName.substring(imageName.lastIndexOf(".") + 1);
                // 生成新的文件名
                newFileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;

                File newFile = new File(StaticPath + newCompanyImagePath, newFileName);

                if (!newFile.exists()) {
                    newFile.createNewFile();
//                    which_id = uploadDB(newCompanyImagePath + "/" + newFileName, ext);
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ResponseEntity("图片上传失败！:" + e.getMessage(), HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity("图片上传失败！:" + e.getMessage(), HttpStatus.OK);
            }
        }
        String imagePath = newCompanyImagePath + "/" + newFileName;
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("ResourcePath", imagePath);
        objectObjectHashMap.put("type", ext);
        R result = R.setResult(objectObjectHashMap, MyHttpState.Successful_Run);
        return new ResponseEntity(result, HttpStatus.OK);
    }






}
