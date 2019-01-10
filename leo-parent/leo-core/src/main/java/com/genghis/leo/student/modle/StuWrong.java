package com.genghis.leo.student.modle;

import com.genghis.steed.mybatis.model.PageBase;

/**
 * Created by hao pc on 2016/9/23.
 */
public class StuWrong extends PageBase {
    private int sno;
    private String name;
    private int cno;
    private int grade5;
    private int grade4;
    private int grade3;
    private int grade2;
    private int grade1;
    private int tolscore;
    private boolean degrade;
    private boolean peohandle;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getGrade5() {
        return grade5;
    }

    public void setGrade5(int grade5) {
        this.grade5 = grade5;
    }

    public int getGrade4() {
        return grade4;
    }

    public void setGrade4(int grade4) {
        this.grade4 = grade4;
    }

    public int getGrade3() {
        return grade3;
    }

    public void setGrade3(int grade3) {
        this.grade3 = grade3;
    }

    public int getGrade2() {
        return grade2;
    }

    public void setGrade2(int grade2) {
        this.grade2 = grade2;
    }

    public int getGrade1() {
        return grade1;
    }

    public void setGrade1(int grade1) {
        this.grade1 = grade1;
    }

    public int getTolscore() {
        return tolscore;
    }

    public void setTolscore(int tolscore) {
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
}
