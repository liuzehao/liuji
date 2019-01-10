package com.genghis.leo.student.service;

import com.genghis.leo.student.modle.StuWrong;

import java.util.List;

/**
 * Created by hao pc on 2016/9/23.
 */
public interface StuWrongService {
    List<StuWrong> getUserListPage(StuWrong stuwrong);
    List<StuWrong> searchStuWrongListPage(String attribute,String value);
}
