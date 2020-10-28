package com.smartflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：tao
 * @date ：Created in 2020/10/17 21:36
 * @description：${description}
 */
@Controller
@RequestMapping("/email")
public class Email {

    @RequestMapping("/getTask")
    public String getTaskSuccess()
    {
      return "EmailSuccess";
    }
}
