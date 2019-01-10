package com.genghis.leo.demotion.model;

/**
 * Created by dabai on 2016/9/26.
 */
public class StuPlan {

    //学期
    private String semester;
    //学院
    private String college;
    //届数
    private String th;
    //专业
    private String major;
    //课程编号
    private String courseNo;
    //课程名称
    private String courseName;
    //学分
    private float credit;
    //课程属性
    private String courseAttr;
    //开课单位
    private String courseDepart;
    //班级
    private String cno;
    //平台
    private String platform;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(String courseAttr) {
        this.courseAttr = courseAttr;
    }

    public String getCourseDepart() {
        return courseDepart;
    }

    public void setCourseDepart(String courseDepart) {
        this.courseDepart = courseDepart;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
