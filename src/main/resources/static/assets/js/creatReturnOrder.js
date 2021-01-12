function show() {
    var show = $(".dialog").css("display");
    $(".dialog").css("display", show == "none" ? "block" : "none");
}

function close() {
    var show = $(".dialog").css("display");
    $(".dialog").css("display", show == "none" ? "block" : "none");
}

function show2() {
    var show = $(".dialog_2").css("display");
    $(".dialog_2").css("display", show == "none" ? "block" : "none");
}

function close2() {
    var show = $(".dialog_2").css("display");
    $(".dialog_2").css("display", show == "none" ? "block" : "none");
}

var getAllFlag = false;
var orderList;
var orderList_2;

function getAllSaleOrderByAgencyName() {

    if (getAllFlag === true) {
        return;
    }
    getAllFlag = true;
    var agencyName = "郎特姆公司";

    $.ajax({
        type: "get",
        url: "showAllOrders?agencyName=" + agencyName,
        data: null,
        success: function (map) {
            orderList = map.orderPojoList;
            var k = 0;
            for (var i = 0; i < map.orderPojoList.length; i++) {
                //alert(orderList[i].orderNumber);
                var orderNumber = orderList[i].orderNumber;
                var sourceNumber = orderList[i].sourceNumber;
                var buyerNickname = orderList[i].buyerNickname;
                //alert(orderNumber + "  " + sourceNumber + "  " + buyerNickname);
                //商品列表
                var orderItems = orderList[i].orderItems;
                for (var j = 0; j < orderItems.length; j++) {
                    k += 1;
                    var productNumber = orderItems[j].productNumber;
                    var productName = orderItems[j].productName;
                    var purchaseQuantity = orderItems[j].purchaseQuantity;
                    var productPrice = orderItems[j].productPrice;

                    //alert(productName + "  " + productNumber + "  " + purchaseQuantity + "  " + productPrice);
                    //拼接表格
                    var sb = '<tr>';
                    sb += '                        <td>';
                    sb += '                            <input type="checkbox" name="isSelected" value="' + k + '">';
                    sb += '                        </td>';
                    sb += '                        <td>' + orderNumber + '</td>';
                    sb += '                        <td>' + sourceNumber + '</td>';
                    sb += '                        <td>' + buyerNickname + '</td>';
                    sb += '                        <td>' + productNumber + '</td>';
                    sb += '                        <td>' + productName + '</td>';
                    sb += '                        <td>' + purchaseQuantity + '</td>';
                    sb += '                        <td>' + productPrice + '</td>';
                    sb += '                        <td style="color: red;" onclick="deleteProduct()"><a onclick="getSaleOrderInfo(this);show2()"' + ' value="' + i + '">查看详情</a></td>';
                    sb += '                    </tr>';
                    $("#dialog-on-html").append(sb);
                }
            }
        }
    });
}

function getSaleOrderInfo(obj) {
    //获取订单在订单列表中的下标
    var serial = $(obj).attr("value");
    // alert(serial);
    // alert(orderList[serial].orderNumber);
    //订单信息
    var orderNumber = orderList[serial].orderNumber;
    var orderStatus = orderList[serial].orderStatus;
    var buyerNickname = orderList[serial].buyerNickname;
    var deliveringWarehouse = orderList[serial].deliveringWarehouse;
    var expressNumber = orderList[serial].expressNumber;
    var sourceNumber = orderList[serial].sourceNumber;
    var agencyName = orderList[serial].agencyName;
    var memberName = orderList[serial].memberName;
    var orderDate = orderList[serial].orderDate;
    var expressCompany = orderList[serial].expressCompany;

    switch (orderStatus) {
        case 0: {
            orderStatus = "已确认收货";
            break;
        }
        case 1: {
            orderStatus = "未确认收货";
            break;
        }
        case 2: {
            orderStatus = "已发货";
            break;
        }
        case 3: {
            orderStatus = "未发货";
            break;
        }
        default:
            break
    }
    //运输信息
    var orderShipping = orderList[serial].orderShipping;
    var receiverProvince = orderShipping.receiverProvince;
    var receiverCity = orderShipping.receiverCity;
    var receiverArea = orderShipping.receiverArea;
    var receiverStreet = orderShipping.receiverStreet;
    var receiveZip = orderShipping.receiveZip;
    var receiverName = orderShipping.receiverName;
    var receiverMobile = orderShipping.receiverMobile;
    //显示信息
    $('#td_01').html(orderNumber);
    $('#td_11').html(orderStatus);
    $('#td_21').html(deliveringWarehouse);
    $('#td_31').html(expressNumber);
    $('#td_41').html(receiverName);
    $('#td_51').html(receiverProvince);
    $('#td_61').html(receiverArea);
    $('#td_71').html(receiverStreet);
    $('#td_02').html(sourceNumber);
    $('#td_12').html(buyerNickname);
    $('#td_22').html(expressCompany);
    $('#td_32').html(agencyName);
    $('#td_42').html(receiverMobile);
    $('#td_52').html(receiverCity);
    $('#td_62').html(receiveZip);
}

