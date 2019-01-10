package com.genghis.leo.security;

import com.genghis.leo.student.modle.StuWrong;
import com.genghis.leo.student.service.StuWrongService;
import com.genghis.steed.ajax.response.PageResponse;
import com.genghis.steed.mybatis.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hao pc on 2016/9/23.
 */
@Controller
@RequestMapping("/stuwrong")
public class StuwrongController {
    @Autowired
    private StuWrongService stuWrongService;
    @RequestMapping(value = "getAllstuwrong", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<StuWrong> findAllStuWrong(StuWrong stuwrong) {
        return new PageResponse<>(stuwrong.getPage(),stuWrongService.getUserListPage(stuwrong));
    }




}
