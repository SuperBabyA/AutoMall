package com.itcase.automall.controller;

import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

  @Value("${automall.basePath}")
  private String basePath;

  @PostMapping("/upload")
  public HttpResult upload(MultipartFile file) {

    // 获取文件原文件名
    String originalFilename = file.getOriginalFilename();
    // 截取文件后缀名
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

    String fileName = UUID.randomUUID().toString() + suffix;

    File dir = new File(basePath);
    // 如果文件夹不存在则创建文件夹
    if (!dir.exists()) {
      dir.mkdir();
    }

    try {
      // 将文件转存在指定位置
      log.info("basePath: {}", basePath);
      file.transferTo(new File(basePath + fileName));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // 将文件名返回出去
    return new HttpResult("200", "上传成功", fileName);

  }

  /*
  * /download/{filename:.+}
  * 正则表达式写法，让Rest风格url可以接受’.‘之后的参数
  * */
  @GetMapping("/download/{filename:.+}")
  public void download(@PathVariable("filename") String name, HttpServletResponse response) {
    log.info("filename: {}", name);
    try {
      FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

      int len = 0;
      byte[] bytes = new byte[1024];
      ServletOutputStream outputStream = response.getOutputStream();

      while ((len = fileInputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, len);
        outputStream.flush();
      }

      // 释放资源
      fileInputStream.close();
      outputStream.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
