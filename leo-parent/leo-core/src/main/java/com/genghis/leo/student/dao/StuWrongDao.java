package com.genghis.leo.student.dao;

import com.genghis.leo.student.modle.StuWrong;
import com.genghis.steed.annotation.mybatisRepository;

import java.util.List;

/**
 * Created by hao pc on 2016/9/23.
 */
@mybatisRepository
public interface StuWrongDao {
    List<StuWrong> getUserListPage(StuWrong stuwrong);
    List<StuWrong> searchStuWrongListPage(String attribute,String value);
}
