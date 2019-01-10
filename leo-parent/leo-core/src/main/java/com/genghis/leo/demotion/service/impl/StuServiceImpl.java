package com.genghis.leo.demotion.service.impl;

import com.genghis.leo.demotion.dao.StuDao;
import com.genghis.leo.demotion.model.StuGrade;
import com.genghis.leo.demotion.model.StuInfo;
import com.genghis.leo.demotion.model.StuPlan;
import com.genghis.leo.demotion.model.StuWrong;
import com.genghis.leo.demotion.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dabai on 2016/9/26.
 */
@Service("StuService")
public class StuServiceImpl implements StuService {

    @Autowired
    private StuDao stuDao;


    //留级学生相关
    @Override
    public List<StuWrong> getUserListPage(StuWrong stuwrong) {
        return stuDao.getUserListPage(stuwrong);
    }

    @Override
    public List<StuWrong> searchStuWrongListPage(String attribute, String value) {
        return stuDao.searchStuWrongListPage(attribute, value);
    }

    @Override
    public StuWrong getOneStuWrong(String sno) {
        return stuDao.getOneStuWrong(sno);
    }

    @Override
    public void updateOneStuWrong(StuWrong stuWrong) {
        stuDao.updateOneStuWrong(stuWrong);
    }

    @Override
    public void setStuWrong(StuWrong stuWrong) {
        stuDao.setStuWrong(stuWrong);
    }

    @Override
    public List<StuWrong> getAllStuWrong() {
        return stuDao.getAllStuWrong();
    }

    @Override
    public void deleteStuWrong(String level) {
        stuDao.deleteStuWrong(level);
        stuDao.deleteConflictCourseByCNO(level);
        stuDao.deleteFailCourseByCNO(level);
    }

    //学生信息相关
    @Override
    public void setStuInfo(StuInfo stuInfo) {
        stuDao.insertStuInfo(stuInfo);
    }

    //根据指定的届数查找 如果th为空则查找全部 如果不为空则根据th指定的届数进行查找
    @Override
    public List<StuInfo> getStuInfoList(String th) {
        return stuDao.getStuInfoList(th);
    }

    @Override
    public void deleteStuInfo(String level) {
        stuDao.deleteStuInfo(level);
    }

    //考试成绩相关
    @Override
    public void setStuGrade(StuGrade stuGrade) {
        stuDao.insertStuGrade(stuGrade);
    }

    //指定学号和学年 获得该学生与培养计划匹配的所有必修课程成绩
    @Override
    public List<StuGrade> getStuGradeWithPlan(String sno, String fullTh) {
        Map<String, String> snoAndFullTh = new HashMap<String, String>(2);
        snoAndFullTh.put("sno", sno);
        snoAndFullTh.put("fullTh", fullTh);
        return stuDao.getStuGradeWithPlan(snoAndFullTh);
    }

    @Override
    public List<StuGrade> getStuGradeWithOutPlan(String sno, String fullTh) {
        Map<String, String> snoAndFullTh = new HashMap<String, String>(2);
        snoAndFullTh.put("sno", sno);
        snoAndFullTh.put("fullTh", fullTh);
        return stuDao.getStuGradeWithOutPlan(snoAndFullTh);
    }

    @Override
    public List<StuGrade> getStuAllGradeWithPlan(String sno) {
        return stuDao.getStuAllGradeWithPlan(sno);
    }

    @Override
    public List<StuGrade> getStuGradeWithName(String sno, int enterYear, int sYear, String courseName) {
        List<StuGrade> result = new ArrayList<StuGrade>(2);
        String fullTh;
        for(int i = enterYear; i < sYear; i++){
            fullTh = "20"+i+"-20"+(i+1);
            List<StuGrade> resultTemp = getStuGrade(sno, fullTh, courseName);
            if (resultTemp != null)
                result.addAll(resultTemp);
        }
        if (result.size() != 0)
            return result;
        return null;
    }

