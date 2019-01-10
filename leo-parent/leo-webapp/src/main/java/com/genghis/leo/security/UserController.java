package com.genghis.leo.security;

import com.genghis.leo.autocomplete.service.AutoCompleteService;
import com.genghis.leo.security.entity.User;
import com.genghis.leo.security.service.UserService;
import com.genghis.steed.ajax.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by panzhuowen on 2014/10/17.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AutoCompleteService autoCompleteService;

    @RequestMapping(value = "/admin/user/getAllUserDo", method = {RequestMethod.GET})
    public String getAllUserDo() {
        return "admin/user/listAllUserDo";
    }

    @RequestMapping(value = "/admin/user/addUserDo", method = {RequestMethod.GET})
    public String addUserDo(HttpServletRequest request) {
        autoCompleteService.initAutoComplete();
        return "admin/user/addUserDo";
    }

    @RequestMapping(value = "/admin/user/findAllUser", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<User> findAllUser(User user) {
        return new PageResponse<>(user.getPage(), userService.getUserListPage(user));
    }

    @RequestMapping(value = "/admin/user/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public void addUser(User user) {
        userService.addUserDo(user);
    }

    @RequestMapping(value = "/admin/user/delUserDo", method = {RequestMethod.DELETE})
    @ResponseBody
    public void delUser(@RequestParam("id") int id) {
        userService.delUser(id);
    }
//替换的
    @RequestMapping(value = "/admin/user/replaceCourse", method = {RequestMethod.GET})
    public String editUserDo(Model model, HttpServletRequest request, @RequestParam("id") int sno, @RequestParam("courseName") String courseName, String name, String totalGrade, float credit, String cno) throws UnsupportedEncodingException {

        model.addAttribute("sno", sno);
        model.addAttribute("courseName", new String(courseName.getBytes("ISO-8859-1"), "UTF-8"));
        model.addAttribute("name", new String(name.getBytes("ISO-8859-1"), "UTF-8"));
        model.addAttribute("totalGrade", new String(totalGrade.getBytes("ISO-8859-1"), "UTF-8"));
        model.addAttribute("oldCredit", credit);
        model.addAttribute("cno", cno);
        return "admin/user/replaceCourse";
    }
    //详情
    @RequestMapping(value = "/admin/user/conflictCourse", method = {RequestMethod.GET})
    public String detailUserDo(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("name") String name, Model model) throws UnsupportedEncodingException {

        model.addAttribute("id", id);
        model.addAttribute("name", new String(name.getBytes("ISO-8859-1"), "UTF-8"));
        return "admin/user/conflictCourse";
    }

    @RequestMapping(value = "/stuwrong/searchStu", method = {RequestMethod.POST})
    @ResponseBody
    public void searchStudentWrong() {
        System.out.println("dd");
        //return stuWrongService.searchStuWrongListPage(attribute,value);
    }

    @RequestMapping(value = "/admin/user/editUser", method = {RequestMethod.POST})
    @ResponseBody
    public void editUser(User user) {
        userService.editUserRole(user);
        userService.editUser(user);
    }

    @RequestMapping(value = "/admin/user/validateUserName", method = {RequestMethod.POST})
    @ResponseBody
    public boolean checkRepeatLoginName(User user) {
        int hasRepeatLoginName = userService.hasCheckRepeatLoginName(user.getLoginName());
        if (user.getUserId() == 0) { //新增
            if (hasRepeatLoginName >= 1) {
                return false;
            }
        } else {  // 编辑
            int checkUser = userService.checkRepeatUser(user);
            if (hasRepeatLoginName == 1 && checkUser == 0) {
                return false;
            }
        }
        return true;
    }
}
