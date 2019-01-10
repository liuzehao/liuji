package com.genghis.leo.demotion.service;

import com.genghis.leo.demotion.model.StuGrade;
import com.genghis.leo.demotion.model.StuInfo;
import com.genghis.leo.demotion.model.StuPlan;
import com.genghis.leo.demotion.model.StuWrong;

import java.util.List;
import java.util.Map;

/**
 * Created by dabai on 2016/9/26.
 */
public interface StuService {
    //留级学生相关
    List<StuWrong> getUserListPage(StuWrong stuwrong);
    List<StuWrong> searchStuWrongListPage(String attribute,String value);
    StuWrong getOneStuWrong(String sno);
    void updateOneStuWrong(StuWrong stuWrong);
    void setStuWrong(StuWrong stuWrong);
    List<StuWrong> getAllStuWrong();
    void deleteStuWrong(String level);

    //学生信息相关
    void setStuInfo(StuInfo stuInfo);
    //根据指定的届数和学生信息所属的学年（createYear）进行查询 如果一个为null则根据另一个查询 全为null则查询全部
    List<StuInfo> getStuInfoList(String th);
    //删除相应年级的学生信息
    void deleteStuInfo(String level);

    //考试成绩相关
    void setStuGrade(StuGrade stuGrade);
    //指定学号和学年 获得该学生与培养计划匹配的所有必修课程成绩
    List<StuGrade> getStuGradeWithPlan(String sno, String fullTh);
    //指定学号和学年 获得该学生与培养计划匹配不上的所有必修课程成绩
    List<StuGrade> getStuGradeWithOutPlan(String sno, String fullTh);
    //获得某个学生的所有成绩 只用于获取没有降过级的学生成绩
    List<StuGrade> getStuAllGradeWithPlan(String sno);
    //指定学号、学生入学时间、当前年份和课程名 查找该学生从当前年度（不包括当前年份）到入学年度是否学过与courseName相同的课 如果学过则返回
    List<StuGrade> getStuGradeWithName(String sno, int enterYear, int sYear, String courseName);
    //指定学号 去除字母数字的课程名（高等数学） 原名（高等数学A1） 查询该学生原来有没有上过类似的课 以进行操作判断
    List<StuGrade> getStuGradeWithCName(String sno, String cno, String cutCName, String norCName);
    //指定学号 学年 课程名 获得相应的成绩
    List<StuGrade> getStuGrade(String sno, String fullTh, String courseName);
    void deleteStuGrade(String semester);

    //培养计划相关
    void setStuPlan(StuPlan stuPlan);
    void deleteStuPlan(String semester);

    //冲突课程相关
    void setConflictCourses(List<StuGrade> stuGrades);
    //根据学号和课程号查询 如果课程号为空 则可以该学生所有冲突课程
    List<StuGrade> getStuConflictCourse(String sno, String courseName);
    //根据课程名删除冲突课程
    void deleteConflictCourse(String sno, String courseName);

    List<StuGrade> getReplaceCourse(String sno, String courseName, String totalGrade);

    //保存
    void setFailCourse(StuGrade stuGrade, int isReplace, String replaceName, String replaceGrade);
    void deleteFailCourse(String sno, String semester);
    //获得没有被替换的不及格课程
    List<StuGrade> getFailCourseWOReplace();
    List<StuGrade> getFailCourse(String sno, String courseName);
    //String sno, String courseName, int isReplace, String replaceName, String replaceGrade
    void updateFailCourse(String sno, String courseName, int isReplace, String replaceName, String replaceGrade);

}