function getSaleOrderInfo_2(obj) {
    //获取订单在订单列表中的下标
    var serial = $(obj).attr("value");
    // alert(serial);
    // alert(orderList_2[serial].orderNumber);
    //订单信息
    var orderNumber = orderList_2[serial].orderNumber;
    var orderStatus = orderList_2[serial].orderStatus;
    var buyerNickname = orderList_2[serial].buyerNickname;
    var deliveringWarehouse = orderList_2[serial].deliveringWarehouse;
    var expressNumber = orderList_2[serial].expressNumber;
    var sourceNumber = orderList_2[serial].sourceNumber;
    var agencyName = orderList_2[serial].agencyName;
    var memberName = orderList_2[serial].memberName;
    var orderDate = orderList_2[serial].orderDate;
    var expressCompany = orderList_2[serial].expressCompany;


    //运输信息
    var orderShipping = orderList_2[serial].orderShipping;
    var receiverProvince = orderShipping.receiverProvince;
    var receiverCity = orderShipping.receiverCity;
    var receiverArea = orderShipping.receiverArea;
    var receiverStreet = orderShipping.receiverStreet;
    var receiveZip = orderShipping.receiveZip;
    var receiverName = orderShipping.receiverName;
    var receiverMobile = orderShipping.receiverMobile;
    //显示信息
    $('#td_01').html(orderNumber);
    $('#td_11').html(orderStatus);
    $('#td_21').html(deliveringWarehouse);
    $('#td_31').html(expressNumber);
    $('#td_41').html(receiverName);
    $('#td_51').html(receiverProvince);
    $('#td_61').html(receiverArea);
    $('#td_71').html(receiverStreet);
    $('#td_02').html(sourceNumber);
    $('#td_12').html(buyerNickname);
    $('#td_22').html(expressCompany);
    $('#td_32').html(agencyName);
    $('#td_42').html(receiverMobile);
    $('#td_52').html(receiverCity);
    $('#td_62').html(receiveZip);
}

function multiQuery() {
    var productNum = $('#queryProductNum').val();
    var productName = $('#queryProductName').val();
    var nickname = $('#queryNickname').val();

    var json = {
        "agencyName": "郎特姆公司",
        "productNumber": productNum,
        "productName": productName,
        "buyerNickname": nickname
    };
    //alert(JSON.stringify(json));
    $.ajax({
        type: "post",
        url: "queryOrder",
        contentType: 'application/json',
        data: JSON.stringify(json),
        success: function (map) {
            $("#dialog-on-html tr:not(:first)").html("");
            //alert("1");
            orderList_2 = map.orderPojoList;
            var k = 0;
            // $.each(map, function (key, value) {
            //     alert(key);
            //     alert(value);
            // });
            for (var i = 0; i < map.orderPojoList.length; i++) {
                //alert(orderList_2[i].orderNumber);
                var orderNumber = orderList_2[i].orderNumber;
                var sourceNumber = orderList_2[i].sourceNumber;
                var buyerNickname = orderList_2[i].buyerNickname;
                //alert(orderNumber + "  " + sourceNumber + "  " + buyerNickname);
                //商品列表
                var orderItems = orderList_2[i].orderItems;
                for (var j = 0; j < orderItems.length; j++) {
                    k += 1;
                    var productNumber = orderItems[j].productNumber;
                    var productName = orderItems[j].productName;
                    var purchaseQuantity = orderItems[j].purchaseQuantity;
                    var productPrice = orderItems[j].productPrice;

                    //alert(productName + "  " + productNumber + "  " + purchaseQuantity + "  " + productPrice);
                    //拼接表格
                    var sb = '<tr>';
                    sb += '                        <td>';
                    sb += '                            <input type="checkbox" name="isSelected" value="' + k + '">';
                    sb += '                        </td>';
                    sb += '                        <td>' + orderNumber + '</td>';
                    sb += '                        <td>' + sourceNumber + '</td>';
                    sb += '                        <td>' + buyerNickname + '</td>';
                    sb += '                        <td>' + productNumber + '</td>';
                    sb += '                        <td>' + productName + '</td>';
                    sb += '                        <td>' + purchaseQuantity + '</td>';
                    sb += '                        <td>' + productPrice + '</td>';
                    sb += '                        <td style="color: red;" onclick="deleteProduct()"><a onclick="getSaleOrderInfo_2(this);show2()"' + ' value="' + i + '">查看详情</a></td>';
                    sb += '                    </tr>';
                    $("#dialog-on-html").append(sb);
                }
            }
        }
    });
}

