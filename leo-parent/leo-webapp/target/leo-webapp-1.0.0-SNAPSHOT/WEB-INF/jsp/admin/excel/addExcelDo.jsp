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
    <script src="<c:url value="/assets/global/plugins/jquery-1.11.0.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/common.js"/>" type="text/javascript"></script>
    <link href="<c:url value="/assets/global/plugins/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/assets/global/plugins/bootstrap-fileinput-master/css/fileinput.css"/>" rel="stylesheet"
          type="text/css"/>
    <script src="<c:url value="/assets/global/plugins/bootstrap-fileinput-master/js/fileinput.js"/>"
            type="text/javascript"></script>
    <script src="<c:url value="/assets/global/plugins/bootstrap-fileinput-master/js/plugins/canvas-to-blob.js"/>"
            type="text/javascript"></script>
    <script src="<c:url value="/assets/global/plugins/bootstrap-fileinput-master/js/fileinput_locale_zh.js"/>"
            type="text/javascript"></script>
    <title>333</title>
</head>
<style>
    .oo {
        border: 1px solid #F00;
        width: 600px;
        height: 100px;
        margin-bottom: 100px;
    }
</style>
<%
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    // public final String format(Date date)
    String s = sdf.format(d);
    request.setAttribute("date", s);
%>
<div>
    <select id="createYear" class="form-control" style="height: 40px;width:600px;">
        <option>${date}-${date-1}</option>
        <option>${date-1}-${date-2}</option>
        <option>${date-2}-${date-3}</option>
        <option>${date-3}-${date-4}</option>
        <option>${date-4}-${date-5}</option>
    </select>
</div>
<div class="oo">
    <input id="input-705" name="input" type="file" multiple class="file-loading">
</div>

<div class="oo">
    <input id="input2" name="input" type="file" multiple class="file-loading">
</div>

<div class="oo">
    <input id="input3" name="input" type="file" multiple class="file-loading">
</div>


</body>
<script>
    var createYear = $("#createYear").val();
    $("#createYear").change(function () {
        createYear = $("#createYear").val();
    });

    var $input = $("#input-705");
    $input.fileinput({
        uploadUrl: basePath+"uploadFile?fileType=2",
        maxFileCount: 1,
        language: 'zh',
        dropZoneTitle: '学生成绩',
        uploadAsync: false,
        showUpload: false, // hide upload button
        showRemove: false, // hide remove button
        minFileCount: 1,
        maxFileCount: 1
    }).on("filebatchselected", function (event, files) {
        // trigger upload method immediately after files are selected
        $input.fileinput("upload");
    });

    var $input2 = $("#input2");
    $input2.fileinput({
        uploadUrl: basePath+"uploadFile?fileType=3", // server upload action http://localhost:8080/bits/  http://111.116.20.180:20482/bits/ 192.168.31.15
        maxFileCount: 1,
        dropZoneTitle: '培养方案',
        language: 'zh',
        uploadAsync: false,
        showUpload: false, // hide upload button
        showRemove: false, // hide remove button
        minFileCount: 1,
        maxFileCount: 10
    }).on("filebatchselected", function (event, files) {
        // trigger upload method immediately after files are selected
        $input2.fileinput("upload");
    });

    var $input3 = $("#input3");
    $input3.fileinput({
        uploadUrl: basePath+"uploadFile?fileType=1&createYear=" + createYear, // server upload action http://localhost:8080/bits/  http://111.116.20.180:20482/bits/ 192.168.31.15
        maxFileCount: 1,
        dropZoneTitle: '学生信息',
        language: 'zh',
        uploadAsync: false,
        showUpload: false, // hide upload button
        showRemove: false, // hide remove button
        minFileCount: 1,
        maxFileCount: 10
    }).on("filebatchselected", function (event, files) {
        // trigger upload method immediately after files are selected
        $input3.fileinput("upload");
    });

</script>
</html>

