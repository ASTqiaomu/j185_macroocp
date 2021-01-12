var list, trs = "";

function getAllRoles() {
    $.ajax({
        type: "GET",
        url: "getAllRoles",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                trs = "";
                for (var i = 0; i < list.length; i++) {
                    trs += '<tr id="' + list[i].rid + '">' +
                        '   <td>' +
                        '       <input type="checkbox" id="check_th' + list[i].rid + '" name="check" onclick="checkOne()">' +
                        '       <label class="check_th" for="check_th' + list[i].rid + '"></label>' +
                        '   </td>' +
                        '   <td>' + list[i].rid + '</td>' +
                        '   <td>' + list[i].rname + '</td>' +
                        '   <td>' + list[i].rdescription + '</td>' +
                        '   <td>' + ((list[i].rstatus === 1) ? "启用" : "禁用") + '</td>' +
                        '   <td class="td-operation no-select">' +
                        '   <div>' +
                        '       <a href="#"><i class="fa fa-trash-o" aria-hidden="true" title="删除角色" ' +
                        '           onclick="deleteOne(this)"></i></a>' +
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-pencil" aria-hidden="true" title="编辑角色" ' +
                        '           onclick="edit_or(this)"></i></a>' +
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-user-md" aria-hidden="true" title="分配权限"' +
                        '           onclick="edit_level_or(this)"></i></a>' +
                        '   </div>' +
                        '</tr>';
                }
                $("#result_or").html("");
                $('#result_or').append(trs);
            }
        }
    });
}

$(document).ready(function () {
    getAllRoles();
});
/*全选框 begin*/
var cks = document.getElementsByName("check");
//全选控制所有子框
function checkAll(ckAll) {
    for (var i = 0; i < cks.length; i++) {
        cks[i].checked = ckAll.checked;
    }
}
//通过子框控制全选
function checkOne() {
    //给每一个子框绑定一个点击事件，每次触发都判断是否全选
    for (var i = 0; i < cks.length; i++) {
        //循环每一个按钮的值，判断是否选中
        var flag = true;
        var ckArr = document.getElementsByName("check");
        for (var j = 0; j < ckArr.length; j++) {
            //有未选中即终止循环，修改标记
            if (!ckArr[j].checked) {
                flag = false;
                break;
            }
        }
        document.getElementById("check_th").checked = flag;//通过标记控制全选
    }
}
/*全选框 end*/

var search_rname = null, status = 2;
var data = null;

function getSearchVal(obj) {
    var val = obj.valueOf().value;
    var id = obj.id;
    if (id === "input_search") {
        search_rname = val;
    } else if (id === "input_select") {
        status = val;
    }
}

function dosearch() {
    if (search_rname !== "" && search_rname !== undefined && search_rname != null) {
        data = {search_rname: search_rname, status: status};
        $.ajax({
            type: "POST",
            url: "getOperationalRoleByName_rStatus",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: true,
            success: function (msg) {
                if (msg.code === "0") {
                    list = msg.list;
                    trs = "";
                    for (var i = 0; i < list.length; i++) {
                        trs += '<tr id="' + list[i].rid + '">' +
                            '   <td>' +
                            '       <input type="checkbox" id="check_th' + list[i].rid + '" name="check" onclick="checkOne()">' +
                            '       <label class="check_th" for="check_th' + list[i].rid + '"></label>' +
                            '   </td>' +
                            '   <td>' + list[i].rid + '</td>' +
                            '   <td>' + list[i].rname + '</td>' +
                            '   <td>' + list[i].rdescription + '</td>' +
                            '   <td>' + ((list[i].rstatus === 1) ? "启用" : "禁用") + '</td>' +
                            '   <td class="td-operation no-select">' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-trash-o" aria-hidden="true" title="删除角色" ' +
                            '           onclick="deleteOne(this)"></i></a>' +
                            '   </div>' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-pencil" aria-hidden="true" title="编辑角色"' +
                            '           onclick="edit_or(this)"></i></a>' +
                            '   </div>' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-user-md" aria-hidden="true" title="分配权限"' +
                            '           onclick="edit_level_or(this)"></i></a>' +
                            '   </div>' +
                            '</tr>';
                    }
                    $("#result_or").html("");
                    $('#result_or').append(trs);
                } else {
                    $("#result_or").html("");
                }
            }
        });
    } else {  //角色名称为空，按角色状态查询
        if (status === "2") {
            getAllRoles();
        } else {
            data = {status: status};
            $.ajax({
                type: "POST",
                url: "getOperationalRoleByrStatus",
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: JSON.stringify(data),
                async: true,
                success: function (msg) {
                    if (msg.code === "0") {
                        list = msg.list;
                        trs = "";
                        for (var i = 0; i < list.length; i++) {
                            trs += '<tr id="' + list[i].rid + '">' +
                                '   <td>' +
                                '       <input type="checkbox" id="check_th' + list[i].rid + '" name="check" onclick="checkOne()">' +
                                '       <label class="check_th" for="check_th' + list[i].rid + '"></label>' +
                                '   </td>' +
                                '   <td>' + list[i].rid + '</td>' +
                                '   <td>' + list[i].rname + '</td>' +
                                '   <td>' + list[i].rdescription + '</td>' +
                                '   <td>' + ((list[i].rstatus === 1) ? "启用" : "禁用") + '</td>' +
                                '   <td class="td-operation no-select">' +
                                '   <div>' +
                                '       <a href="#"><i class="fa fa-trash-o" aria-hidden="true" title="删除角色" ' +
                                '           onclick="deleteOne(this)"></i></a>' +
                                '   </div>' +
                                '   <div>' +
                                '       <a href="#"><i class="fa fa-pencil" aria-hidden="true" title="编辑角色" ' +
                                '           onclick="edit_or(this)"></i></a>' +
                                '   </div>' +
                                '   <div>' +
                                '       <a href="#"><i class="fa fa-user-md" aria-hidden="true" title="分配权限"' +
                                '           onclick="edit_level_or(this)"></i></a>' +
                                '   </div>' +
                                '</tr>';
                        }
                        $("#result_or").html("");
                        $('#result_or').append(trs);
                    } else {
                        $("#result_or").html("");
                    }
                }
            });
        }
    }
}