var saleOrderNumber;

function addOrder() {
    var $ids = [];    //定义一个空数组
    var $chkBoxes = $('#dialog-on-html').find('input:checked');   //找到被选中的checkbox集
    if ($chkBoxes.length == 0) {         //如果不勾选弹出警告框
        alert('请至少选择一个数据集');
        return false;
    }
    //遍历被选中的checkbox集
    $($chkBoxes).each(function () {
        //$ids.push($(this).attr('data-id'));   //找到对应checkbox中data-id属性值，然后push给空数组$ids
        //alert($(this).attr('value'));
        //判断选择的商品是否在同一个订单
        var ordernum = $(this).parent().parent().children("td").eq(1).html();
        //alert(ordernum);
        $ids.push(ordernum);
    });
    //判断选择的商品是否在同一个订单
    var isTrue = true;
    for (var i = 0; i < $ids.length; i++) {
        if ($ids.indexOf($ids[i]) != 0) {      //用indexOf来判断是否一样
            isTrue = false;
            break;
        }
    }
    if (isTrue) {
        //alert("一样！");
        //清空
        $("#tab-on-html tr:not(:first)").html("");
        //动态拼接
        //遍历被选中的checkbox集
        $($chkBoxes).each(function () {
            //alert($(this).attr('value'));
            //遍历，得到选中的那一行各个列的值
            var flag = 0;
            var inStr;
            $(this).parent().parent().children("td").each(function () {
                //var inStr;
                //第一次进入循环
                if (flag == 0) {
                    inStr += '<tr><td>';
                    //$("#tab-on-html").append(str1);
                    //$("#tab-on-html tbody").append('<td></td>');
                    inStr += '</td>';
                    flag = 1;
                    return true;//continue
                } else {//非第一次进入循环
                    //alert($(this).html());
                    //alert($(this).index());
                    if ($(this).index() == 1) {//销售订单号
                        $('#orderNumber').html('<option>' + $(this).html() + '</option>')
                        saleOrderNumber = $(this).html();
                    } else if ($(this).index() == 3) {//买家昵称
                        return true;//continue
                    } else if ($(this).index() == 7) {//开票价
                        inStr += '<td><input type="number" style="width: 50px;" onchange="calculateSum(this)"></td>';
                        //$("#tab-on-html tbody").append('<td><input type="number" style="width: 50px;"></td>');
                    } else if ($(this).index() == 8) {//操作
                        //<td id="operation" style="color: red;" onclick="deleteProduct()"><a>删除</a></td>
                        inStr += '<td style="color: red;" onclick="removeItem(this)"><a>删除</a></td>';
                        //$("#tab-on-html tbody").append('<td style="color: red;" onclick="deleteProduct()"><a>删除</a></td>');
                        return true;
                    }
                    var str = '<td>' + $(this).html() + '</td>';
                    inStr += str;
                    //$("#tab-on-html tbody").append(str);//.children("tr").eq(1).html()
                    if ($(this).index() == 7) {//开票价
                        inStr += '<td></td>';
                        //$("#tab-on-html tbody").append('<td></td>');
                    }
                }
            });
            inStr += '</tr>';
            //$("#tab-on-html").append('</tr>');
            $("#tab-on-html").append(inStr);
        });
        showExtraInfo();
    } else {
        alert("商品所属订单不同，请重新选择！");
    }
    //var $ids_str = $ids.join(',');              //将数组转化为用逗号隔开的字符串
    //( 还有一种写法：var $ids_str += ','+($ids + '') ) ，Number型加上空字符串''可以将Number转化为字符串
    // console.log($ids_str);                  // =>1,2
}

function calculateSum(obj) {
    var price = $(obj).parent().parent().children('td').eq(7).html();
    var max = $(obj).parent().parent().children('td').eq(5).html();

    $(obj).attr("max", max);
    $(obj).attr("min", 1);
    var price_2 = parseInt(price);
    var sum = $(obj).val() * price_2;
    $(obj).parent().parent().children('td').eq(8).html(sum);
}

