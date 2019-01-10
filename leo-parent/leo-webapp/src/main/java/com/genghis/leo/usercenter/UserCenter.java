package com.genghis.leo.usercenter;

/**
 * Created by hao pc on 2016/4/12.
 */
import com.genghis.leo.autocomplete.service.AutoCompleteService;
import com.genghis.leo.security.entity.User;
import com.genghis.leo.security.service.UserService;
import com.genghis.steed.ajax.response.PageResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserCenter {
    @Autowired
    private UserService userService;
    //获取个人信息
    @RequestMapping(value = "/handleUserInfo", method = RequestMethod.GET)
    private User getCurrentUser(HttpSession session) {
       return (User) session.getAttribute("User");
    }
    //编辑个人信息
    @RequestMapping(value = "/editUser", method = {RequestMethod.POST})
    @ResponseBody
    public void editUser(User user,HttpSession session) {
        userService.editUser(user);
    }
    //修改密码
    @RequestMapping(value ="/handlePassword" , method = RequestMethod.GET)
    public String changePassword() {
        return "user/handlePassword";
    }
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public void updatePassword(User user) {

        userService.updatePassword(user);
    }

}