function deleteOne(obj) {
    var rid = $(obj).parents()[3].id;
    var rname = $(obj).parents()[3].children[2].valueOf().innerHTML;
    if (window.confirm('确定要删除"' + rname + '"吗？')) {
        //确定
        data = {rid: rid};
        $.ajax({
            type: "POST",
            url: "deleteOperationalRoleByrID",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: true,
            success: function (msg) {
                if (msg > 0) {   //移除元素
                    $("#" + rid).remove();
                } else {
                    alert("删除失败");
                }
            }
        });
    } else {
        //取消
    }
}

function deleteMultiple() {
    if (window.confirm('确定要批量删除吗？')) {
        //确定
        var checks = $("[id^='check_thOR']");    //选择id以“check_thOR”开头的元素
        var to_delete_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_delete_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {rid: to_delete_rid};
                $.ajax({
                    type: "POST",
                    url: "deleteOperationalRoleByrID",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg > 0) {   //移除元素
                            $("#" + to_delete_rid).remove();
                        } else {
                            alert("删除失败");
                        }
                    }
                });
            }
        }
    } else {
        //取消
    }
}

function enableMultiple() {
    if (window.confirm('确定要批量启用吗？')) {
        //确定
        var checks = $("[id^='check_thOR']");    //选择id以“check_thOR”开头的元素
        var to_enable_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_enable_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {rid: to_enable_rid, rstatus: 1};
                $.ajax({
                    type: "POST",
                    url: "updateOperationalRolerStatusByrID",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg > 0) {   //修改元素
                            $(checks[i]).parents()[1].children[4].valueOf().innerText = "启用";
                        } else {
                            alert("启用失败");
                        }
                    }
                });
            }
        }
    } else {
        //取消
    }
}

function disableMultiple() {
    if (window.confirm('确定要批量禁用吗？')) {
        //确定
        var checks = $("[id^='check_thOR']");    //选择id以“check_thOR”开头的元素
        var to_disable_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_disable_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {rid: to_disable_rid, rstatus: 0};
                $.ajax({
                    type: "POST",
                    url: "updateOperationalRolerStatusByrID",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg > 0) {   //修改元素
                            $(checks[i]).parents()[1].children[4].valueOf().innerText = "禁用";
                        } else {
                            alert("启用失败");
                        }
                    }
                });
            }
        }
    } else {
        //取消
    }
}

function light_down() {
    document.getElementById("light-down").style.opacity = "1";
    document.getElementById("light-down").style.zIndex = "100";
}

function light_up() {
    document.getElementById("light-down").style.opacity = "0";
    document.getElementById("light-down").style.zIndex = "0";
}

function light_down_edit() {
    light_down();
    document.getElementById("div-edit").style.opacity = "1";
    document.getElementById("div-edit").style.zIndex = "100";
}

function light_up_edit() {
    document.getElementById("div-edit").style.opacity = "0";
    document.getElementById("div-edit").style.zIndex = "0";
    light_up();
}

function light_down_level() {
    light_down();
    document.getElementById("div-edit-level").style.opacity = "1";
    document.getElementById("div-edit-level").style.zIndex = "100";
}

function light_up_level() {
    document.getElementById("div-edit-level").style.opacity = "0";
    document.getElementById("div-edit-level").style.zIndex = "0";
    light_up();
}

function light_down_add() {
    light_down();
    document.getElementById("div-add").style.opacity = "1";
    document.getElementById("div-add").style.zIndex = "100";
}

function light_up_add() {
    document.getElementById("div-add").style.opacity = "0";
    document.getElementById("div-add").style.zIndex = "0";
    light_up();
}

function edit_or(obj) {
    var rid = $(obj).parents()[3].id;
    data = {rid: rid};
    $.ajax({
        type: "POST",
        url: "getOperationalRoleByrID",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                var radio = document.getElementsByName("Status");
                if (list[0].rstatus === 1) {
                    radio[0].checked = true;
                } else if (list[0].rstatus === 0) {
                    radio[1].checked = true;
                }
                $("#Name")[0].valueOf().value = list[0].rname;
                $("#ID")[0].valueOf().value = list[0].rid;
                $("#Description")[0].valueOf().value = list[0].rdescription;
                $("#PermissionLevel")[0].valueOf().value = list[0].rpermissionLevel;
                light_down_edit();
            }
        }
    });
}

