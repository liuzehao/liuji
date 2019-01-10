package com.genghis.leo.demotion.dao;

import com.genghis.leo.demotion.model.StuGrade;
import com.genghis.leo.demotion.model.StuInfo;
import com.genghis.leo.demotion.model.StuPlan;
import com.genghis.leo.demotion.model.StuWrong;
import com.genghis.steed.annotation.mybatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dabai on 2016/9/26.
 */
@mybatisRepository
public interface StuDao {

    //留级信息相关
    List<StuWrong> getUserListPage(StuWrong stuwrong);
    List<StuWrong> searchStuWrongListPage(String attribute,String value);
    StuWrong getOneStuWrong(String sno);
    void updateOneStuWrong(StuWrong stuWrong);
    void setStuWrong(StuWrong stuWrong);
    List<StuWrong> getAllStuWrong();
    void deleteStuWrong(String level);

    //学生信息相关
    void insertStuInfo(StuInfo stuInfo);
    List<StuInfo> getStuInfoList(@Param("th") String th);
    //删除相应年级的学生信息
    void deleteStuInfo(String level);

    //学生成绩相关
    void insertStuGrade(StuGrade stuGrade);
    //指定学号和学年 获得该学生与培养计划匹配的所有必修课程成绩 并且相同课程中只取最高分 sno fullTh
    List<StuGrade> getStuGradeWithPlan(Map<String, String> snoAndFullTh);
    //指定学号和学年 获得该学生与培养计划匹配不上的所有必修课程成绩 并且相同课程中只取最高分
    List<StuGrade> getStuGradeWithOutPlan(Map<String, String> snoAndFullTh);
    //获得某个学生的所有成绩 只用于获取没有降过级的学生成绩
    List<StuGrade> getStuAllGradeWithPlan(String sno);
    //指定学号 去除字母数字的课程名（高等数学） 原名（高等数学A1） 查询该学生原来有没有上过类似的课 以进行操作判断 String sno, String cutCName, String norCName
    List<StuGrade> getStuGradeWithCName(Map<String, String> snoCutAndNorCourse);
    //指定学号 学年 课程名 获得相应的成绩 sno fullTh courseName
    List<StuGrade> getStuGrade(Map<String, String> snoFullThAndCourseName);
    void deleteStuGrade(String semester);


    //培养计划相关
    void insertStuPlan(StuPlan stuPlan);
    void deleteStuPlan(String semester);

    //冲突课程相关
    void insertConflictCourse(StuGrade stuGrade);
    //String sno, String courseName
    List<StuGrade> getStuConflictCourse(Map<String, String> snoAndCourseName);
    //根据课程名删除冲突课程
    void deleteConflictCourse(Map<String, String> snoAndCourseName);


    //String sno, String courseName, String totalGrade
    List<StuGrade> getReplaceCourse(Map<String, String> snoCNameAndTGrade);

    //保存不及格课程 StuGrade stuGrade, int isReplace, String replaceNo, String replaceName
    void setFailCourse(Map<String, Object> map);
    void deleteFailCourse(Map<String, String> snoAndSemester);
    //获得没有被替换的不及格课程
    List<StuGrade> getFailCourseWOReplace();
    //String sno, String courseName
    List<StuGrade> getFailCourse(Map<String, String> map);
    //String sno, String courseName, int isReplace, String replaceName, String replaceGrade
    void updateFailCourse(Map<String, Object> map);

    void deleteFailCourseByCNO(String level);
    void deleteConflictCourseByCNO(String level);
}
