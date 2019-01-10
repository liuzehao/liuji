<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: panzhuowen
  Date: 2014/10/17
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>留级学生管理</title>
    <%@include file="../../includes/common.jsp"%>
    <script src="<c:url value="/js/admin/systemUser.js"/>" type="text/javascript"></script>
    <script>
        function computeFail(){
            window.location.href = basePath+"stuwrong/computeFail"
        }
    </script>
</head>
<body class="page-header-fixed">
<%@include file="../../includes/top.jsp"%>
<%@include file="../../includes/top.jsp" %>
<div class="page-container" data-options="tools:'#tab-tools'">
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <jsp:include page="../../includes/menu.jsp">
                <jsp:param name="permissionNum" value="1"/>
                <jsp:param name="permissionLeafNum" value="1"/>
            </jsp:include>
        </div>
    </div>
    <div class="page-content-wrapper">
        <div class="page-content" style="min-height:1033px !important" >
            <!-- BEGIN PAGE HEADER-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title"></h3>
                    <ul class="page-breadcrumb breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <span>主页</span>
                            <i class="fa fa-angle-right"></i>
                        </li>
                        <li>
                            <span>留级学生管理</span>
                            <i class="fa fa-angle-right"></i>
                        </li>
                        <li>
                            <span></span>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12">
                    <!-- Begin: life time stats -->
                    <div class="portlet box grey-cascade">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-user"></i>
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                            </div>
                            <div class="actions">
                                <a href="#" onclick="userListTable.deleteStuInfo()" class="btn btn-default" > 删除学生信息</a>
                                <a href="#" onclick="userListTable.deleteStuGrade()" class="btn btn-default" > 删除无效成绩信息</a>
                                <a href="#" onclick="userListTable.deleteStuPlan()" class="btn btn-default" > 删除无效培养计划</a>
                                <a href="#" onclick="userListTable.deleteStuDegrade()" class="btn btn-default" > 删除留降级记录</a>

                                <a href="#" onclick="userListTable.addUserInfo()" class="btn btn-default" id="datatable_ajax_new"><i class="fa fa-plus faa-shake animated-hover"></i> 导入EXCEL</a>
                                <a href="#" onclick="computeFail()" class="btn btn-default" ><i class="fa fa-bar-chart-o faa-shake animated-hover"></i> 计算留降级</a>
                                <a href="#" onclick="userListTable.exportExcel()" class="btn btn-default" ><i class="fa  fa-arrow-circle-o-up faa-spin animated-hover"></i> 导出EXCEL</a>
                                <a href="#" onclick="userListTable.exportFailCourse()" class="btn btn-default" ><i class="fa  fa-arrow-circle-o-up faa-spin animated-hover"></i> 导出不及格课程</a>

                                <%--<a href="#" class="btn btn-default" id="reloadTable"><i class="fa fa-refresh faa-spin animated-hover"></i> 重新载入</a>--%>
                                <%--<div class="btn-group">--%>
                                    <%--<a class="btn btn-default" href="#" data-toggle="dropdown">--%>
                                        <%--<i class="fa fa-columns faa-shake animated-hover "></i>--%>
                                        <%--选择列 <i class="fa fa-angle-down"></i>--%>
                                    <%--</a>--%>
                                    <%--<div id="datatable_ajax_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">--%>
                                        <%--<label><input type="checkbox" checked data-column="0">学号</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="1">姓名</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="2">班级</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="3">第一学年</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="4">第二学年</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="5">第三学年</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="6">第四学年</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="7">第五学年</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="8">总学分</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="9">降级</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="10">人工查对</label>--%>
                                        <%--<label><input type="checkbox" checked data-column="11">操作</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <%
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                            // public final String format(Date date)
                            String s = sdf.format(d);
                            request.setAttribute("date", s);
                        %>
                        <div class="portlet-body">
                            <div class="table-container">
                                <table id="datatable_ajax" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr role="row" class="heading">
                                        <th>学号</th>
                                        <th>姓名</th>
                                        <th>班级</th>
                                        <th>第一学年</th>
                                        <th>第二学年</th>
                                        <th>第三学年</th>
                                        <th>第四学年</th>
                                        <th>第五学年</th>
                                        <th>第六学年</th>
                                        <th>总学分</th>
                                        <th>降级</th>
                                        <th>人工查对</th>
                                        <th>学籍情况</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- End: life time stats -->
                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
    </div>
</div>
<!-- BEGIN FOOTER -->
<%@include file="../../includes/footer.jsp" %>
<%--删除学生记录--%>
<div class="modal fade" id="deleteStuInfoDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="deleteStuInfoTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="deleteStuInfoIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--删除成绩--%>
<div class="modal fade" id="deleteStuGradeDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="deleteStuGradeTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="deleteStuGradeIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--删除培养计划--%>
<div class="modal fade" id="deleteStuPlanDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="deleteStuPlanTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="deleteStuPlanIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--删除学生留级信息--%>
<div class="modal fade" id="deleteStuDegradeDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="deleteStuDegradeTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="deleteStuDegradeIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--导出Excel--%>
<div class="modal fade" id="exportExcelDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="exportExcelTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="exportExcelIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--导出Excel--%>
<div class="modal fade" id="exportFailCourseDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="exportFailCourseTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="exportFailCourseIframe" style="border: none;width:200px;height:250px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modalDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="dialogTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="userEditIframe" style="border: none;width:700px;height:650px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- END FOOTER -->
<div class="modal fade" id="deleteRowConfirm" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">确认</h4>
            </div>
            <div class="modal-body">确认删除当前行？</div>
            <div class="modal-footer">
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
                <button type="button" class="btn blue" id="deleteRow">删除</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        userListTable.init();
    });
</script>

