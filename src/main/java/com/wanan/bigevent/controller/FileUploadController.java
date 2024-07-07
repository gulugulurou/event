package com.wanan.bigevent.controller;

import com.wanan.bigevent.pojo.Result;
import com.wanan.bigevent.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        String url = AliOssUtil.uploadFile(file.getOriginalFilename(), file.getInputStream());
        return Result.success(url);
    }

}
