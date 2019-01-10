package com.genghis.leo.demotion;

import com.genghis.leo.demotion.model.StuGrade;
import com.genghis.leo.demotion.model.StuInfo;
import com.genghis.leo.demotion.model.StuWrong;
import com.genghis.leo.demotion.service.StuService;
import com.genghis.steed.ajax.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hao pc on 2016/9/23.
 */
@Controller
@RequestMapping("/stuwrong")
public class StuwrongController {

    //一学年的不及格必修课学分大于等于18分则降级
    private static final int ONE_YEAR_WRONG_LINE = 18;
    private static final int ALL_YEAR_WRONG_LINE = 28;
    private static final int CURR_YEAR = getCurrYear();


    @Autowired
    private StuService stuService;

    @RequestMapping(value = "getAllstuwrong", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<StuWrong> findAllStuWrong(StuWrong stuwrong) {
        return new PageResponse<>(stuwrong.getPage(), stuService.getUserListPage(stuwrong));
    }

    @RequestMapping(value = "findStuConflictCourse", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<StuGrade> findStuConflictCourse(StuWrong stuwrong, String sno) {
        List<StuGrade> StuConfi= stuService.getStuConflictCourse(sno, null);
        return new PageResponse<>(stuwrong.getPage(), StuConfi);
    }

    /**
     * 将原来没有加入的课程重新加入进行计算
     */
    @RequestMapping("/addCourse")
    public String addCourse(String sno, String name, String courseName) throws UnsupportedEncodingException {
        courseName = new String(courseName.getBytes("ISO-8859-1"), "UTF-8");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        StuGrade conflictCourse = stuService.getStuConflictCourse(sno, courseName).get(0);
        //计算这门课属于grade几的课程
        String totalGrade = conflictCourse.getTotalGrade();
        StuWrong stuWrong = stuService.getOneStuWrong(sno);
        if (ifInteger(totalGrade) && Integer.valueOf(totalGrade) < 60) {

            switch (Integer.valueOf(conflictCourse.getSemester().substring(0, 4))
                    - Integer.valueOf(sno.substring(0, 4))) {
                case 0:
                    if (stuWrong.getGrade1() != -1) {
                        stuWrong.setGrade1(stuWrong.getGrade1() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade1(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);

                    break;
                case 1:
                    if (stuWrong.getGrade2() != -1) {
                        stuWrong.setGrade2(stuWrong.getGrade2() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade2(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    break;
                case 2:
                    if (stuWrong.getGrade3() != -1) {
                        stuWrong.setGrade3(stuWrong.getGrade3() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade3(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    break;
                case 3:
                    if (stuWrong.getGrade4() != -1) {
                        stuWrong.setGrade4(stuWrong.getGrade4() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade4(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    break;
                case 4:
                    if (stuWrong.getGrade5() != -1) {
                        stuWrong.setGrade5(stuWrong.getGrade5() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade5(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    break;
                case 5:
                    if (stuWrong.getGrade6() != -1) {
                        stuWrong.setGrade6(stuWrong.getGrade6() + conflictCourse.getCredit());
                    } else {
                        stuWrong.setGrade6(conflictCourse.getCredit());
                    }
                    if (stuWrong.isValidGrade1() && stuWrong.getGrade1() >= ONE_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    if (stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE)
                        stuWrong.setDegrade(true);
                    break;
            }


        }
        stuService.deleteConflictCourse(sno, courseName);
        List<StuGrade> allConflictCourses = stuService.getStuConflictCourse(sno, null);

        if (allConflictCourses == null || allConflictCourses.size() == 0)
            stuWrong.setPeohandle(false);
        stuService.updateOneStuWrong(stuWrong);

        System.out.println(sno+" "+courseName+" "+name);
        return "redirect:/admin/user/conflictCourse?id="+sno+"&name="+name;
    }

    /**
     * 忽略课程
     * @param sno
     * @param name
     * @param courseName
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/ignore")
    public String ignore(String sno, String name, String courseName) throws UnsupportedEncodingException {
        courseName = new String(courseName.getBytes("ISO-8859-1"), "UTF-8");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");


        stuService.deleteConflictCourse(sno, courseName);
        List<StuGrade> allConflictCourses = stuService.getStuConflictCourse(sno, null);

        if (allConflictCourses == null || allConflictCourses.size() == 0){
            StuWrong stuWrong = stuService.getOneStuWrong(sno);
            stuWrong.setPeohandle(false);
            stuService.updateOneStuWrong(stuWrong);
        }
        return "redirect:/admin/user/conflictCourse?id="+sno+"&name="+name;
    }

    @RequestMapping("/replaceItem")
    @ResponseBody
    public List<StuGrade> replaceItem(String sno, String courseName, String cno) throws UnsupportedEncodingException {
        courseName = new String(courseName.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(sno+"  "+courseName);
        String cutCName = isFollowCharNum(courseName);
        List<StuGrade> list;
//        List<String> data = new ArrayList<String>();
        if (cutCName != null){
            System.out.println(cutCName);
            list = stuService.getStuGradeWithCName(sno, cno, cutCName, courseName);

            if (list != null && list.size() != 0){
                for (StuGrade stuGrade : list){
                    System.out.println(stuGrade.getCourseName());
                }
                return list;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }


    /**
     * 替代 用当前课程替代所选择的课
     * @param hasGrade
     * @param sno
     * @param name
     * @param courseName
     * @param totalGrade
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/replace")
    @ResponseBody
    public String replace(String semester, String hasGrade, String sno, String name, String courseName, String oldCourseName, String totalGrade, String oldTotalGrade, float oldCredit) throws UnsupportedEncodingException {
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        courseName = new String(courseName.getBytes("ISO-8859-1"), "UTF-8");
        oldCourseName = new String(oldCourseName.getBytes("ISO-8859-1"), "UTF-8");
        totalGrade = new String(totalGrade.getBytes("ISO-8859-1"), "UTF-8");
        oldTotalGrade = new String(oldTotalGrade.getBytes("ISO-8859-1"), "UTF-8");

        StuWrong stuWrong = stuService.getOneStuWrong(sno);
        //原课程
        StuGrade course = stuService.getReplaceCourse(sno, oldCourseName, oldTotalGrade).get(0);



        //两门课的成绩中有不是数字的情况
        if (!ifInteger(totalGrade) || !ifInteger(oldTotalGrade)){
            return "{\"msg\":\"error\"}";
        }

        //数据库里有该课的成绩 能够匹配上
        if (hasGrade.equals("true")){
            //所选课程
            StuGrade selectCourse = stuService.getReplaceCourse(sno, courseName, totalGrade).get(0);
            //用及格的课程换不及格的课程
            if (Integer.valueOf(oldTotalGrade) >= 60){
                if(Integer.valueOf(totalGrade) < 60){
                    //不及格的课程一定在fail_course表中 有且只有一条记录
//                    List<StuGrade> list = stuService.getFailCourse(sno, courseName);
//                    if (list.size() == 1){
                        //更新记录 表示该课程已经被替换
                        stuService.updateFailCourse(sno, courseName, 1, oldCourseName, oldTotalGrade);

                        //判断是哪个学年的成绩 将那个年度的不及格学分减去该学分 并更新降级状态
                        //判断学年成绩
                        switch (Integer.valueOf(selectCourse.getSemester().substring(0, 4))
                                - Integer.valueOf(sno.substring(0, 4))) {
                            case 0:
                                //减去该学分
                                stuWrong.setGrade1(stuWrong.getGrade1()-selectCourse.getCredit());
                                break;
                            case 1:
                                //减去该学分
                                stuWrong.setGrade2(stuWrong.getGrade2()-selectCourse.getCredit());
                                break;
                            case 2:
                                //减去该学分
                                stuWrong.setGrade3(stuWrong.getGrade3()-selectCourse.getCredit());
                                break;
                            case 3:
                                //减去该学分
                                stuWrong.setGrade4(stuWrong.getGrade4()-selectCourse.getCredit());
                                break;
                            case 4:
                                //减去该学分
                                stuWrong.setGrade5(stuWrong.getGrade5()-selectCourse.getCredit());
                                break;
                            case 5:
                                //减去该学分
                                stuWrong.setGrade6(stuWrong.getGrade6()-selectCourse.getCredit());
                                break;
                        }
                        stuWrong.freshTolScore();
                        if (stuWrong.isDegrade() && stuWrong.freshTolScore() < ALL_YEAR_WRONG_LINE){
                            //每学年都小于18
                            if (stuWrong.getGradeLess18() == 0){
                                stuWrong.setDegrade(false);
                            }
                        }
                    if(stuService.getReplaceCourse(sno, oldCourseName, oldTotalGrade).size()==0){
                        stuWrong.setPeohandle(false);
                    }
                        stuService.updateOneStuWrong(stuWrong);
//                    }else{
//                        return "{\"msg\":\"error\"}";
//                    }
                }
            }else{
                //原课程不及格 所选课程及格  将原课程存到不及格课程表  加上不及格学分 并重新判断是否留级
                if(Integer.valueOf(totalGrade) >= 60){
                    //插入到不及格课程表中
                    stuService.setFailCourse(course, 0, null, null);
                    //判断是哪个学年的成绩 将那个年度的不及格学分加上该学分 并更新降级状态
                    //判断学年成绩           //这里使用所选择的课程（即培养计划中有的课程）的学期为准
                    switch (Integer.valueOf(selectCourse.getSemester().substring(0, 4))
                            - Integer.valueOf(sno.substring(0, 4))) {
                        case 0:
                            //加上该学分                              //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade1(stuWrong.getGrade1()+selectCourse.getCredit());
                            break;
                        case 1:
                            //加上该学分                              //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade2(stuWrong.getGrade2()+selectCourse.getCredit());
                            break;
                        case 2:
                            //加上该学分                              //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade3(stuWrong.getGrade3()+selectCourse.getCredit());
                            break;
                        case 3:
                            //加上该学分                             //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade4(stuWrong.getGrade4()+selectCourse.getCredit());
                            break;
                        case 4:
                            //加上该学分                              //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade5(stuWrong.getGrade5()+selectCourse.getCredit());
                            break;
                        case 5:
                            //加上该学分                              //这里使用培养计划中的课程（所选课程）的学分
                            stuWrong.setGrade6(stuWrong.getGrade6()+selectCourse.getCredit());
                            break;
                    }
                    stuWrong.freshTolScore();
                    //之前没有降级
                    if (!stuWrong.isDegrade()){
                        //刷新之后的总不及格分数也不需要降级
                        if (stuWrong.freshTolScore() < ALL_YEAR_WRONG_LINE){
                            //每学年都小于18
                            if (stuWrong.getGradeLess18() != 0){
                                stuWrong.setDegrade(true);
                            }
                        }else{
                            stuWrong.setDegrade(true);
                        }
                    }
                    if(stuService.getReplaceCourse(sno, oldCourseName, oldTotalGrade).size()==0){
                        stuWrong.setPeohandle(false);
                    }
                    stuService.updateOneStuWrong(stuWrong);
                }else{//原课程不及格 所选课程不及格
                    //学分相同 只需要在不及格表中进行标识
                    //将原课程加入到不及格课程表
                    stuService.setFailCourse(course, 0, null, null);
                    //更新记录 表示该课程已经被替换
                    stuService.updateFailCourse(sno, courseName, 1, oldCourseName, oldTotalGrade);
                    float diff;
                    //学分不相同 则判断a1所在学年，将course-selectCourse的值加入到a1所在的学年的不及格学分，判断不及格情况
                    if ((diff = course.getCredit()-selectCourse.getCredit()) != 0){
                                        //这里使用培养计划中的课程（所选课程）的学分
                        switch (Integer.valueOf(selectCourse.getSemester().substring(0, 4))
                                - Integer.valueOf(sno.substring(0, 4))) {
                            case 0:
                                //加上该差值
                                stuWrong.setGrade1(stuWrong.getGrade1()+diff);
                                break;
                            case 1:
                                stuWrong.setGrade2(stuWrong.getGrade2()+diff);
                                break;
                            case 2:
                                stuWrong.setGrade3(stuWrong.getGrade3()+diff);
                                break;
                            case 3:
                                stuWrong.setGrade4(stuWrong.getGrade4()+diff);
                                break;
                            case 4:
                                stuWrong.setGrade5(stuWrong.getGrade5()+diff);
                                break;
                            case 5:
                                stuWrong.setGrade6(stuWrong.getGrade6()+diff);
                                break;
                        }
                        stuWrong.freshTolScore();
                        //判断留级情况
                        if (stuWrong.getGradeLess18() != 0 || stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE){
                            stuWrong.setDegrade(true);
                        }else{
                            stuWrong.setDegrade(false);
                        }
                        if(stuService.getReplaceCourse(sno, oldCourseName, oldTotalGrade).size()==0){
                            stuWrong.setPeohandle(false);
                        }
                        stuService.updateOneStuWrong(stuWrong);
                    }
                }
            }
        }else{//没有该课的成绩
            //原来的成绩及格（即用及格的课程换不及格的课程）
            if (Integer.valueOf(oldTotalGrade) >= 60){
                //插入到fail_course表中 并标记已经被替代
                StuGrade stuGrade = new StuGrade();
                stuGrade.setSno(sno);
                stuGrade.setName(name);
                stuGrade.setCourseName(courseName);
                stuGrade.setTotalGrade(totalGrade);
                stuService.setFailCourse(stuGrade, 1, oldCourseName, oldTotalGrade);
            }else{//原来的成绩不及格
                stuService.setFailCourse(course, 0, null, null);
                switch (Integer.valueOf(semester.substring(0, 4))
                        - Integer.valueOf(sno.substring(0, 4))) {
                    case 0:
                        //加上该差值
                        stuWrong.setGrade1(stuWrong.getGrade1()+course.getCredit());
                        break;
                    case 1:
                        stuWrong.setGrade2(stuWrong.getGrade2()+course.getCredit());
                        break;
                    case 2:
                        stuWrong.setGrade3(stuWrong.getGrade3()+course.getCredit());
                        break;
                    case 3:
                        stuWrong.setGrade4(stuWrong.getGrade4()+course.getCredit());
                        break;
                    case 4:
                        stuWrong.setGrade5(stuWrong.getGrade5()+course.getCredit());
                        break;
                    case 5:
                        stuWrong.setGrade6(stuWrong.getGrade6()+course.getCredit());
                        break;
                }
                stuWrong.freshTolScore();
                //判断留级情况
                if (stuWrong.getGradeLess18() != 0 || stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE){
                    stuWrong.setDegrade(true);
                }else{
                    stuWrong.setDegrade(false);
                }

            }
            if(stuService.getReplaceCourse(sno, oldCourseName, oldTotalGrade).size()==0){
                stuWrong.setPeohandle(false);
            }
            stuService.updateOneStuWrong(stuWrong);

        }
        stuService.deleteConflictCourse(sno, oldCourseName);
        System.out.println("replace "+sno+" "+name+" "+courseName+" "+totalGrade);
        return "{\"msg\":\"success\"}";
//        return "redirect:/admin/user/conflictCourse?id="+sno+"&name="+name;
    }

    @RequestMapping(value = "computeFail")
    public String computeFail() {
        for(int i = 1; i <= 3; i++){
            compute(String.valueOf(getCurrYear()-i));
        }
        return "/admin/user/listAllUserDo";
    }

    /**
     * 计算某一级的不及格学分
     *
     * @param level 级数 如"13"代表2013级
     */
    public void compute(String level) {
        List<StuInfo> stuInfos = stuService.getStuInfoList(level);
        StuWrong stuWrong;
        //遍历所有学生
        for (StuInfo stuInfo : stuInfos) {
            stuWrong = new StuWrong();
            //输入基本信息
            stuWrong.setCno(stuInfo.getCno());
            stuWrong.setName(stuInfo.getName());
            stuWrong.setSno(stuInfo.getSno());
            stuWrong.setRoll(stuInfo.getRollStatus());
            System.out.println("学号 " + stuInfo.getSno() + " 姓名 " + stuInfo.getName() + " 班级 " + stuInfo.getCno());
            //完整学年
            String fullTh;
            //表示当前计算的学分是大几的学分
            int aFew = 1;
            //学号与班级匹配 则未降过级
            //只要计算每年的成绩、计算总成绩并判断是否留级
            if (ifMatch(stuInfo.getSno(), stuInfo.getCno())) {
                List<StuGrade> stuAllGrades = stuService.getStuAllGradeWithPlan(stuInfo.getSno());
                //存储学年和对应的总不及格学分
                Map<String, Float> semAndCredit = new HashMap<String, Float>();
                //遍历单个学生成绩
                for(StuGrade stuGrade : stuAllGrades){
                    //总成绩是数字且不及格
                    if(ifInteger(stuGrade.getTotalGrade()) && Integer.valueOf(stuGrade.getTotalGrade()) < 60){
                        String schoolYear = stuGrade.getSemester().substring(0, 9);
                        if (semAndCredit.containsKey(schoolYear)){
                            semAndCredit.put(schoolYear, semAndCredit.get(schoolYear)+stuGrade.getCredit());
                        }else{
                            semAndCredit.put(schoolYear, stuGrade.getCredit());
                        }
                    }
                }
                float failCreditCount = 0;
                for (int sYear = Integer.valueOf(level), i = 1; sYear < CURR_YEAR; sYear++, i++) {
                    fullTh = "20" + sYear + "-20" + (sYear + 1);
                    if (semAndCredit.containsKey(fullTh)){
                        failCreditCount = semAndCredit.get(fullTh);
                    }
                    switch (i) {
                        case 1:
                            stuWrong.setGrade1(failCreditCount);
                            stuWrong.setValidGrade1(true);
                            break;
                        case 2:
                            stuWrong.setGrade2(failCreditCount);
                            stuWrong.setValidGrade2(true);
                            break;
                        case 3:
                            stuWrong.setGrade3(failCreditCount);
                            stuWrong.setValidGrade3(true);
                            break;
                        case 4:
                            stuWrong.setGrade4(failCreditCount);
                            stuWrong.setValidGrade4(true);
                            break;
                        case 5:
                            stuWrong.setGrade5(failCreditCount);
                            stuWrong.setValidGrade5(true);
                            break;
                        case 6:
                            stuWrong.setGrade6(failCreditCount);
                            stuWrong.setValidGrade6(true);
                            break;
                    }
                    if (stuWrong.getGradeLess18() != 0){
                        stuWrong.setDegrade(true);
                    }
                }

            } else {//降过级

                //不及格学分
                float failCreditCount;
                //记录成绩列表中有但培养计划里没有的课程
                List<StuGrade> conflictCourses;

                //sYear表示学年 同level相似都为两位数 i表示从入学开始的第几学年
                //此循环表示计算某同学从大一开始到今年的每一年不及格学分
                for (int sYear = Integer.valueOf(stuInfo.getSno().substring(2, 4)), i = 1; sYear < CURR_YEAR; sYear++, i++) {
                    fullTh = "20" + sYear + "-20" + (sYear + 1);
                    System.out.println("完整学年 " + fullTh);
                    switch (i) {
                        case 1:
                            failCreditCount = firstCompute(stuInfo.getSno(), fullTh);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade1(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade1(failCreditCount);
                            stuWrong.setValidGrade1(true);
                            //大一分数就超过18分 则留级
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            } else {
                                aFew++;
                            }
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());

                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }


                            //recordConflict(stuInfo.getSno(), fullTh);
                            break;
                        case 2:
                            failCreditCount = otherCompute(stuInfo.getSno(), fullTh, sYear);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade2(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade2(failCreditCount);
                            stuWrong.setValidGrade2(true);
                            //如果前一学年留级了 则判定上一学年的成绩无效
                            if (stuWrong.isWasWrong() && aFew == stuWrong.getWrongLevel()) {
                                stuWrong.setValidGrade1(false);
                                stuService.deleteFailCourse(stuWrong.getSno(), "20" + (sYear-1) + "-20" + sYear);
                            }
                            //单年不及格学分超过18分
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }else{
                                stuWrong.setDegrade(false);
                            }
                            //总不及格学分超28
                            if (aFew == stuWrong.getValidCreditNum() && stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }
                            if (!stuWrong.isDegrade())
                                aFew++;
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());
                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }
                            break;
                        case 3:
                            failCreditCount = otherCompute(stuInfo.getSno(), fullTh, sYear);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade3(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade3(failCreditCount);
                            stuWrong.setValidGrade3(true);
                            //如果前一学年留级了 则判定上一学年的成绩无效
                            if (stuWrong.isWasWrong() && aFew == stuWrong.getWrongLevel()) {
                                stuWrong.setValidGrade2(false);
                                stuService.deleteFailCourse(stuWrong.getSno(), "20" + (sYear-1) + "-20" + sYear);
                            }
                            //单年不及格学分超过18分
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWrongLevel(aFew);
                            }else{
                                stuWrong.setDegrade(false);
                            }
                            if (aFew == stuWrong.getValidCreditNum() && stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }
                            if (!stuWrong.isDegrade())
                                aFew++;
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());
                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }
                            break;
                        case 4:
                            failCreditCount = otherCompute(stuInfo.getSno(), fullTh, sYear);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade4(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade4(failCreditCount);
                            stuWrong.setValidGrade4(true);
                            //如果前一学年留级了 则判定上一学年的成绩无效
                            if (stuWrong.isWasWrong() && aFew == stuWrong.getWrongLevel()) {
                                stuWrong.setValidGrade3(false);
                                stuService.deleteFailCourse(stuWrong.getSno(), "20" + (sYear-1) + "-20" + sYear);
                            }
                            //单年不及格学分超过18分
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWrongLevel(aFew);
                            }else{
                                stuWrong.setDegrade(false);
                            }
                            if (aFew == stuWrong.getValidCreditNum() && stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }
                            if (!stuWrong.isDegrade())
                                aFew++;
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());
                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }
                            break;
                        case 5:
                            failCreditCount = otherCompute(stuInfo.getSno(), fullTh, sYear);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade5(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade5(failCreditCount);
                            stuWrong.setValidGrade5(true);
                            //如果前一学年留级了 则判定上一学年的成绩无效
                            if (stuWrong.isWasWrong() && aFew == stuWrong.getWrongLevel()) {
                                stuWrong.setValidGrade4(false);
                                stuService.deleteFailCourse(stuWrong.getSno(), "20" + (sYear-1) + "-20" + sYear);
                            }
                            //单年不及格学分超过18分
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWrongLevel(aFew);
                            }else{
                                stuWrong.setDegrade(false);
                            }
                            if (aFew == stuWrong.getValidCreditNum() && stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }
                            if (!stuWrong.isDegrade())
                                aFew++;
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());
                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }
                            break;
                        case 6:
                            failCreditCount = otherCompute(stuInfo.getSno(), fullTh, sYear);
                            if (failCreditCount == -1) {
                                stuWrong.setGrade6(failCreditCount);
                                break;
                            }
                            stuWrong.setGrade6(failCreditCount);
                            stuWrong.setValidGrade6(true);
                            //如果前一学年留级了 则判定上一学年的成绩无效
                            if (stuWrong.isWasWrong() && aFew == stuWrong.getWrongLevel()) {
                                stuWrong.setValidGrade5(false);
                                stuService.deleteFailCourse(stuWrong.getSno(), "20" + (sYear-1) + "-20" + sYear);
                            }
                            //单年不及格学分超过18分
                            if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWrongLevel(aFew);
                            }else{
                                stuWrong.setDegrade(false);
                            }
                            if (aFew == stuWrong.getValidCreditNum() && stuWrong.freshTolScore() >= ALL_YEAR_WRONG_LINE) {
                                stuWrong.setDegrade(true);
                                stuWrong.setWasWrong(true);
                                stuWrong.setWrongLevel(aFew);
                            }
                            if (!stuWrong.isDegrade())
                                aFew++;
                            System.out.println("第" + i + "学年 大" + aFew + " 不及格学分" + failCreditCount +
                                    " degrade：" + stuWrong.isDegrade() + " waswrong " + stuWrong.isWasWrong() +
                                    " wronglevel " + stuWrong.getWrongLevel());
                            //记录冲突
                            conflictCourses = stuService.getStuGradeWithOutPlan(stuInfo.getSno(), fullTh);
                            if (conflictCourses == null || conflictCourses.size() == 0)
                                stuWrong.setPeohandle(false);
                            else {
                                stuService.setConflictCourses(conflictCourses);
                                stuWrong.setPeohandle(true);
                            }
                            break;
                    }
                }
            }
            stuWrong.freshTolScore();
            stuService.setStuWrong(stuWrong);
            System.out.println("计算完成：" + stuInfo.getName());
        }
    }

    /**
     * 专门用来计算大学第一年的不及格学分
     *
     * @param sno    学号 如"2012xxxx"
     * @param fullTh 学年 如"2012-2013"
     * @return
     */
    private float firstCompute(String sno, String fullTh) {
        float failCreditCount = 0;
        List<StuGrade> stuGrades = stuService.getStuGradeWithPlan(sno, fullTh);
        if (stuGrades == null || stuGrades.size() == 0)
            return -1;
        for (StuGrade stuGrade : stuGrades) {
            //是数字且小于60
            if (ifInteger(stuGrade.getTotalGrade()) && Integer.valueOf(stuGrade.getTotalGrade()) < 60){
                stuService.setFailCourse(stuGrade, 0, null, null);
                failCreditCount += stuGrade.getCredit();
            }
        }
        return failCreditCount;
    }

    /**
     * 用来计算除了第一学年之外的所有学年的不及格学分
     *
     * @param sno    学号 如"2012xxxx"
     * @param fullTh 学年 如"2012-2013"
     * @param sYear  当前要计算的年份 如"13"表示目前要计算"2013-2014"学年的成绩
     *               同时如果有需要可以查询入学年份到sYear的成绩记录（假如12年入学，则可以查询"2012-2013"学年成绩 假如11年入学，则可以查询"2011-2012""2012-2013"学年成绩）
     * @return
     */
    private float otherCompute(String sno, String fullTh, int sYear) {
        int enterYear = Integer.valueOf(sno.substring(2, 4));
        float failCreditCount = 0;
        List<StuGrade> stuGrades = stuService.getStuGradeWithPlan(sno, fullTh);
        if (stuGrades == null || stuGrades.size() == 0)
            return -1;
        for (StuGrade stuGrade : stuGrades) {
            //是数字且小于60
            if (ifInteger(stuGrade.getTotalGrade()) && Integer.valueOf(stuGrade.getTotalGrade()) < 60) {
                //之前是否学过相同的课
                List<StuGrade> preStuGrades = stuService.getStuGradeWithName(sno, enterYear, sYear, stuGrade.getCourseName());
                //最高的成绩
                int maxGrade;
                //之前上过相同的课
                if (preStuGrades != null) {
                    maxGrade = Integer.valueOf(stuGrade.getTotalGrade());
                    for (StuGrade preStuGrade : preStuGrades) {
                        if (ifInteger(preStuGrade.getTotalGrade())) {//以防万一 未来测试如果没问题可删
                            maxGrade = Math.max(maxGrade, Integer.valueOf(preStuGrade.getTotalGrade()));
                        }
                    }
                    //最高的成绩仍然小于60分
                    if (maxGrade < 60) {
                        stuService.setFailCourse(stuGrade, 0, null, null);
                        failCreditCount += stuGrade.getCredit();
                    }
                } else {
                    stuService.setFailCourse(stuGrade, 0, null, null);
                    failCreditCount += stuGrade.getCredit();
                }
            }
        }
        return failCreditCount;
    }

    /**
     * 比较学号与班级是否匹配 匹配则说明未留过级
     * 如2013xxxx和13xxxx 则学号与班级匹配 2013xxxx和14xxxx 则说明降一级
     *
     * @param sno 学号
     * @param cno 班级
     * @return
     */
    public boolean ifMatch(String sno, String cno) {

        String snoSub = sno.substring(2, 4);
        String cnoSub;
        //正常本科
        if (cno.length() == 6) {
            cnoSub = cno.substring(0, 2);
        } else {//软职
            cnoSub = cno.substring(1, 3);
        }
        return snoSub.equals(cnoSub);
    }

    /**
     * 计算留降级情况
     *
     * @param th     届数 如 "14"表示2014届的学生
     * @param fullTh 当前学年 如"2015-1016"
     *               1.获得某一届学生的所有信息
     *               2.
     */
    public void computeWrong(String th, String fullTh) {
        //可能要计算的一些量
        //如果要降级，要降到大几
        //如2015届 在2016年9月计算时是大二，但如果要降级则要仍为大一

        List<StuInfo> stuInfos = stuService.getStuInfoList(th);
        for (StuInfo stuInfo : stuInfos) {
            //入学年份  12表示2012年入学
            String enterYear = stuInfo.getSno().substring(2, 4);
            //当前是大几
            int currLevel;
            //本科正常届
            if (stuInfo.getCno().length() == 6) {
                currLevel = CURR_YEAR - Integer.valueOf(stuInfo.getCno().substring(2, 4));
            } else {//软职
                currLevel = CURR_YEAR - Integer.valueOf(stuInfo.getCno().substring(1, 3));
            }

            StuWrong stuWrong = stuService.getOneStuWrong(stuInfo.getSno());
            float failCreditCount = 0;
            //没有记录
            if (stuWrong == null) {
                stuWrong = new StuWrong();
                stuWrong.setSno(stuInfo.getSno());
                stuWrong.setName(stuInfo.getName());
                stuWrong.setCno(stuInfo.getCno());
                //只入学一年 说明是新生 只需要计算当前学年的情况
                if (CURR_YEAR - Integer.valueOf(enterYear) == 1) {
                    failCreditCount = computeCredit(stuInfo.getSno(), fullTh);
                    stuWrong.setGrade1(failCreditCount);
                    //如果当前学年超过18分 则降级
                    if (failCreditCount >= ONE_YEAR_WRONG_LINE) {
                        stuWrong.setDegrade(true);
                        stuWrong.setWasWrong(true);
                        stuWrong.setWrongLevel(1);
                    }
                    stuWrong.setTolscore(failCreditCount);
                } else {//第一次使用系统 需要计算所有学年的不及格成绩
                    int enterYearNum = Integer.valueOf(enterYear);
                    //从入学那年遍历到当前学年  如2012年入学 则遍历2012-2013 2013-2014 2014-2015 2015-2016
                    for (int i = enterYearNum; i < CURR_YEAR; i++) {
                        String oldFullTh = "20" + i + "-20" + (i + 1);
                        StuInfo oldStuInfo = stuService.getStuInfoList(th).get(0);
                        computeCredit(oldStuInfo.getSno(), oldFullTh);
                    }

                }
            } else {
                //留过级 则要考虑特殊情况
                if (stuWrong.isWasWrong()) {
                    //specialComputeCredit(stuInfo, stuWrong, enterYear, currLevel, fullTh);

                } else {//没有留过级 则直接计算当前不及格学分即可
                    failCreditCount = computeCredit(stuInfo.getSno(), fullTh);
                    //第一次留级有三种情况 大一或大二或大三过完发现要留级 大一的情况在上面那个if排除了
                    switch (CURR_YEAR - Integer.valueOf(enterYear)) {
                        case 2:
                            stuWrong.setGrade2(failCreditCount);
                            break;
                        case 3:
                            stuWrong.setGrade3(failCreditCount);
                            break;
                    }
                    //刷新总不及格学分
                    stuWrong.freshTolScore();
                    //第一次留级
                    if (failCreditCount >= ONE_YEAR_WRONG_LINE || stuWrong.getTolscore() >= ALL_YEAR_WRONG_LINE) {
                        stuWrong.setDegrade(true);
                        stuWrong.setWasWrong(true);
                        stuWrong.setWrongLevel(CURR_YEAR - Integer.valueOf(enterYear));
                    }
                    stuService.updateOneStuWrong(stuWrong);
                }
            }


        }
    }

    /**
     * 之前有过留级 进行特殊计算
     *
     * @param stuInfo   学生信息
     * @param stuWrong  留降级信息
     * @param enterYear 入学年份 12表示2012年入学
     * @param currLevel 当前大几
     * @param fullTh    完整学年 如'2015-2016'
     * @return 不及格学分
     */
//    private void specialComputeCredit(StuInfo stuInfo, StuWrong stuWrong, String enterYear, int currLevel, String fullTh) {
//        float failCreditCount = 0;
//        //判断上过相同课的情况 即给定学号 查询学生所有符合当前培养计划的课程成绩 放入hash表 再value放最高成绩
//        List<StuGrade> stuGrades = stuService.getStuGradeWithPlan(stuInfo.getSno(), null);
//        //利用hash来判断相同课程的情况下哪个课程分数高
//        Map<String, Integer> gradeMap = new HashMap<String, Integer>();
//        Map<String, Float> creditMap = new HashMap<String, Float>();
//        for (StuGrade stuGrade : stuGrades) {
//            String totalGrade = stuGrade.getTotalGrade();
//            if (!totalGrade.equals("") && ifInteger(totalGrade)) {
//                //没有则加入 有且小于也加入
//                if (!gradeMap.containsKey(stuGrade.getCourseName())) {
//                    gradeMap.put(stuGrade.getCourseName(), Integer.valueOf(totalGrade));
//                    creditMap.put(stuGrade.getCourseName(), stuGrade.getCredit());
//                } else if (gradeMap.get(stuGrade.getCourseName()) < Integer.valueOf(totalGrade)) {
//                    gradeMap.put(stuGrade.getCourseName(), Integer.valueOf(totalGrade));
//                }
//            }
//        }
//        //gradeMap中保存的课程名--成绩 crdeitMap中存储的是课程名--学分
//
//        //有不同课的情况 判断是否属于相同的课
//        //获得当前学期的成绩，如果这门课中有字母或数字（高数A1） 则将字母数字去除后用前半段进行查询 得到的结果不为空 则说明有需要操作判断的情况
//        for (Map.Entry<String, Integer> entry : gradeMap.entrySet()) {
//            String cutGradeName = isFollowCharNum(entry.getKey());
//            if (cutGradeName != null) {
//                //之前有上过类似的课 如高数A1 高数A2
//                List<StuGrade> conflictGrades = stuService.getStuGradeWithCName(stuInfo.getSno(), cutGradeName, entry.getKey());
//                if (conflictGrades != null) {//不为空 则有冲突的课
//                    //原有的成绩
//                    StuGrade norGrade = stuService.getStuGrade(stuInfo.getSno(), fullTh, entry.getKey());
//                    conflictGrades.add(norGrade);
//                    stuService.setConflictCourses(conflictGrades);
//                    //从map中删除该项
//                    gradeMap.remove(entry.getKey());
//                    creditMap.remove(entry.getKey());
//                    //声明有操作
//                    stuWrong.setPeohandle(true);
//                }
//            }
//        }
//
//        //计算不及格学分
//        for (Map.Entry<String, Integer> entry : gradeMap.entrySet()) {
//            if (entry.getValue() < 60) {
//                failCreditCount += creditMap.get(entry.getKey());
//            }
//        }
//
//        //记录不及格学分且判断原有不及格学分无效
//        switch (CURR_YEAR - Integer.valueOf(enterYear)) {
//            case 2:
//                stuWrong.setGrade2(failCreditCount);
//                stuWrong.setValidGrade1(false);
//                break;
//            case 3:
//                stuWrong.setGrade3(failCreditCount);
//                stuWrong.setValidGrade2(false);
//                break;
//            case 4:
//                stuWrong.setGrade4(failCreditCount);
//                stuWrong.setValidGrade3(false);
//                break;
//            case 5:
//                stuWrong.setGrade5(failCreditCount);
//                stuWrong.setValidGrade4(false);
//                break;
//            case 6:
//                stuWrong.setGrade6(failCreditCount);
//                stuWrong.setValidGrade5(false);
//                break;
//        }
//
//        //刷新总学分
//        stuWrong.freshTolScore();
//        //判断是否降级
//        if (failCreditCount >= ONE_YEAR_WRONG_LINE || stuWrong.getTolscore() >= ALL_YEAR_WRONG_LINE) {
//            stuWrong.setDegrade(true);
//            stuWrong.setWasWrong(true);
//            stuWrong.setWrongLevel(currLevel);
//        } else {
//            stuWrong.setDegrade(false);
//        }
//        stuService.updateOneStuWrong(stuWrong);
//    }

    /**
     * 计算某学年与培养计划相匹配的必修课程中不及格课程的学分
     *
     * @param sno    学号
     * @param fullTh 完整学年 如'2015-2016'
     * @return
     */
    private float computeCredit(String sno, String fullTh) {
        List<StuGrade> stuGrades = stuService.getStuGradeWithPlan(sno, fullTh);
        float failCreditCount = 0;//不及格学分统计
        for (StuGrade stuGrade : stuGrades) {
            String totalGrade = stuGrade.getTotalGrade();
            //总成绩不为空且总成绩中的字符串为数字
            if (!totalGrade.equals("") && ifInteger(totalGrade)) {
                if (Integer.valueOf(totalGrade) < 60) {
                    failCreditCount += stuGrade.getCredit();
                }
            }
        }
        return failCreditCount;
    }

    /**
     * 获得整型的今年后两位  如2016年返回16
     *
     * @return
     */
    private static int getCurrYear() {
        return 16;
    }

    /**
     * 判断这个字符串是不是数字 如"13"是不是表示数字13
     *
     * @param str
     * @return
     */
    private boolean ifInteger(String str) {
        if (str.equals(""))
            return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断这个字符串后面是否接字母数字 如"高等数学A1"
     * 如果接则去除字母数字后返回 不接则返回null
     *
     * @param str
     * @return
     */
    private String isFollowCharNum(String str) {
        if (str == null)
            return null;
        Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]+)[A-Za-z]*[0-9]*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }

    }

}
