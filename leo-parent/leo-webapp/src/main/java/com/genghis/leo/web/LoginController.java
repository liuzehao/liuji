/*
* LoginController.java
* Created on  2013-10-6 下午12:35
* 版本       修改时间          作者      修改内容
* V1.0.1    2013-10-6       gaoxinyu    初始版本
*
*/
package com.genghis.leo.web;

import com.genghis.leo.security.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.genghis.leo.security.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author gaoxinyu
 * @version 1.0.1
 */
@Controller
@RequestMapping()
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(HttpServletRequest request) {
        return "login";
    }
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String loginPost(User user,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword(),user.isRememberMe());
        User user1 = userService.findUserByLoginName(user.getLoginName());
        session.setAttribute("User",user1);
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/login";
        }
        if(currentUser.isAuthenticated()){
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/login";
        }
    }

}
