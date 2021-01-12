let ts_ID = null;               //<!--01_调拨单号_主键-->
let ts_Type = null;             //<!--调拨类型-->
let wname_Out = null;           //<!-- 03_仓库名称_唯一-->
let wname_In = null;            //<!-- 03_仓库名称_唯一-->
let product_ID = null;          //<!-- 12_产品编码_外键-->
let product_Name = null;        //<!-- 03_产品名称-->
let _type = null;               //<!-- 04_品类-->
let true_Name = null;           //<!--创建人姓名-->
//根据时间段查询
let start_Date = null;          //<!--开始时间-->
let end_Date = null;            //<!--结束时间-->
let productList = null;         //<!--所有的产品信息-->
let url = "";                   //作拼接用的url

// 详情信息点击显示/消失div
function display() {
    let obj = document.getElementById('transfer_details');
    let details = document.getElementById('transferlist_page_details');
    let product = document.getElementById('transferlist_page_product');
    if (obj.style.display === "none") {
        obj.style.display = "block";
        if (details.style.display === "none") {
            display_Show_details();
        }
    }
    else if (obj.style.display === "block") {
        if (details.style.display === "block") {
            details.style.display = "none";
        }
        if (product.style.display === "block") {
            product.style.display = "none";
        }
        obj.style.display = "none";
    }
}

// 显示隐藏的调拨单详细信息
function display_Show_details() {
    let details = document.getElementById('transferlist_page_details');
    let product = document.getElementById('transferlist_page_product');
    if (product.style.display === "block") {
        product.style.display = "none";
    }
    if (details.style.display === "none") {
        details.style.display = "block";
    }

    getAllOrderInformationByTsID();
}

// 显示隐藏的产品信息div
function display_Show_Product() {
    let details = document.getElementById('transferlist_page_details');
    let product = document.getElementById('transferlist_page_product');
    if (details.style.display === "block") {
        details.style.display = "none";
    }
    if (product.style.display === "none") {
        product.style.display = "block";
    }

    getAllOrderProductListByProductID();
}

// 拼接url
function select_Dynamic() {
    url = "";
    ts_ID = $('#ts_ID').val();
    ts_Type = $('#ts_Type').val();
    wname_Out = $('#wName_out').val();
    wname_In = $('#wName_in').val();
    product_ID = $('#product_ID').val();
    product_Name = $('#product_Name').val();
    _type = $('#_type').val();
    true_Name = $('#true_Name').val();
    start_Date = $('#start_Date').val();
    end_Date = $('#end_Date').val();

    if (ts_ID !== null && ts_ID !== '') {
        url += "tsID=" + ts_ID;
    }
    if (ts_Type !== '0') {
        url += "&tsType=" + ts_Type;
    }
    if (wname_Out !== '0') {
        url += "&wnameOut=" + wname_Out;
    }
    if (wname_In !== '0') {
        url += "&wnameIn=" + wname_In;
    }
    if (product_ID !== null && product_ID !== '') {
        url += "&productID=" + product_ID;
    }
    if (product_Name !== null && product_Name !== '') {
        url += "&productName=" + product_Name;
    }
    if (_type !== null && _type !== '') {
        url += "&type=" + _type;
    }
    if (true_Name !== '0') {
        url += "&trueName=" + true_Name;
    }
    if (start_Date !== null && start_Date !== '') {
        let temp = translateTime(start_Date);
        // let time = [
        //     temp.getFullYear() +
        //     '-' + (temp.getMonth() + 1) +
        //     '-' + temp.getDate()
        // ];
        url += "&startDate=" + temp;
    }
    if (end_Date !== null && end_Date !== '') {
        let temp = translateTime(end_Date);
        // let time = [
        //     temp.getFullYear() +
        //     '-' + (temp.getMonth() + 1) +
        //     '-' + temp.getDate()
        // ];
        url += "&endDate=" + temp;
    }
    $('#transferlistshow').find('tr').remove();
    selectTransferOrderList();
}