var rname, rid, rdescription, rpermissionLevel, rstatus;

function saveedit_or() {
    var radio = document.getElementsByName("Status");

    if (radio[0].checked) {
        rstatus = 1;
    } else if (radio[1].checked) {
        rstatus = 0;
    }
    rname = $("#Name")[0].valueOf().value;
    rid = $("#ID")[0].valueOf().value;
    rdescription = $("#Description")[0].valueOf().value;
    rpermissionLevel = $("#PermissionLevel")[0].valueOf().value;
    data = {
        rname: rname,
        rid: rid,
        rdescription: rdescription,
        rpermissionLevel: rpermissionLevel,
        rstatus: rstatus
    };
    $.ajax({
        type: "POST",
        url: "updateOperationalRole",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg > 0) {   //修改页内元素
                var tr = $("#" + rid)[0];
                tr.children[3].innerHTML = rdescription;                    //角色描述
                tr.children[4].innerHTML = rstatus === 1 ? "启用" : "禁用"; //角色状态
                light_up_edit();
            } else {
                alert("保存失败");
            }
        }
    });
}

function edit_level_or(obj) {
    var rid = $(obj).parents()[3].id;
    data = {rid: rid};
    $.ajax({
        type: "POST",
        url: "getOperationalRoleByrID",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                $("#Name-level")[0].valueOf().value = list[0].rname;
                $("#ID-level")[0].valueOf().value = list[0].rid;
                $("#PermissionLevel-level")[0].valueOf().value = list[0].rpermissionLevel;
                light_down_level();
            }
        }
    });
}

function saveedit_level_or() {
    rid = $("#ID-level")[0].valueOf().value;
    rpermissionLevel = $("#PermissionLevel-level")[0].valueOf().value;
    data = {rid: rid, rpermissionLevel: rpermissionLevel};
    $.ajax({
        type: "POST",
        url: "updateOperationalRoleLevelByrID",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg > 0) {   //修改页内元素（页面内没显示权限等级，所以无需修改）
                light_up_level();
            } else {
                alert("保存失败");
            }
        }
    });
}

function clean_addinfo() {
    $("#StatusOn-add")[0].checked = false;
    $("#StatusOff-add")[0].checked = false;
    $("#Name-add")[0].valueOf().value = "";
    $("#Description-add")[0].valueOf().value = "";
    $("#PermissionLevel-add")[0].valueOf().value = "";
}

function saveadd_or() {
    var ok;
    rname = $("#Name-add")[0].valueOf().value;
    rdescription = $("#Description-add")[0].valueOf().value;
    var radio = document.getElementsByName("Status-add");
    if (radio[0].checked) {
        rstatus = 1;
    } else if (radio[1].checked) {
        rstatus = 0;
    } else {
        rstatus = null;
    }
    rpermissionLevel = $("#PermissionLevel-add")[0].valueOf().value;
    if (rname == null || rname === "" || rstatus == null || rpermissionLevel == null || rpermissionLevel === "") {
        ok = false;
    } else {
        ok = true;
    }
    if (ok) {
        data = {rname: rname, rdescription: rdescription, rpermissionLevel: rpermissionLevel, rstatus: rstatus};
        $.ajax({
            type: "POST",
            url: "addOperationalRole",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {
                if (msg.code === "0") { //修改元素
                    list = msg.list;
                    for (var i = 0; i < list.length; i++) {
                        var tr = '<tr id="' + list[i].rid + '">' +
                            '   <td>' +
                            '       <input type="checkbox" id="check_th' + list[i].rid + '" name="check" onclick="checkOne()">' +
                            '       <label class="check_th" for="check_th' + list[i].rid + '"></label>' +
                            '   </td>' +
                            '   <td>' + list[i].rid + '</td>' +
                            '   <td>' + list[i].rname + '</td>' +
                            '   <td>' + list[i].rdescription + '</td>' +
                            '   <td>' + ((list[i].rstatus === 1) ? "启用" : "禁用") + '</td>' +
                            '   <td class="td-operation no-select">' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-trash-o" aria-hidden="true" title="删除角色" ' +
                            '           onclick="deleteOne(this)"></i></a>' +
                            '   </div>' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-pencil" aria-hidden="true" title="编辑角色" ' +
                            '           onclick="edit_or(this)"></i></a>' +
                            '   </div>' +
                            '   <div>' +
                            '       <a href="#"><i class="fa fa-user-md" aria-hidden="true" title="分配权限"' +
                            '           onclick="edit_level_or(this)"></i></a>' +
                            '   </div>' +
                            '</tr>';
                    }
                    light_up_add();
                    clean_addinfo();
                    $('#result_or').append(tr);
                } else {
                    alert("添加失败");
                }
            }
        });
    } else {
        alert("带星号字段为必填项");
    }
}
