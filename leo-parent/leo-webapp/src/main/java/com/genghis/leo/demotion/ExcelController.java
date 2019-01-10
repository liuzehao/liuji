package com.genghis.leo.demotion;

import com.genghis.leo.demotion.model.StuGrade;
import com.genghis.leo.demotion.model.StuInfo;
import com.genghis.leo.demotion.model.StuPlan;
import com.genghis.leo.demotion.model.StuWrong;
import com.genghis.leo.demotion.service.StuService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hao pc on 2016/9/24.
 */
@Controller
public class ExcelController {

    private String stu_info = "20140045";

    @Autowired
    private StuService stuService;

    @RequestMapping(value = "/excel/getExcel", method = {RequestMethod.GET})
    public String getExcelPage() {
        return "admin/excel/addExcelDo";
    }

    //导出不及格且没有被替换的课程
    @RequestMapping("/exportFailCoursePage")
    public String exportFailCoursePage(){
        return "/admin/excel/exportFailCoursePage";
    }

    @RequestMapping("/exportFailCourse")
    @ResponseBody
    public String exportFailCourse(String path){
        List<StuGrade> failCourses = stuService.getFailCourseWOReplace();
        File exportFile = new File(path);
        if (exportFile.exists())
            exportFile.delete();
        // 打开文件
        WritableWorkbook book;
        try {
            book = Workbook.createWorkbook(exportFile);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet1", 0);
            exportFailCourseFirstRow(sheet);
            exportFailCourseOtherRow(sheet, failCourses);
            book.write();
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        } catch (RowsExceededException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        } catch (WriteException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        }

        return "{\"msg\":\"success\"}";
    }

    public void exportFailCourseOtherRow(WritableSheet sheet, List<StuGrade> failCourses) throws WriteException {
        Label label;
        for(int i = 1; i <= failCourses.size(); i++){
            StuGrade stuGrade = failCourses.get(i-1);
            label = new Label(0, i, stuGrade.getSno()==null?"":stuGrade.getSno());
            sheet.addCell(label);
            label = new Label(1, i, stuGrade.getName()==null?"":stuGrade.getName());
            sheet.addCell(label);
            label = new Label(2, i, stuGrade.getSemester()==null?"":stuGrade.getSemester());
            sheet.addCell(label);
            label = new Label(3, i, stuGrade.getCno()==null?"":stuGrade.getCno());
            sheet.addCell(label);
            label = new Label(4, i, stuGrade.getCourseNo()==null?"":stuGrade.getCourseNo());
            sheet.addCell(label);
            label = new Label(5, i, stuGrade.getCourseName());
            sheet.addCell(label);
            label = new Label(6, i, stuGrade.getUsualGrade()==null?"":stuGrade.getUsualGrade());
            sheet.addCell(label);
            label = new Label(7, i, stuGrade.getExperGrade()==null?"":stuGrade.getExperGrade());
            sheet.addCell(label);
            label = new Label(8, i, stuGrade.getTotalGrade()==null?"":stuGrade.getTotalGrade());
            sheet.addCell(label);
            label = new Label(9, i, stuGrade.getGradeId()==null?"":stuGrade.getGradeId());
            sheet.addCell(label);
            label = new Label(10, i, stuGrade.getCoursePro()==null?"":stuGrade.getCoursePro());
            sheet.addCell(label);
            label = new Label(11, i, stuGrade.getCourseAttr()==null?"":stuGrade.getCourseAttr());
            sheet.addCell(label);
            label = new Label(12, i, stuGrade.getPeriod()==null?"":stuGrade.getPeriod());
            sheet.addCell(label);
            label = new Label(13, i, String.valueOf(stuGrade.getCredit()));
            sheet.addCell(label);
            label = new Label(14, i, stuGrade.getGpa()==null?"":stuGrade.getGpa());
            sheet.addCell(label);
            label = new Label(15, i, stuGrade.getCourseDepart()==null?"":stuGrade.getCourseDepart());
            sheet.addCell(label);
            label = new Label(16, i, stuGrade.getExamPro()==null?"":stuGrade.getExamPro());
            sheet.addCell(label);
        }
    }