    @Override
    public List<StuGrade> getStuGradeWithCName(String sno, String cno, String cutCName, String norCName) {
        Map<String, String> snoCnoCutAndNorCourse = new HashMap<String, String>();
        snoCnoCutAndNorCourse.put("sno", sno);
        snoCnoCutAndNorCourse.put("cno", cno);
        snoCnoCutAndNorCourse.put("cutCName", cutCName);
        snoCnoCutAndNorCourse.put("norCName", norCName);
        System.out.println(sno+" "+cutCName+" "+norCName);
        return stuDao.getStuGradeWithCName(snoCnoCutAndNorCourse);
    }

    @Override
    public List<StuGrade> getStuGrade(String sno, String fullTh, String courseName) {
        Map<String, String> map = new HashMap<String, String>(3);
        map.put("sno", sno);
        map.put("fullTh", fullTh);
        map.put("courseName", courseName);
        return stuDao.getStuGrade(map);
    }

    @Override
    public void deleteStuGrade(String semester) {
        stuDao.deleteStuGrade(semester);
    }


    //培养计划相关
    @Override
    public void setStuPlan(StuPlan stuPlan) {
        stuDao.insertStuPlan(stuPlan);
    }

    @Override
    public void deleteStuPlan(String semester) {
        stuDao.deleteStuPlan(semester);
    }

    @Override
    public void setConflictCourses(List<StuGrade> stuGrades) {
        for (StuGrade stuGrade : stuGrades){
            stuDao.insertConflictCourse(stuGrade);
        }
    }

    @Override
    public List<StuGrade> getStuConflictCourse(String sno, String courseName) {
        Map<String, String> snoAndCourseName = new HashMap<String, String>(2);
        snoAndCourseName.put("sno", sno);
        snoAndCourseName.put("courseName", courseName);
        List<StuGrade> stuList= stuDao.getStuConflictCourse(snoAndCourseName);
        return stuList;
    }

    @Override
    public void deleteConflictCourse(String sno, String courseName) {
        Map<String, String> snoAndCourseName = new HashMap<String, String>(2);
        snoAndCourseName.put("sno", sno);
        snoAndCourseName.put("courseName", courseName);
        stuDao.deleteConflictCourse(snoAndCourseName);
    }

    @Override
    public List<StuGrade> getReplaceCourse(String sno, String courseName, String totalGrade) {
        Map<String, String> snoCNameAndTGrade = new HashMap<>();
        snoCNameAndTGrade.put("sno", sno);
        snoCNameAndTGrade.put("courseName", courseName);
        snoCNameAndTGrade.put("totalGrade", totalGrade);
        return stuDao.getReplaceCourse(snoCNameAndTGrade);
    }

    @Override
    public void setFailCourse(StuGrade stuGrade, int isReplace, String replaceName, String replaceGrade) {
        Map<String, Object> map = new HashMap<>();
        map.put("stuGrade", stuGrade);
        map.put("isReplace", isReplace);
        map.put("replaceGrade", replaceGrade);
        map.put("replaceName", replaceName);
        stuDao.setFailCourse(map);
    }

    @Override
    public void deleteFailCourse(String sno, String semester) {
        Map<String, String> snoAndSemester = new HashMap<String, String>(2);
        snoAndSemester.put("sno", sno);
        snoAndSemester.put("semester", semester);
        stuDao.deleteFailCourse(snoAndSemester);
    }

    @Override
    public List<StuGrade> getFailCourseWOReplace() {
        return stuDao.getFailCourseWOReplace();
    }

    @Override
    public List<StuGrade> getFailCourse(String sno, String courseName) {
        Map<String, String> map = new HashMap<>(2);
        map.put("sno", sno);
        map.put("courseName", courseName);
        return stuDao.getFailCourse(map);
    }

    @Override
    public void updateFailCourse(String sno, String courseName, int isReplace, String replaceCourseName, String replaceGrade) {
        Map<String, Object> map = new HashMap<>();
        map.put("sno", sno);
        map.put("courseName", courseName);
        map.put("isReplace", isReplace);
        map.put("replaceCourseName", replaceCourseName);
        map.put("replaceGrade", replaceGrade);
        stuDao.updateFailCourse(map);
    }

}
