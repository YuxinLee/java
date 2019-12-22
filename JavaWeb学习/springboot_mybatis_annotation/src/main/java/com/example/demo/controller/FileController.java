package com.example.demo.controller;

import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
//@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }


    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam("excel") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "文件不能为空";
        }

        InputStream inputStream = file.getInputStream();
        List<List<Object>> lists = fileService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        for (int i = 0; i < lists.size(); i++) {
            List<Object> lo = lists.get(i);

            System.out.println(lo);

        }
        return "上传成功";
    }
}