    /**
     * excel导出第一行
     * @param sheet
     * @throws WriteException
     */
    public void exportFailCourseFirstRow(WritableSheet sheet) throws WriteException {
        Label label;
        label = new Label(0, 0, "学号");
        sheet.addCell(label);
        label = new Label(1, 0, "姓名");
        sheet.addCell(label);
        label = new Label(2, 0, "学期");
        sheet.addCell(label);
        label = new Label(3, 0, "班级");
        sheet.addCell(label);
        label = new Label(4, 0, "课程号");
        sheet.addCell(label);
        label = new Label(5, 0, "课程名称");
        sheet.addCell(label);
        label = new Label(6, 0, "平时成绩");
        sheet.addCell(label);
        label = new Label(7, 0, "实验成绩");
        sheet.addCell(label);
        label = new Label(8, 0, "总成绩");
        sheet.addCell(label);
        label = new Label(9, 0, "成绩标识");
        sheet.addCell(label);
        label = new Label(10, 0, "课程性质");
        sheet.addCell(label);
        label = new Label(11, 0, "课程属性");
        sheet.addCell(label);
        label = new Label(12, 0, "学时");
        sheet.addCell(label);
        label = new Label(13, 0, "学分");
        sheet.addCell(label);
        label = new Label(14, 0, "绩点");
        sheet.addCell(label);
        label = new Label(15, 0, "开课学院");
        sheet.addCell(label);
        label = new Label(16, 0, "考试性质");
        sheet.addCell(label);
    }



    //导出不及格信息到excel
    @RequestMapping("/exportExcelPage")
    public String exportExcelPage(){
        return "/admin/excel/exportExcelPage";
    }



    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(String path){
        List<StuWrong> list = stuService.getAllStuWrong();
        File exportFile = new File(path);
        if (exportFile.exists())
            exportFile.delete();
        // 打开文件
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(exportFile);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet1", 0);
            exportWrongFirstRow(sheet);
            exportWrongOtherRow(sheet, list);
            book.write();
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        } catch (RowsExceededException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        } catch (WriteException e) {
            e.printStackTrace();
            return "{\"msg\":\"error\"}";
        }