function showExtraInfo() {
    //alert("57  " + saleOrderNumber);
    // alert(orderList);
    // console.log(orderList);
    var json = eval(orderList);
    var serial;

    $(json).each(function (index) {
        //alert("loop");
        var val = json[index];
        var orderNumber = json[index].orderNumber;
        if (orderNumber == saleOrderNumber) {
            serial = index;
            return false;
        }
    });
    var memberName = json[serial].memberName;
    //送货信息
    var orderShipping = json[serial].orderShipping;
    var receiverProvince = orderShipping.receiverProvince;
    var receiverCity = orderShipping.receiverCity;
    var receiverArea = orderShipping.receiverArea;
    var receiverStreet = orderShipping.receiverStreet;
    var receiveZip = orderShipping.receiveZip;
    var receiverName = orderShipping.receiverName;
    var receiverMobile = orderShipping.receiverMobile;
    //alert("33" + receiverCity);
    $('#memberName').html(memberName);
    $('#receiverProvince').html(receiverProvince);
    $('#receiverCity').html(receiverCity);
    $('#receiverArea').html(receiverArea);
    $('#receiverStreet').html(receiverStreet);
    $('#receiveZip').html(receiveZip);
    $('#receiverName').html(receiverName);
    $('#receiverMobile').html(receiverMobile);

}

function submitreturnOrder() {
    var flag = 0;
    //判断为空
    var $returnItems = $('#tab-on-html').find('tr:not(:first)');
    //遍历行
    $($returnItems).each(function () {
        var num = $(this).children('td').eq(6).children('input').val();
        if (num == "" || num == 0) {
            //alert("数量不能为空！")
            flag = 1;
            return false;
        }
    });
    var expressCompany = $("#expressCompany option:selected").text();
    var expressNum = $('#expressNum').val();
    var expressNum_2 = expressNum.replace(/\s+/g, "");
    var oWarehouseName = $("#oWarehouseName option:selected").text();
    var returnedWarehouseName = $("#returnedWarehouseName option:selected").text();
    // alert(expressCompany + expressNum + oWarehouseName + returnedWarehouseName);
    if (expressCompany == "" || expressNum_2 == "" || oWarehouseName == "" || returnedWarehouseName == "") {
        flag = 1;
    }
    if (flag == 1) {
        alert("请完善信息！");
        return false;
    } else {
        var saleOrderNumber = $('#orderNumber option:selected').text();
        var returnOrderStatu = 1;
        var returnType = $("#returnType option:selected").text();
        var returnReason = $('#returnReason').html();
        var expressCompany = $("#expressCompany option:selected").text();
        var expressNumber = $('#expressNum').val();
        var customerRemark = $('#agencyMark').val();
        var oWarehouseName = $("#oWarehouseName option:selected").text();
        var returnedWarehouseName = $("#returnedWarehouseName option:selected").text();
        var agencyName = "郎特姆公司";
        var memberName = $('#memberName').html();

        var item_json = '[';
        //遍历行
        var $returnItems = $('#tab-on-html').find('tr:not(:first)');
        $($returnItems).each(function () {
            var number = $(this).children().length;
            //console.log(number);
            if (number == 0) {
                //不含子节点
                return true;
            }
            //alert("99");
            var product_json = '';
            //商品编号
            var productNum = $(this).children('td').eq(3).html();
            //商品名称
            var productName = $(this).children('td').eq(4).html();
            //退回数量
            var returnAmount = $(this).children('td').eq(6).children('input').val();
            //退回金额
            var refund = $(this).children('td').eq(8).html();
            product_json += '{"returnProductNumber":"';
            product_json += productNum;
            product_json += '","returnProductName":"';
            product_json += productName;
            product_json += '","returnProductAmount":';
            product_json += returnAmount;
            product_json += ',"refundAmount":';
            product_json += refund;
            product_json += '},'
            //列表
            item_json += product_json;
        });
        item_json = item_json.substring(0, item_json.length - 1);
        item_json += ']';
        //alert(json);
        console.log(item_json);
        var json = {
            "returnOrderInfo": {
                "saleOrderNumber": saleOrderNumber,
                "returnOrderStatu": returnOrderStatu,
                "returnType": returnType,
                "returnReason": returnReason,
                "expressCompany": expressCompany,
                "expressNumber": expressNumber,
                "customerRemark": customerRemark,
                "oWarehouseName": oWarehouseName,
                "returnedWarehouseName": returnedWarehouseName,
                "agencyName": agencyName,
                "memberName": memberName
            },
            "returnOrderItems": $.parseJSON(item_json)
        };

        console.log(json);
        $.ajax({
            type: "post",
            url: "generateReturnOrder",
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (map) {
                alert("申请成功！");
            }
        });
    }

}

function removeItem(obj) {
    //alert("+");
    $(obj).parent().remove();
}