function translateTime(obj) {
    let date = new Date(obj);
    let year = date.getFullYear();     // 年
    let month = date.getMonth() + 1;   // 月
    if (month.toString().length === 1) {
        month = '0' + month.toString();
    }
    let day = date.getDate();          // 日
    if (day.toString().length === 1) {
        day = '0' + day.toString();
    }
    let hour = date.getHours();        // 时
    if (hour.toString().length === 1) {
        hour = '0' + hour.toString();
    }
    let min = date.getMinutes();       // 分
    if (min.toString().length === 1) {
        min = '0' + min.toString();
    }
    let sec = date.getSeconds();       // 秒
    if (sec.toString().length === 1) {
        sec = '0' + sec.toString();
    }
    time = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    return time;
}

// 获取调拨单列表
function selectTransferOrderList() {

    $.ajax({
        type: "post",
        url: "getalltransferorderlist?" + url,
        data: 'json',
        success: function (res) {
            data = res.list;
            productList = res.list;

            let html = '';
            // 总数量
            let Q = 0;
            // 总体积;
            let V = 0;
            // 总价格
            let TotalPrice = 0;
            for (let i = 0; i < data.length; i++) {

                Q += data[i].tsVolumeDose;
                V += data[i].tsTotalVolume;
                let date = new Date(data[i].createDate);
                TotalPrice += data[i].price * data[i].tsVolumeDose;
                html += "<tr id='" + i + "' class='gradeA odd'>" +
                    "<td>" + parseInt(i + 1) + "</td>" +
                    "<td>" + data[i].tsID + "</td>" +
                    "<td>" + date.toLocaleDateString() + "</td>" +
                    "<td>" + data[i].tsType + "</td>" +
                    "<td>" + data[i].calloutwID + "</td>" +
                    "<td>" + data[i].wnameOut + "</td>" +
                    "<td>" + data[i].callinwID + "</td>" +
                    "<td>" + data[i].wnameIn + "</td>" +
                    "<td>" + data[i].productID + "</td>" +
                    "<td>" + data[i].productName + "</td>" +
                    "<td>" + data[i].type + "</td>" +
                    "<td>" + data[i].tsVolumeDose + "</td>" +
                    "<td>" + data[i].unit + "</td>" +
                    "<td>" + data[i].tsTotalVolume + "</td>" +
                    "<td style='color: black;' onclick='hasChecked(" + i + ")'><i class='fa  fa-info-circle fa-2x'></i></td></tr>";
            }
            html += "<td colspan='15' style='text-align: right'><label >小计&nbsp;&nbsp;&nbsp;&nbsp;数量：" + Q + " &nbsp;&nbsp;体积：" + V + "m³ &nbsp;&nbsp;订单金额：" + TotalPrice + "元</label></td>";
            transferlistshow.innerHTML = html;
        },
        error: function (arg1) {
            alert("加载数据失败");
            console.log(arg1);
        }
    })
}

let p_ID = null;
let t_ID = null;

function hasChecked(obj) {
    p_ID = productList[obj].productID;
    t_ID = productList[obj].tsID;
    display();
}