        return "{\"msg\":\"success\"}";
    }



    public void exportWrongOtherRow(WritableSheet sheet, List<StuWrong> list) throws WriteException {
        Label label;
        for(int i = 1; i <= list.size(); i++){
            StuWrong stuWrong = list.get(i-1);
            label = new Label(0, i, stuWrong.getSno());
            sheet.addCell(label);
            label = new Label(1, i, stuWrong.getName());
            sheet.addCell(label);
            label = new Label(2, i, stuWrong.getCno());
            sheet.addCell(label);
            label = new Label(3, i, String.valueOf(stuWrong.getGrade1()));
            sheet.addCell(label);
            label = new Label(4, i, stuWrong.isValidGrade1() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(5, i, String.valueOf(stuWrong.getGrade2()));
            sheet.addCell(label);
            label = new Label(6, i, stuWrong.isValidGrade2() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(7, i, String.valueOf(stuWrong.getGrade3()));
            sheet.addCell(label);
            label = new Label(8, i, stuWrong.isValidGrade3() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(9, i, String.valueOf(stuWrong.getGrade4()));
            sheet.addCell(label);
            label = new Label(10, i, stuWrong.isValidGrade4() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(11, i, String.valueOf(stuWrong.getGrade5()));
            sheet.addCell(label);
            label = new Label(12, i, stuWrong.isValidGrade5() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(13, i, String.valueOf(stuWrong.getGrade6()));
            sheet.addCell(label);
            label = new Label(14, i, stuWrong.isValidGrade6() ? "有效" : "无效");
            sheet.addCell(label);
            label = new Label(15, i, String.valueOf(stuWrong.getTolscore()));
            sheet.addCell(label);
            label = new Label(16, i, stuWrong.isDegrade() ? "是" : "否");
            sheet.addCell(label);
            label = new Label(17, i, stuWrong.isPeohandle() ? "是" : "否");
            sheet.addCell(label);
        }
    }

    /**
     * excel导出不及格信息第一行
     * @param sheet
     * @throws WriteException
     */
    public void exportWrongFirstRow(WritableSheet sheet) throws WriteException {
        Label label;
        label = new Label(0, 0, "学号");
        sheet.addCell(label);
        label = new Label(1, 0, "姓名");
        sheet.addCell(label);
        label = new Label(2, 0, "班级");
        sheet.addCell(label);
        label = new Label(3, 0, "第一学年");
        sheet.addCell(label);
        label = new Label(4, 0, "第一学年是否有效");
        sheet.addCell(label);
        label = new Label(5, 0, "第二学年");
        sheet.addCell(label);
        label = new Label(6, 0, "第二学年是否有效");
        sheet.addCell(label);
        label = new Label(7, 0, "第三学年");
        sheet.addCell(label);
        label = new Label(8, 0, "第三学年是否有效");
        sheet.addCell(label);
        label = new Label(9, 0, "第四学年");
        sheet.addCell(label);
        label = new Label(10, 0, "第四学年是否有效");
        sheet.addCell(label);
        label = new Label(11, 0, "第五学年");
        sheet.addCell(label);
        label = new Label(12, 0, "第五学年是否有效");
        sheet.addCell(label);
        label = new Label(13, 0, "第六学年");
        sheet.addCell(label);
        label = new Label(14, 0, "第六学年是否有效");
        sheet.addCell(label);
        label = new Label(15, 0, "总不及格学分");
        sheet.addCell(label);
        label = new Label(16, 0, "是否降级");
        sheet.addCell(label);
        label = new Label(17, 0, "是否需要人工操作");
        sheet.addCell(label);
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)//上传Excel
    public
    @ResponseBody
    HashMap<String, String> uploadFile(@RequestParam(value = "input", required = true) MultipartFile file, int fileType, String createYear) throws IOException, BiffException {

        //createYear = new String(createYear.getBytes("ISO-8859-1"), "UTF-8");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("false", " ");//标志
        if (file != null) {

            String myFileName = file.getOriginalFilename();
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            System.out.println(myFileName);//Excel已经读入内存
            map.put("true", " ");//返回成功
            //1是学生信息 2是成绩信息 3是培养方案
            switch (fileType) {
                case 1:
                    insertStuInfo(file, createYear);
                    break;
                case 2:
                    insertStuGrade(file);
                    break;
                case 3:
                    insertStuPlan(file);
                    break;
            }
        }

        return map;
    }

    /**
     * 导入培养计划
     *
     * @param file
     * @throws IOException
     * @throws BiffException
     */
    private void insertStuPlan(MultipartFile file) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        int rowNums = sheet.getRows();
        int col = 1;
        StuPlan stuPlan;
        System.out.println("导入开始");
        for (int row = 1; row < rowNums; row++) {
            stuPlan = new StuPlan();
            //学期 学院 届数 专业 课程编号 课程名称 学分 课程属性 开设单位 班级号 所属平台
            stuPlan.setSemester(sheet.getCell(col++, row).getContents());
            stuPlan.setCollege(sheet.getCell(col++, row).getContents());
            stuPlan.setTh(sheet.getCell(col++, row).getContents());
            stuPlan.setMajor(sheet.getCell(col++, row).getContents());
            stuPlan.setCourseNo(sheet.getCell(col++, row).getContents());
            stuPlan.setCourseName(sheet.getCell(col++, row).getContents());
            stuPlan.setCredit(Float.valueOf(sheet.getCell(col++, row).getContents()));
            stuPlan.setCourseAttr(sheet.getCell(col++, row).getContents());
            stuPlan.setCourseDepart(sheet.getCell(col++, row).getContents());
            stuPlan.setCno(sheet.getCell(col++, row).getContents());
            stuPlan.setPlatform(sheet.getCell(col, row).getContents());
            col = 1;
            stuService.setStuPlan(stuPlan);
        }
        System.out.println("导入完成");
    }

    /**
     * 导入学生成绩
     *
     * @param file
     * @throws IOException
     * @throws BiffException
     */
    private void insertStuGrade(MultipartFile file) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        int rowNums = sheet.getRows();
        int col = 0;
        StuGrade stuGrade;
        System.out.println("导入开始");
        for (int row = 1; row < rowNums; row++) {
            if (sheet.getCell(col, row).getContents().equals(""))
                break;

            //用于测试
            //if (sheet.getCell(col, row).getContents().equals(stu_info)) {
            stuGrade = new StuGrade();
            //学号 姓名 开课学期 班级 课程编号 课程名称 平时成绩 实验成绩 总成绩 成绩标志 课程性质 课程属性 学时 学分 绩点 开课单位 录入人 考试性质 重补学期
            stuGrade.setSno(sheet.getCell(col++, row).getContents());
            stuGrade.setName(sheet.getCell(col++, row).getContents());
            stuGrade.setSemester(sheet.getCell(col++, row).getContents());
            stuGrade.setCno(sheet.getCell(col++, row).getContents());
            stuGrade.setCourseNo(sheet.getCell(col++, row).getContents());
            stuGrade.setCourseName(sheet.getCell(col++, row).getContents());
            stuGrade.setUsualGrade(sheet.getCell(col++, row).getContents());
            stuGrade.setExperGrade(sheet.getCell(col++, row).getContents());
            stuGrade.setTotalGrade(convertTotalGrade(sheet.getCell(col++, row).getContents()));
            stuGrade.setGradeId(sheet.getCell(col++, row).getContents());
            stuGrade.setCoursePro(sheet.getCell(col++, row).getContents());
            stuGrade.setCourseAttr(sheet.getCell(col++, row).getContents());
            stuGrade.setPeriod(sheet.getCell(col++, row).getContents());
            stuGrade.setCredit(Float.valueOf(sheet.getCell(col++, row).getContents()));

            stuGrade.setGpa(sheet.getCell(col++, row).getContents());
            stuGrade.setCourseDepart(sheet.getCell(col++, row).getContents());
            stuGrade.setTypeMan(sheet.getCell(col++, row).getContents());
            stuGrade.setExamPro(sheet.getCell(col++, row).getContents());
            stuGrade.setReSemester(sheet.getCell(col++, row).getContents());
            stuGrade.setRemark(sheet.getCell(col++, row).getContents());
            stuGrade.setStatus(sheet.getCell(col, row).getContents());
            col = 0;
            stuService.setStuGrade(stuGrade);
            //}
        }
        System.out.println("导入完成");
    }

    private String convertTotalGrade(String totalGrade){
        if (totalGrade.equals("优")){
            return "95";
        }else if(totalGrade.equals("良")){
            return "85";
        }else if(totalGrade.equals("中")){
            return "75";
        }else if(totalGrade.equals("及格")){
            return "65";
        }else if(totalGrade.equals("不及格")){
            return "0";
        }else {
            return totalGrade;
        }
    }


    /**
     * 导入学生信息
     *
     * @param file
     * @throws IOException
     * @throws BiffException
     */
    private void insertStuInfo(MultipartFile file, String createYear) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        int rowNums = sheet.getRows();
        int col = 0;
        StuInfo stuInfo;
        System.out.println("导入开始");
        for (int row = 1; row < rowNums; row++) {
            System.out.printf("1");
            //用于测试
//            String sno = sheet.getCell(col, row).getContents();
//            if (sno.equals(stu_info)){
                stuInfo = new StuInfo();
                //学号 姓名 学院 专业 专业方向 班级 学籍 入学时间
                stuInfo.setSno(sheet.getCell(col++, row).getContents());
                stuInfo.setName(sheet.getCell(col++, row).getContents());
                stuInfo.setCollege(sheet.getCell(col++, row).getContents());
                stuInfo.setMajor(sheet.getCell(col++, row).getContents());
                //stuInfo.setMajorField(sheet.getCell(col++, row).getContents());
                stuInfo.setCno(sheet.getCell(col++, row).getContents());
                stuInfo.setRollStatus(sheet.getCell(col++, row).getContents());
                //stuInfo.setEnterDate(sheet.getCell(col, row).getContents());
                //stuInfo.setCreateYear(createYear);
                col = 0;
                stuService.setStuInfo(stuInfo);
//            }
        }
        System.out.println("导入完成");
    }
}
