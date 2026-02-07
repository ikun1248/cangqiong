package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/admin")
public class UploadController {

    @Autowired
    AliOssUtil aliOssUtil;

    @PostMapping("common/upload")
    public Result upload(MultipartFile file) throws Exception {

        log.info("文件上传：{}",file);

        String originalFilename = file.getOriginalFilename();
        String url = aliOssUtil.upload(file.getBytes(), originalFilename);

        return Result.success(url);
    }
}
