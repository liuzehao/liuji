package com.genghis.leo.demotion;

import com.genghis.leo.demotion.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dabai on 2016/10/7.
 */
@Controller
public class DeleteController {

    @Autowired
    private StuService stuService;

    //删除学生信息
    @RequestMapping("/deleteStuInfoPage")
    public String deleteStuInfoPage(){
        return "admin/delete/deleteStuInfoPage";
    }

    @RequestMapping("/deleteStuInfo")
    @ResponseBody
    public String deleteStuInfo(@RequestParam("level") String level){
        stuService.deleteStuInfo(level.substring(2, 4));
        return "{\"msg\":\"success\"}";
    }

    //删除学生成绩
    @RequestMapping("/deleteStuGradePage")
    public String deleteStuGradePage(){
        return "admin/delete/deleteStuGradePage";
    }

    @RequestMapping("/deleteStuGrade")
    @ResponseBody
    public String deleteStuGrade(@RequestParam("level") String semester){

        stuService.deleteStuGrade(semester);
        return "{\"msg\":\"success\"}";
    }

    //删除培养计划
    @RequestMapping("/deleteStuPlanPage")
    public String deleteStuPlanPage(){
        return "admin/delete/deleteStuPlanPage";
    }

    @RequestMapping("/deleteStuPlan")
    @ResponseBody
    public String deleteStuPlan(@RequestParam("level") String semester){

        stuService.deleteStuPlan(semester);
        System.out.println("deleteStuPlan "+semester);
        return "{\"msg\":\"success\"}";
    }

    //删除留级记录
    @RequestMapping("/deleteStuDegradePage")
    public String deleteStuDegradePage(){
        return "admin/delete/deleteStuDegradePage";
    }

    @RequestMapping("/deleteStuDegrade")
    @ResponseBody
    public String deleteStuDegrade(@RequestParam("level") String level){

        stuService.deleteStuWrong(level.substring(2, 4));
        System.out.println("deleteStuDegrade "+level.substring(2, 4));
        return "{\"msg\":\"success\"}";
    }
}
