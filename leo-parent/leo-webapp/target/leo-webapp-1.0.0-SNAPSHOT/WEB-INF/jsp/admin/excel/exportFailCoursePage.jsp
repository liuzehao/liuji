<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: hao pc
  Date: 2016/3/23
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<html>
<head>
    <script src="<c:url value="/js/common.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/assets/global/plugins/jquery-1.11.0.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/assets/global/plugins/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <link href="<c:url value="/assets/global/plugins/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript">
        function exportExcel() {
            var path = $("#path").val();
            $.ajax({
                url: basePath + "/exportFailCourse?path=" + path,
                type: "GET",
                success: function (data) {
                    alert("导出成功");
                },
                error: function (data) {
                    alert("导出失败，请重试");
                }
            })
        }
    </script>
</head>
<body>

<%
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    // public final String format(Date date)
    String s = sdf.format(d);
    request.setAttribute("date", s);
%>

<div class="form-group">
    <input type="text" value="C:\Users\db\Desktop\" class="form-control" id="path" placeholder="请输入文件路径及文件名">
</div>
<button onclick="exportExcel()" type="button" class="btn btn-default">确定</button>


</body>
</html>

