<%--
  Created by IntelliJ IDEA.
  User: dabai
  Date: 2016/10/4
  Time: 下午17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <%@include file="../../includes/common.jsp" %>
    <script type="text/javascript">

        $(document).ready(function () {

            var sno = $("#sno").val();
            var courseName = $("#courseName").val();
            var name = $("#name").val();
            var totalGrade = $("#totalGrade").val();
            var oldCredit = $("#oldCredit").val();
            var cno = $("#cno").val();

            $.ajax({
                url: basePath + "stuwrong/replaceItem?sno=" + sno + "&courseName=" + courseName + "&cno=" + cno,
                type: 'GET',
                success: function (data) {
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            //'<option value="'+'">'+'</option>'
                            var replaceContent = '<option value="';
                            if (data[i].sno == null) {
                                replaceContent += false + '">' + data[i].semester + '/' + data[i].courseName + '/无法匹配到培养计划';
                            } else {
                                replaceContent += true + '">' + data[i].semester + '/' + data[i].courseName + '/' + data[i].totalGrade;
                            }
                            replaceContent += '</option>';

                            $("#replaceItem").append(replaceContent);
//                            $("#replaceItem").append('<li role="presentation"><a role="menuitem" tabindex="-1" href="'
//                                    +basePath+'stuwrong/replace?sno='+sno+'&name='+name+'&courseName='+data[i].courseName+'&oldCourseName='+courseName+'&totalGrade='+data[i].totalGrade+'&oldTotalGrade='+totalGrade+'&oldCredit='+oldCredit+'">'
//                                    +data[i].semester+'/'+data[i].courseName+'/'+data[i].totalGrade+'</a></li>');
                        }
                    }
                },
                error: function (data) {
                    alert("请求错误，请重试");
                }
            });

            $("#replace").click(function(){
                var sno = $("#sno").val();
                var courseName = $("#courseName").val();
                var name = $("#name").val();
                var totalGrade = $("#totalGrade").val();
                var oldCredit = $("#oldCredit").val();
                var cno = $("#cno").val();

                var selectedReplaceItem = $("#replaceItem").find("option:selected").text();
                var semester = selectedReplaceItem.split("/")[0];
                var selectedCourseName = selectedReplaceItem.split("/")[1];
                var score = selectedReplaceItem.split("/")[2];
                var canReplace = true;
                var hasGrade = $("#replaceItem").find("option:selected").val();

                if (score == "无法匹配到培养计划"){
                    if($("#score").val()==null || $("#score").val() == ""){
                        canReplace = false;
                        alert("请输入所选择的课程成绩");
                    }else{
                        score = $("#score").val();
                    }
                }
                if(canReplace){
                    $.ajax({
                        //sno学号  name姓名 courseName当前选择的这门课的名称
                        url: basePath+'stuwrong/replace?semester='+semester+'&hasGrade='+hasGrade+'&sno='+sno+'&name='+name+'&courseName='+selectedCourseName+'&oldCourseName='+courseName+'&totalGrade='+score+'&oldTotalGrade='+totalGrade+'&oldCredit='+oldCredit,
                        type:"GET",
                        dataType:"JSON",
                        success:function(data){
                            if (data.msg == "error"){
                                alert("替换失败，请检查条件后重试");
                            }else if(data.msg == "success"){
                                alert("成功")
                            }
                        },
                        error:function(){
                            alert("替换失败，请重试");
                        }
                    })
                }
            });

        });
    </script>
</head>
<body style="background: #ffffff">
<div class="dropdown">
    <input id="sno" type="hidden" value="${sno}"/>
    <input id="courseName" type="hidden" value="${courseName}"/>
    <input id="name" type="hidden" value="${name}"/>
    <input id="totalGrade" type="hidden" value="${totalGrade}"/>
    <input id="oldCredit" type="hidden" value="${oldCredit}"/>
    <input id="cno" type="hidden" value="${cno}"/>

    <div>
        <select id="replaceItem">
            <option value="">请选择</option>
        </select>
    </div>
    <div>
        <input id="score" type="text"/>
    </div>
    <div>
        <button id="replace" type="button" class="btn btn-danger">确认替换</button>
    </div>

</div>
</body>
</html>
