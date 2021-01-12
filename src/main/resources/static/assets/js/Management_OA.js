var list, trs = "";
function getAllAccounts() {
    $.ajax({
        type: "GET",
        url: "getAllAccounts",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                trs = "";
                for (var i = 0; i < list.length; i++) {
                    trs += '<tr id="' + list[i].userID + '">' +
                        '   <td>' +
                        '       <input type="checkbox" id="check_th' + list[i].userID + '" name="check" onclick="checkOne()">' +
                        '       <label class="check_th" for="check_th' + list[i].userID + '"></label>' +
                        '   </td>' +
                        '   <td>' + list[i].userID + '</td>' +
                        '   <td>' + list[i].userName + '</td>' +
                        '   <td>' + list[i].trueName + '</td>' +
                        '   <td>' + list[i].organization + '</td>' +
                        '   <td>' + list[i].rname + '</td>' +
                        '   <td>' + ((list[i].sex === "M") ? "男" : "女") + '</td>' +
                        '   <td>' + list[i].email + '</td>' +
                        '   <td>' + list[i].phone + '</td>' +
                        '   <td>' + ((list[i].astatus === 1) ? "启用" : "禁用") + '</td>' +
                        '   <td class="td-operation no-select">' +
                        '   <div>' +
                        '       <a><i class="fa fa-trash-o" aria-hidden="true" title="删除账号" ' +
                        '             onclick="deleteOne(this)"></i></a>'+
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-pencil" aria-hidden="true" title="编辑账号" ' +
                        '           onclick="edit_oa(this)"></i></a>' +
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-key" aria-hidden="true" title="重置密码" ' +
                        '           onclick="resetPassword(this)"></i></a>'+
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-user-circle-o" aria-hidden="true" title="分配角色"' +
                        '           onclick="assignRoles(this)"></i></a>' +
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
    getAllAccounts();
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
        //通过标记控制全选
        document.getElementById("check_th").checked = flag;
    }
}

/*全选框 end*/

var search_username = null, search_truername = null, search_phone = null, search_sex = 2, search_astatus = 2;
var data = null;

function getSearchVal(obj) {
    var val = obj.valueOf().value;
    var id = obj.id;
    if (id === "input_search1") {
        search_username = val;
    } else if (id === "input_search2") {
        search_truername = val;
    } else if (id === "input_search3") {
        search_truername = val;
    }else if (id === "input_select_sex") {
        search_sex = val;
    }else if (id === "input_select_status") {
        search_astatus = val;
    }
}

function dosearch() {
    var url = null;
    if (search_username !== "" && search_username !== undefined && search_username != null) {
        data = {search_username: search_username};
        url = "getAccountByuserName";
    } else if (search_truername !== "" && search_truername !== undefined && search_truername != null) {
        data = {search_truername: search_truername};
        url = "getAccountBytruerName";
    }else if (search_phone !== "" && search_phone !== undefined && search_phone != null) {
        data = {search_phone: search_phone};
        url = "getAccountByphone";
    }else if (search_sex === 2 && search_astatus === 2) {
        data = null;
        url = "getAllAccounts";
    }else if (search_sex !== 2 && search_astatus === 2) {
        data = {search_sex: search_sex};
        url = "getAccountBysex";
    }else if (search_sex === 2 && search_astatus !== 2) {
        data = {search_astatus: search_astatus};
        url = "getAccountByaStatus";
    } else if (search_sex !== 2 && search_astatus !== 2) {
        data = {search_sex: search_sex, search_astatus: search_astatus};
        url = null;
    }
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: true,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                trs = "";
                for (var i = 0; i < list.length; i++) {
                    trs += '<tr id="' + list[i].userID + '">' +
                        '   <td>' +
                        '       <input type="checkbox" id="check_th' + list[i].userID + '" name="check" onclick="checkOne()">' +
                        '       <label class="check_th" for="check_th' + list[i].userID + '"></label>' +
                        '   </td>' +
                        '   <td>' + list[i].userID + '</td>' +
                        '   <td>' + list[i].userName + '</td>' +
                        '   <td>' + list[i].trueName + '</td>' +
                        '   <td>' + list[i].organization + '</td>' +
                        '   <td>' + list[i].rname + '</td>' +
                        '   <td>' + ((list[i].sex === "M") ? "男" : "女") + '</td>' +
                        '   <td>' + list[i].email + '</td>' +
                        '   <td>' + list[i].phone + '</td>' +
                        '   <td>' + ((list[i].astatus === 1) ? "启用" : "禁用") + '</td>' +
                        '   <td class="td-operation no-select">' +
                        '   <div>' +
                        '       <a><i class="fa fa-trash-o" aria-hidden="true" title="删除账号" ' +
                        '             onclick="deleteOne(this)"></i></a>'+
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-pencil" aria-hidden="true" title="编辑账号" ' +
                        '           onclick="edit_oa(this)"></i></a>' +
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-key" aria-hidden="true" title="重置密码" ' +
                        '           onclick="resetPassword(this)"></i></a>'+
                        '   </div>' +
                        '   <div>' +
                        '       <a><i class="fa fa-user-circle-o" aria-hidden="true" title="分配角色"' +
                        '           onclick="assignRoles(this)"></i></a>' +
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
function deleteOne(obj) {
    var userid = $(obj).parents()[3].id;
    var username = $(obj).parents()[3].children[2].valueOf().innerHTML;
    if (window.confirm('确定要删除"' + username + '"吗？')) {
        //确定
        data = {userid: userid};
        $.ajax({
            type: "POST",
            url: "deleteOperationalAccountByuserID",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: true,
            success: function (msg) {
                if (msg > 0) {   //移除元素
                    $("#" + userid).remove();
                } else {
                    alert("删除失败");
                }
            },error:function (msg) {
                alert("存在外键依赖，删除失败，");
                //console.log(msg);
            }
        });
    } else {
        //取消
    }
}

function deleteMultiple() {
    if (window.confirm('确定要批量删除吗？')) {
        //确定
        var checks = $("[id^='check_thOA']");    //选择id以“check_thOR”开头的元素
        var to_delete_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_delete_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {userid: to_delete_rid};
                $.ajax({
                    type: "POST",
                    url: "deleteOperationalAccountByuserID",
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
        var checks = $("[id^='check_thOA']");    //选择id以“check_thOR”开头的元素
        var to_enable_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_enable_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {userid: to_enable_rid, astatus: 1};
                $.ajax({
                    type: "POST",
                    url: "updateOperationalAccountStatusByruserID",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg > 0) {   //修改元素
                            $(checks[i]).parents()[1].children[9].valueOf().innerText = "启用";
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
        var checks = $("[id^='check_thOA']");    //选择id以“check_thOR”开头的元素
        var to_disable_rid;
        for (var i = 0; i < checks.length; i++) {
            if (checks[i].checked) {
                to_disable_rid = checks[i].id.slice(8);  //选取从8位开始之后的字符
                data = {userid: to_disable_rid, astatus: 0};
                $.ajax({
                    type: "POST",
                    url: "updateOperationalAccountStatusByruserID",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg > 0) {   //修改元素
                            $(checks[i]).parents()[1].children[9].valueOf().innerText = "禁用";
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
    document.getElementById("div-edit2").style.opacity = "1";
    document.getElementById("div-edit2").style.zIndex = "100";
}

function light_up_edit() {
    document.getElementById("div-edit2").style.opacity = "0";
    document.getElementById("div-edit2").style.zIndex = "0";
    light_up();
}

function light_down_edit_role() {
    light_down();
    document.getElementById("div-edit-role").style.opacity = "1";
    document.getElementById("div-edit-role").style.zIndex = "100";
}

function light_up_edit_role() {
    document.getElementById("div-edit-role").style.opacity = "0";
    document.getElementById("div-edit-role").style.zIndex = "0";
    light_up();
}

function light_down_add() {
    light_down();
    document.getElementById("div-add2").style.opacity = "1";
    document.getElementById("div-add2").style.zIndex = "100";
}

function light_up_add() {
    document.getElementById("div-add2").style.opacity = "0";
    document.getElementById("div-add2").style.zIndex = "0";
    light_up();
}

function edit_oa(obj) {
    var userid = $(obj).parents()[3].id;
    data = {userid: userid};
    $.ajax({
        type: "POST",
        url: "getOperatingAccountByID",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                var radio = document.getElementsByName("sex-edit");
                if (list[0].sex === "M") {
                    radio[0].checked = true;
                } else if (list[0].sex === "F") {
                    radio[1].checked = true;
                }
                $("#username-edit")[0].valueOf().value = list[0].userName;
                $("#userid-edit")[0].valueOf().value = list[0].userID;
                $("#organization-edit")[0].valueOf().value = list[0].organization;
                $("#productLines-edit")[0].valueOf().value = list[0].productLines;
                $("#rname-edit")[0].valueOf().value = list[0].rname;
                $("#phone-edit")[0].valueOf().value = list[0].phone;
                $("#email-edit")[0].valueOf().value = list[0].email;
                light_down_edit();
            }
        }
    });
}

var username, userid, organization, productLines, rname, phone, email, sex;
function saveedit_oa() {
    var radio = document.getElementsByName("sex-edit");

    if (radio[0].checked) {
        sex = "M";
    } else if (radio[1].checked) {
        sex = "F";
    }
    username = $("#username-edit")[0].valueOf().value;
    userid = $("#userid-edit")[0].valueOf().value;
    organization = $("#organization-edit")[0].valueOf().value;
    productLines = $("#productLines-edit")[0].valueOf().value;
    rname = $("#rname-edit")[0].valueOf().value;
    phone = $("#phone-edit")[0].valueOf().value;
    email = $("#email-edit")[0].valueOf().value;
    data = {
        username: username,
        organization: organization,
        productLines: productLines,
        rname: rname,
        phone: phone,
        email: email,
        sex: sex
    };
    $.ajax({
        type: "POST",
        url: "updateOperationalAccountByusername",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg > 0) {   //修改页内元素
                var tr = $("#" + userid)[0];
                test = tr;
                tr.children[4].innerHTML = organization;
                tr.children[5].innerHTML = rname;
                tr.children[6].innerHTML = (sex === "M" ? "男" : "女");
                tr.children[7].innerHTML = email;
                tr.children[8].innerHTML = phone;
                light_up_edit()
            } else {
                alert("保存失败");
            }
        }
    });
}

function clean_addinfo() {
    $("#sex-add-m")[0].checked = false;
    $("#sex-add-f")[0].checked = false;
    $("#username-add")[0].valueOf().value = "";
    $("#truename-add")[0].valueOf().value = "";
    $("#organization-add")[0].valueOf().value = "";
    $("#productLines-add")[0].valueOf().value = "";
    $("#rname-add")[0].valueOf().value = "";
    $("#phone-add")[0].valueOf().value = "";
    $("#email-add")[0].valueOf().value = "";
}

function saveadd_oa() {
    var ok;
    var username, truename, organization, productLines, rname, phone, email, sex;
    username = $("#username-add")[0].valueOf().value;
    truename = $("#truename-add")[0].valueOf().value;
    organization = $("#organization-add")[0].valueOf().value;
    productLines = $("#productLines-add")[0].valueOf().value;
    rname = $("#rname-add")[0].valueOf().value;
    phone = $("#phone-add")[0].valueOf().value;
    email = $("#email-add")[0].valueOf().value;
    var radio = document.getElementsByName("sex-add");
    if (radio[0].checked) {
        sex = "M";
    } else if (radio[1].checked) {
        sex = "F";
    } else {
        sex = null;
    }
    if (username == null || username === "" ||  truename == null || truename === "" ||
        organization == null || organization === "" ||  productLines == null || productLines === "" ||
        rname == null || rname === "" ||  phone == null || phone === "") {
        ok = false;
    } else {
        ok = true;
    }
    if (ok) {
        data = {
            username: username,
            truename: truename,
            organization: organization,
            productLines: productLines,
            rname: rname,
            phone: phone,
            email: phone,
            sex: sex,
        };
        $.ajax({
            type: "POST",
            url: "addOperationalAccount",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {
                if (msg.code === "0") { //修改元素
                    list = msg.list;
                    for (var i = 0; i < list.length; i++) {
                        var tr = '<tr id="' + list[i].userID + '">' +
                            '   <td>' +
                            '       <input type="checkbox" id="check_th' + list[i].userID + '" name="check" onclick="checkOne()">' +
                            '       <label class="check_th" for="check_th' + list[i].userID + '"></label>' +
                            '   </td>' +
                            '   <td>' + list[i].userID + '</td>' +
                            '   <td>' + list[i].userName + '</td>' +
                            '   <td>' + list[i].trueName + '</td>' +
                            '   <td>' + list[i].organization + '</td>' +
                            '   <td>' + list[i].rname + '</td>' +
                            '   <td>' + ((list[i].sex === "M") ? "男" : "女") + '</td>' +
                            '   <td>' + list[i].email + '</td>' +
                            '   <td>' + list[i].phone + '</td>' +
                            '   <td>' + ((list[i].astatus === 1) ? "启用" : "禁用") + '</td>' +
                            '   <td class="td-operation no-select">' +
                            '   <div>' +
                            '       <a><i class="fa fa-trash-o" aria-hidden="true" title="删除账号" ' +
                            '             onclick="deleteOne(this)"></i></a>'+
                            '   </div>' +
                            '   <div>' +
                            '       <a><i class="fa fa-pencil" aria-hidden="true" title="编辑账号" ' +
                            '           onclick="edit_oa(this)"></i></a>' +
                            '   </div>' +
                            '   <div>' +
                            '       <a><i class="fa fa-key" aria-hidden="true" title="重置密码" ' +
                            '           onclick="resetPassword(this)"></i></a>'+
                            '   </div>' +
                            '   <div>' +
                            '       <a><i class="fa fa-user-circle-o" aria-hidden="true" title="分配角色"' +
                            '           onclick="assignRoles(this)"></i></a>' +
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
function searchReset() {
    $("#input_search1")[0].valueOf().value = "";
    $("#input_search2")[0].valueOf().value = "";
    $("#input_search3")[0].valueOf().value = "";
    $("#input_select_sex")[0].value = "2";
    $("#input_select_status")[0].value = "2";
}
function resetPassword(obj) {
    var username = $(obj).parents()[3].children[2].valueOf().innerHTML;
    if (window.confirm('确定要重置"'+username+'"的密码吗？')) {
        //确定
        var userid = $(obj).parents()[3].id;
        data = {userid: userid};
        $.ajax({
            type: "POST",
            url: "resetOperatingAccountPasswordByID",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {
                if (msg > 0) {
                    alert("重置成功")
                } else {
                    alert("重置失败");
                }
            }
        });
    } else {
        //取消
    }

}
function assignRoles(obj) {
    var userid = $(obj).parents()[3].id;
    data = {userid: userid};
    $.ajax({
        type: "POST",
        url: "getOperatingAccountByID",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: function (msg) {
            if (msg.code === "0") {
                list = msg.list;
                $("#username-edit-role")[0].valueOf().value = list[0].userName;
                $("#truename-edit-role")[0].valueOf().value = list[0].trueName;
                light_down_edit_role();
            }
        }
    });
}
function assignRolesMultiple(obj) {
    $("#username-edit-role")[0].valueOf().value = "";
    $("#truename-edit-role")[0].valueOf().value = "";
    light_down_edit_role();
}
