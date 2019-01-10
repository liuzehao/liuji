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
    <title>冲突课程详情</title>
    <%@include file="../../includes/common.jsp"%>
    <script src="<c:url value="/js/admin/conflict.js"/>" type="text/javascript"></script>
    <script>
    function computeFail(){
        window.location.href = "http://localhost:8080/leo/stuwrong/computeFail"
    }
    </script>
</head>
<body class="page-header-fixed">
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
                            <span>冲突课程详情</span>
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
                                <i class="fa fa-user"></i>${name}
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                            </div>
                            <div class="actions">

                                <div class="btn-group">
                                    <a class="btn btn-default" href="#" data-toggle="dropdown">
                                        <i class="fa fa-columns faa-shake animated-hover "></i>
                                        选择列 <i class="fa fa-angle-down"></i>
                                    </a>
                                    <input id="sno" type="hidden" value="${id}"/>
                                    <div id="datatable_ajax_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                        <label><input type="checkbox" checked data-column="0">学期</label>
                                        <label><input type="checkbox" checked data-column="1">课程</label>
                                        <label><input type="checkbox" checked data-column="2">总成绩</label>
                                        <label><input type="checkbox" checked data-column="3">课程性质</label>
                                        <label><input type="checkbox" checked data-column="4">课时</label>
                                        <label><input type="checkbox" checked data-column="5">学分</label>
                                        <label><input type="checkbox" checked data-column="6">绩点</label>
                                        <label><input type="checkbox" checked data-column="7">学院</label>
                                        <label><input type="checkbox" checked data-column="8">考试性质</label>

                                        <label><input type="checkbox" checked data-column="9">操作</label>
                                    </div>
                                </div>
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
                                        <th>学期</th>
                                        <th>课程</th>
                                        <th>总成绩</th>
                                        <th>课程性质</th>
                                        <th>课时</th>
                                        <th>学分</th>
                                        <th>绩点</th>
                                        <th>学院</th>
                                        <th>考试性质</th>
                                        <th>操作</th>

                                    </tr>
                                    <!--
                                    <tr role="row" class="filter" id="searchTable">
                                        <td>
                                            <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                                   id="sno" placeholder="学号">
                                        </td>
                                        <td>
                                            <input type="text" class="form-control form-filter input-sm" name="usernameQuery"
                                                   id="name" placeholder="姓名">
                                        </td>
                                        <td>
                                            <input type="text" class="form-control form-filter input-sm" name="emailQuery"
                                                   id="cno" placeholder="班级">
                                        </td>
                                        <td>
                                            <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                                   id="grade5" placeholder="单年学分">
                                        </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="grade4" placeholder="单年学分">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="grade3" placeholder="单年学分">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="grade2" placeholder="单年学分">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="grade1" placeholder="单年学分">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="tolscore" placeholder="累计学分">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="degrade" placeholder="是/否">
                                    </td>
                                        <td>
                                        <input type="text" class="form-control form-filter input-sm" name="nameQuery"
                                               id="peohandle" placeholder="是/否">
                                    </td>
                                        <td>
                                            <div class="margin-bottom-5">
                                                <a class=" btn default btn-xs yellow" id="search">
                                                    <i class="fa fa-search faa-shake animated-hover"></i>查询</a>
                                            </div>
                                        </td>
                                    </tr>
                                    -->
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
<div class="modal fade" id="modalDialog" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 450px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="dialogTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body">
                    <iframe id="userEditIframe" style="border: none;width:400px;" frameBorder="0"></iframe>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modalDialogAdd" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="dialogTitleAdd"></h4>
            </div>
            <div class="modal-body">
                <button type="button" class="btn btn-primary">是</button>
                <button type="button" onclick="userListTable.quitAdd()"class="btn btn-danger">否</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modalDialogIg" tabindex="-1" role="basic" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="dialogTitleIg"></h4>
            </div>
            <div class="modal-body">
                <button  type="button" class="btn btn-primary">是</button>
                <button type="button" onclick="userListTable.quitIg()"class="btn btn-danger">否</button>
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

