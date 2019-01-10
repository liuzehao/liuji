var userListTable = function () {
    var reloadTable = function (pageFlag) {
        $("#datatable_ajax").DataTable().draw(pageFlag)
    };
    var onSortColumn = function (sortColumn, sortDirection) {
        switch (sortColumn) {
            case "loginName":
                sortColumn = "login_name";
                break;
            case "username":
                sortColumn = "user_name";
                break;
            case "roleName":
                sortColumn = "role_name";
                break;
        }
        return onSortColumnDefault(sortColumn, sortDirection);
    };
    var ruleUser = {
        objInfo: {
            loginname: {
                checkEmpty: ["loginname", "账号"],
                max: ["loginname", 10, "账号"]
            },
            username: {
                checkEmpty: ["username", "用户名"],
                max: ["username", 10, "用户名"]
            },
            password: {
                checkEmpty: ["password", "密码"],
                checkRegExp: ["password", "", JCRegExp.letterNum]
            },
            roleName: {
                checkEmpty: ["roleName", "角色"]
            }

        }
    };
    /**
     * dataTable事件初始化方法
     */
    var handleRecords = function () {
        var sno = $("#sno").val();
        var grid = new DataTable();
        var $table = $("#datatable_ajax");
        grid.init({
            src: $table,
            url: basePath + "stuwrong/findStuConflictCourse?sno=" + sno,
            onSortColumn: onSortColumn,
            onQuery: function (data) {
                data.loginName = $("#logName").val();
                data.username = $("#userName").val();
                data.roleName = $("#roleName").val();
            },
            dataTable: {
                "columns": [
                    {data: 'semester', orderable: false},
                    {data: 'courseName', orderable: false},
                    {data: 'totalGrade', orderable: false},
                    {data: 'coursePro', orderable: false},
                    {data: 'period', orderable: false},
                    {data: 'credit', orderable: false},
                    {data: 'gpa', orderable: false},
                    {data: 'courseDepart', orderable: false},
                    {data: 'examPro', orderable: false},
                    {data: 'operate', orderable: false,
                        render: function (data, type, full) {
                            var returnValue = '<a class="edit btn default btn-xs red" onclick="userListTable.editUserInfo('+sno+',\''+full.courseName+'\''+',\''+full.name+'\''+',\''+full.totalGrade+'\''+',\''+full.credit+'\''+',\''+full.cno+'\''+')"><i class="fa fa-edit faa-shake animated-hover"></i>替换</a>&nbsp;';
                            returnValue = returnValue+'<a class="edit btn default btn-xs purple" href="/leo/stuwrong/addCourse?sno='+full.sno + '&name=' + full.name + '&courseName=' + full.courseName + '\"><i class="fa fa-edit faa-shake animated-hover"></i>加入</a>&nbsp;';
                            returnValue = returnValue+'<a class="edit btn default btn-xs grey" href="/leo/stuwrong/ignore?sno='+full.sno + '&name=' + full.name + '&courseName=' + full.courseName + '\"><i class="fa fa-edit faa-shake animated-hover"></i>忽略</a>&nbsp;';
                            return returnValue;
                        }
                        //href="/leo/admin/user/replaceCourse?id='+full.sno+'\"
                    }
                ]
            }
        });


        //重新载入按钮事件
        $("#reloadTable").click(function () {
            reloadTable(false);
        });
//查询事件

        $("#search").click(function () {
            var attribute;
            var value;
            $("#searchTable td input").each(function () {
                if ($(this).val()) {
                    attribute = $(this).attr("id");
                    value = $(this).val();
                }
            });
            $.ajax({
                url: basePath + "stuwrong/searchStuWrong",
                type: "POST",
                data: {
                    attribute: "sno",
                    value: "1"
                },
                success: function (json) {
                    $("#datatable_ajax").DataTable().ajax.reload(function (json) {
                        //这里的json返回的是服务器的数据
                        $('#myInput').val(json.lastInput);
                    }, false);
                },
                error: doError
            })
        });
        //显示列选择按钮事件
        $('#datatable_ajax_column_toggler').find('input[type="checkbox"]').change(function () {
            var iCol = parseInt($(this).attr("data-column"));
            $table.DataTable().column(iCol).visible(!$table.DataTable().column(iCol).visible());
        });
        //行删除按钮事件
        $table.find('a.delete').live('click', function () {
            var $this = $(this);
            //confirm中确认按钮事件，此处需要unbind，否则点击取消时下次再点击删除按钮会重复绑定click。
            $("#deleteRow").unbind("click.deleteRow").bind("click.deleteRow", function () {
                var $row = $table.DataTable().row($this.parents('tr')[0]);
                $.ajax({
                    url: basePath + "admin/user/delUserDo?id=" + $row.data().userId,
                    dataType: "json",
                    type: "DELETE",
                    success: function () {
                        $table.DataTable().draw();
                        $('#deleteRowConfirm').modal('hide');
                        toast.success();
                    },
                    error: doError
                })
            })
        });

    };

    return {
        init: function () {
            handleRecords();
        },
        addUserInfo: function () {
            $("#dialogTitle").text("导入EXCEL");
            $("#userEditIframe").attr("src", basePath + "excel/getExcel");
            $("#modalDialog").modal("show");
        },
        //替换
        editUserInfo: function (id, courseName, name, totalGrade, credit, cno) {
            $("#dialogTitle").text("替换");
            $("#userEditIframe").attr("src", basePath + "admin/user/replaceCourse?id=" + id +"&courseName="+courseName+"&name="+name+"&totalGrade="+totalGrade+"&credit="+credit+"&cno="+cno);
            $("#modalDialog").modal("show");
        },
//加入事件
        addClass: function (id) {
            $("#dialogTitleAdd").text("是否直接加入课程？");
            $("#modalDialogAdd").modal("show");
        },
        //忽略
        ignoreClass: function (id) {
            $("#dialogTitleIg").text("是否忽略课程？");
            $("#modalDialogIg").modal("show");
        },
        quit: function () {
            location.href = "about:blank";
            parent.parent.$("#modalDialog").modal("hide");
        },
        quitAdd: function () {

            parent.parent.$("#modalDialogAdd").modal("hide");
        },
        quitIg: function () {

            parent.parent.$("#modalDialogIg").modal("hide");
        },
        editSave: function () {
            var ruleEditUser = $.extend(true, {}, ruleUser, {
                objInfo: {
                    loginName: {
                        ajax: [basePath + "admin/user/validateUserName", {}, {userId: "$('#checkId').val().trim()", loginName: "$('#loginname').val().trim()"},
                            backFunc, "text", "POST"]
                    }
                }
            });
            if (!JC.validate(ruleEditUser)) return;
            $("#saves").button('loading');
            $.ajax({
                url: basePath + "admin/user/editUser",
                data: {
                    userId: $("#checkId").val(),
                    loginName: $("#loginname").val(),
                    username: $("#username").val(),
                    password: $("#password").val() == $("#oldPassword").val() ? $("#oldPassword").val() : $("#password").val().md5(),
                    roleId: $("#roleName").getJcAutoCompleteHiddenValue() == undefined ? $("#roleId").val() : $("#roleName").getJcAutoCompleteHiddenValue()
                },
                dataType: "json",
                type: "POST",
                success: function () {
                    location.href = "about:blank";
                    parent.parent.$("#modalDialog").modal("hide");
                    var $table = parent.$("#datatable_ajax");
                    $table.DataTable().draw();
                    parent.toast.success();
                },
                error: doError
            })
        },
        addSave: function () {
            var ruleAddUser = $.extend(true, {}, ruleUser, {
                objInfo: {
                    loginName: {
                        ajax: [basePath + "admin/user/validateUserName", {}, {loginName: "$('#loginname').val().trim()"},
                            backFunc, "text", "POST"]
                    }
                }
            });
            if (!JC.validate(ruleAddUser)) return;
            $("#saves").button('loading');
            $.ajax({
                url: basePath + "admin/user/addUser",
                data: {
                    loginName: $("#loginname").val(),
                    username: $("#username").val(),
                    password: $("#password").val().md5(),
                    roleId: $("#roleName").getJcAutoCompleteHiddenValue()
                },
                dataType: "json",
                type: "POST",
                success: function () {
                    location.href = "about:blank";
                    parent.parent.$("#modalDialog").modal("hide");
                    var $table = parent.$("#datatable_ajax");
                    $table.DataTable().draw();
                    parent.toast.success();
                },
                error: doError
            })
        },
        reloadTable: reloadTable
    };


    function backFunc(data, returnObj) {
        returnObj.obj = $('#loginname')
        returnObj.errMsg = "此账号已存在！"

        if (data == "true") {
            returnObj.bool = true;
        }
        if (data == "false") {
            returnObj.bool = false;
        }

        return returnObj;
    }

}();