package com.itcase.automall.controller;

import com.itcase.automall.utils.encryption.MD5Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TempController {

  @GetMapping("/md5/{str}")
  public String md5Password(@PathVariable("str") String str) {
    return MD5Util.inputPassToFormPass(str);
  }
}
