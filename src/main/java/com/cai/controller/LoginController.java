package com.cai.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        //具体的业务；
        if(!StringUtils.isEmpty(username) && "123456".equals(password) && "wyc".equals(username)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //告诉用户登录失败了
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }

    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("loginUser");
        return "index";
    }

}
