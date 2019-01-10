package com.genghis.leo.demotion.model;

import com.genghis.steed.mybatis.model.PageBase;

/**
 * Created by hao pc on 2016/9/23.
 */
public class StuWrong extends PageBase {
    //学号
    private String sno;
    private String name;
    //班级
    private String cno;
    //学籍情况
    private String roll;
    private float grade6;
    private float grade5;
    private float grade4;
    private float grade3;
    private float grade2;
    private float grade1;

    //标志每个学年的学分是否有效 正常没有留过级的学生只有前三学年成绩
    private boolean validGrade6 = false;
    private boolean validGrade5 = false;
    private boolean validGrade4 = false;
    private boolean validGrade3 = false;
    private boolean validGrade2 = false;
    private boolean validGrade1 = false;

    //总不及格学分
    private float tolscore;
    //是否降级
    private boolean degrade;
    //操作
    private boolean peohandle;

    //是否留过级 为了应对大一留一级 大二留一级的情况
    private boolean wasWrong;
    //上一次留级是大几
    private int wrongLevel;

    /**
     * 获得有几个有效成绩
     * @return
     */
    public int getValidCreditNum() {
        int result = 0;

        if (validGrade1)
            result++;
        if (validGrade2)
            result++;
        if (validGrade3)
            result++;
        if (validGrade4)
            result++;
        if (validGrade5)
            result++;
        if (validGrade6)
            result++;
        return result;
    }

    /**
     * 判断该学生有效学年的不及格学分都小于18  在替换replace时用到
     * @return 返回0表示每个有效学年的不及格学分都小于18 返回其他数字表示有某一学年的不及格学分大于18
     */
    public int getGradeLess18(){
        if(validGrade1 && grade1 >= 18){
            return 1;
        }
        if(validGrade2 && grade2 >= 18){
            return 2;
        }
        if(validGrade3 && grade3 >= 18){
            return 3;
        }
        if(validGrade4 && grade4 >= 18){
            return 4;
        }
        if(validGrade5 && grade5 >= 18){
            return 5;
        }
        if(validGrade6 && grade6 >= 18){
            return 6;
        }
        return 0;
    }

    /**
     * 刷新总学分
     */
    public float freshTolScore() {
        float temp = 0;
        if (validGrade1 && grade1 != -1) {
            temp += grade1;
        }
        if (validGrade2 && grade2 != -1) {
            temp += grade2;
        }
        if (validGrade3 && grade3 != -1) {
            temp += grade3;
        }
        if (validGrade4 && grade4 != -1) {
            temp += grade4;
        }
        if (validGrade5 && grade5 != -1) {
            temp += grade5;
        }
        if (validGrade6 && grade6 != -1) {
            temp += grade6;
        }
        tolscore = temp;
        return getTolscore();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public float getGrade5() {
        return grade5;
    }

    public void setGrade5(float grade5) {
        this.grade5 = grade5;
    }

    public float getGrade4() {
        return grade4;
    }

    public void setGrade4(float grade4) {
        this.grade4 = grade4;
    }

    public float getGrade3() {
        return grade3;
    }

    public void setGrade3(float grade3) {
        this.grade3 = grade3;
    }

    public float getGrade2() {
        return grade2;
    }

    public void setGrade2(float grade2) {
        this.grade2 = grade2;
    }

    public float getGrade1() {
        return grade1;
    }

    public void setGrade1(float grade1) {
        this.grade1 = grade1;
    }

    public float getTolscore() {
        return tolscore;
    }

    public void setTolscore(float tolscore) {
        this.tolscore = tolscore;
    }

    public boolean isDegrade() {
        return degrade;
    }

    public void setDegrade(boolean degrade) {
        this.degrade = degrade;
    }

    public boolean isPeohandle() {
        return peohandle;
    }

    public void setPeohandle(boolean peohandle) {
        this.peohandle = peohandle;
    }

    public boolean isWasWrong() {
        return wasWrong;
    }

    public void setWasWrong(boolean wasWrong) {
        this.wasWrong = wasWrong;
    }

    public int getWrongLevel() {
        return wrongLevel;
    }

    public void setWrongLevel(int wrongLevel) {
        this.wrongLevel = wrongLevel;
    }

    public float getGrade6() {
        return grade6;
    }

    public void setGrade6(float grade6) {
        this.grade6 = grade6;
    }

    public boolean isValidGrade5() {
        return validGrade5;
    }

    public void setValidGrade5(boolean validGrade5) {
        this.validGrade5 = validGrade5;
    }

    public boolean isValidGrade4() {
        return validGrade4;
    }

    public void setValidGrade4(boolean validGrade4) {
        this.validGrade4 = validGrade4;
    }

    public boolean isValidGrade3() {
        return validGrade3;
    }

    public void setValidGrade3(boolean validGrade3) {
        this.validGrade3 = validGrade3;
    }

    public boolean isValidGrade2() {
        return validGrade2;
    }

    public void setValidGrade2(boolean validGrade2) {
        this.validGrade2 = validGrade2;
    }

    public boolean isValidGrade1() {
        return validGrade1;
    }

    public void setValidGrade1(boolean validGrade1) {
        this.validGrade1 = validGrade1;
    }

    public boolean isValidGrade6() {
        return validGrade6;
    }

    public void setValidGrade6(boolean validGrade6) {
        this.validGrade6 = validGrade6;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
