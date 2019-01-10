package com.genghis.leo.demotion.model;

/**
 * Created by dabai on 2016/9/25.
 */
public class StuInfo {

    //学号
    private String sno;
    //姓名
    private String name;
    //院系
    private String college;
    //专业
    private String major;
    //专业方向
    //private String majorField;
    //班级
    private String cno;
    //学籍状态
    private String rollStatus;
    //入学日期
    //private String enterDate;
    //创建年份 如在2016年9月输入时 选择2015-2016
    //private String createYear;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(String rollStatus) {
        this.rollStatus = rollStatus;
    }
}