// 获取产品信息
function getAllOrderProductListByProductID() {

    $.ajax({
        type: "post",
        url: "getallorderproductlist?productID=" + p_ID,
        async: true,
        data: 'json',
        success: function (res) {
            data = res.list;
            let html = '';
            let Q = 0;
            let V = 0;
            for (let i = 0; i < data.length; i++) {
                Q += data[i].tsQuantity;
                V += data[i].unitVolume * data[i].tsQuantity;

                html += "<tr>" +
                    "<td style='background: white'>" + parseInt(i + 1) + "</td>" +
                    "<td style='background: white'>" + data[i].stockOrderID + "</td>" +
                    "<td style='background: white'>" + data[i].calloutwID + "</td>" +
                    "<td style='background: white'>" + data[i].wnameOut + "</td>" +
                    "<td style='background: white'>" + data[i].callinwID + "</td>" +
                    "<td style='background: white'>" + data[i].wnameIn + "</td>" +
                    "<td style='background: white'>" + data[i].productID + "</td>" +
                    "<td style='background: white'>" + data[i].productName + "</td>" +
                    "<td style='background: white'>" + data[i].repertory + "</td>" +
                    "<td style='background: white'>" + data[i].callinWInventory + "</td>" +
                    "<td style='background: white'>" + data[i].tsQuantity + "</td>" +
                    "<td style='background: white'>" + data[i].checkQuantity + "</td>" +
                    "<td style='background: white'>" + data[i].actualShipments + "</td>" +
                    "<td style='background: white'>" + data[i].signedQuantity + "</td>" +
                    "<td style='background: white'>" + data[i].unit + "</td>" +
                    "<td style='background: white'>" + data[i].unitVolume + "</td></tr>";
            }
            html += "<td colspan='16' style='text-align: right; background: #f9f9f9;'><label >小计&nbsp;&nbsp;&nbsp;&nbsp;数量：" + Q + "件 &nbsp;&nbsp;体积：" + V + "m³</label></td>";
            transferlist_product.innerHTML = html;
        },
        error: function (arg1) {
            alert("加载数据失败");
            console.log(arg1);
        }
    })
}

// 展示调拨单详细信息
function getAllOrderInformationByTsID() {

    $.ajax({
        type: "post",
        url: "getallorderinformation?tsID=" + t_ID,
        async: true,
        data: 'json',
        success: function (res) {
            data = res.list;
            let i = 0;
            $('#td_01').html(data[i].createDate);
            $('#td_11').html(data[i].worganizationOut);
            $('#td_21').html(data[i].calloutwID);
            $('#td_31').html(data[i].wnameOut);
            $('#td_41').html(data[i].trueName);
            $('#td_51').html(data[i].agencyID);
            $('#td_61').html(data[i].consignee);
            $('#td_71').html(data[i].deliverAd);
            $('#td_81').html(data[i].tsVolumeDose);
            $('#td_91').html(data[i].remark);
            $('#td_02').html(data[i].tsID);
            $('#td_12').html(data[i].worganizationIn);
            $('#td_22').html(data[i].callinwID);
            $('#td_32').html(data[i].wnameIn);
            $('#td_42').html(data[i].transMode);
            $('#td_52').html(data[i].agencyName);
            $('#td_62').html(data[i].consigneeTel);
            $('#td_72').html(data[i].consigneeID);
            $('#td_82').html(data[i].tsTotalVolume);
            $('#td_92').html(data[i].organization);
        },
        error: function (arg1) {
            alert("加载数据失败");
            console.log(arg1);
        }
    })
}

// 调用插件导出excel
function exportExcel() {

    // 获取时间
    let dateTime = Gettime();

    $("#dataTables-example").table2excel({
        exclude: ".excludeThisClass",
        name: "Worksheet Name",
        // 名字
        filename: "调拨单列表" + dateTime, // do include extension
        preserveColors: false // set to true if you want background colors and font colors preserved
    });

}

// 获取当前日期/时间
function Gettime() {

    let now = new Date();
    let time_arr = [
        ' ' + now.getFullYear() +
        '-' + (now.getMonth() + 1) +
        '-' + now.getDate() +
        ' ' + now.getHours() +
        ':' + now.getMinutes() +
        ':' + now.getSeconds()
    ];
    return time_arr;
}

//退出
function logout() {
    $.ajax({
        type: "POST",
        url: "logout",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            if (msg === 0) {
                window.location.href = "login.html";
            } else {
                alert("退出时遇到问题，请查看后台");
            }
        }
    });
}

// 拿到登录用户名
let map = null;
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "getloginuser",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            map = msg;
            $('#login-truename')[0].innerHTML = map["login_truename"];
            $('#login_truename')[0].innerHTML = map["login_truename"];
            $('#login_rName')[0].innerHTML = map["login_rname"];
        }
    });
});