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
        function deleteStuPlan() {
            var level = $("#level").val();
            $.ajax({
                url: basePath + "/deleteStuPlan?level=" + level,
                type: "GET",
                success: function (data) {
                    alert("删除成功")
                },
                error: function (data) {
                    alert("删除失败，请重试");
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

<select id="level">
    <option value="${date-8}-${date-7}">${date-8}-${date-7}学年</option>
    <option value="${date-7}-${date-6}">${date-7}-${date-6}学年</option>
    <option value="${date-6}-${date-5}">${date-6}-${date-5}学年</option>
    <option value="${date-5}-${date-4}">${date-5}-${date-4}学年</option>
    <option value="${date-4}-${date-3}">${date-4}-${date-3}学年</option>
    <option value="${date-3}-${date-2}">${date-3}-${date-2}学年</option>
    <option value="${date-2}-${date-1}">${date-2}-${date-1}学年</option>
    <option value="${date-1}-${date}">${date-1}-${date}学年</option>
</select>

<div>
    <button onclick="deleteStuPlan()" type="button" class="btn btn-danger">确认删除</button>
</div>

</body>
</html>

