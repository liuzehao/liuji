package com.genghis.leo.student.service.impl;

import com.genghis.leo.student.dao.StuWrongDao;
import com.genghis.leo.student.modle.StuWrong;
import com.genghis.leo.student.service.StuWrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao pc on 2016/9/23.
 */
@Service("StuWrongService")
public class StuWrongServiceImpl implements StuWrongService {
    @Autowired
    private StuWrongDao stuWrongDao;
    @Override
   public List<StuWrong> getUserListPage(StuWrong stuwrong)
   {
       return stuWrongDao.getUserListPage(stuwrong);
   }
    @Override
    public List<StuWrong> searchStuWrongListPage(String attribute,String value){
        return stuWrongDao.searchStuWrongListPage(attribute,value);
    }
}
