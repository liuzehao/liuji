package com.genghis.leo.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hao pc on 2016/9/24.
 */
@Controller
public class ExcelController {
    @RequestMapping(value = "/excel/getExcel",method = {RequestMethod.GET})
    public String getExcelPage() {
        return "admin/excel/addExcelDo";
    }
    @RequestMapping(value="uploadFile",method= RequestMethod.POST)//上传Excel
    public @ResponseBody  HashMap<String, String> uploadFile(@RequestParam(value = "input", required = true) MultipartFile file) throws IOException {
        String myFileName = file.getOriginalFilename();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("false", " ");//标志
        if(file != null){
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            System.out.println(myFileName);//Excel已经读入内存
            map.put("true", " ");//返回成功
        }

        return map;
    }
}
