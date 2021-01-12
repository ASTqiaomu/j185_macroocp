var getAllFlag = false;
var returnOrderList;
var agencyName = "郎特姆公司";

function getAllReturnOrderByAgencyName() {
    //alert("start");
    if (getAllFlag === true) {
        return;
    }
    getAllFlag = true;
    //var agencyName = "郎特姆公司";

    $.ajax({
        type: "get",
        url: "showAllReturnOrders?agencyName=" + agencyName,
        data: null,
        success: function (map) {
            //alert("success");
            returnOrderList = map.returnOrderPojoList;
            for (var i = 0; i < map.returnOrderPojoList.length; i++) {
                //alert(orderList[i].orderNumber);
                var returnOrderNumber = returnOrderList[i].returnOrderNumber;
                var returnOrderDate = returnOrderList[i].returnOrderDate;
                var saleOrderNumber = returnOrderList[i].saleOrderNumber;
                var returnOrderStatu = returnOrderList[i].returnOrderStatu;
                var returnType = returnOrderList[i].returnType;
                var oWarehouseName = returnOrderList[i].owarehouseName;
                var returnedWarehouseName = returnOrderList[i].returnedWarehouseName;
                var memberName = returnOrderList[i].memberName;
                var returnedPayment = returnOrderList[i].returnedPayment;
                if (returnedPayment === null) {
                    returnedPayment = '';
                }

                switch (returnOrderStatu) {
                    case 1: {
                        returnOrderStatu = "待收货";
                        break;
                    }
                    case 2: {
                        returnOrderStatu = "已取消";
                        break;
                    }
                    case 3: {
                        returnOrderStatu = "待审核";
                        break;
                    }
                    case 4: {
                        returnOrderStatu = "已驳回";
                        break;
                    }
                    case 5: {
                        returnOrderStatu = "待审核结算";
                        break;
                    }
                    case 6: {
                        returnOrderStatu = "已审核结算";
                        break;
                    }
                    case 7: {
                        returnOrderStatu = "已完成";
                        break;
                    }
                    default:
                        break
                }
                //拼接
                var html = '';
                html += "<tr>" + "<td>" + returnOrderNumber + "</td>" +
                    "<td>" + returnOrderDate + "</td>" +
                    "<td>" + saleOrderNumber + "</td>" +
                    "<td>" + returnedPayment + "</td>" +
                    "<td>" + returnOrderStatu + "</td>" +
                    "<td>" + returnType + "</td>" +
                    "<td>" + memberName + "</td>" +
                    "<td>" + oWarehouseName + "</td>" +
                    "<td>" + returnedWarehouseName + "</td>" +
                    "<td>" + "<a>-</a>" + "</td>" + "</tr>";
                //alert(returnOrderDate + "  " + returnedPayment + "  " + parseInt(returnedPayment));
                $('#transferlistshow').append(html);
            }
        }
    });
}

function mutiQuery() {
    //alert("mutil");
    var returnOrderNumber = $("#returnOrderNumber").val();
    var saleOrderNumber = $("#saleOrderNumber").val();
    var returnOrderStatu = $("#returnOrderStatu option:selected").val();
    var returnOrderStatu_2 = parseInt(returnOrderStatu);
    //alert(returnOrderStatu_2);
    var returnReason = $("#returnReason").val();
    var expressCompany = $("#expressCompany option:selected").text();
    var expressNumber = $("#expressNumber").val();
    //var oWarehouseName = $("#oWarehouseName option:selected").val();
    var returnedWarehouseName = $("#returnWarehouseName option:selected").val();

    var json = {
        "agencyName": agencyName,
        "returnOrderNumber": returnOrderNumber,
        "saleOrderNumber": saleOrderNumber,
        "returnOrderStatu": returnOrderStatu_2,
        "returnReason": returnReason,
        "expressCompany": expressCompany,
        "expressNumber": expressNumber,
        "returnedWarehouseName": returnedWarehouseName
    };
    console.log(json);
    console.log(JSON.stringify(json));
    $.ajax({
        type: "post",
        url: "queryReturnOrder",
        contentType: 'application/json',
        data: JSON.stringify(json),
        success: function (map) {
            //alert("success");
            var returnOrderList = map.returnOrderPojoList;
            //清空
            $("#transferlistshow").html("");
            for (var i = 0; i < map.returnOrderPojoList.length; i++) {
                //alert(orderList[i].orderNumber);
                var returnOrderNumber = returnOrderList[i].returnOrderNumber;
                var returnOrderDate = returnOrderList[i].returnOrderDate;
                var saleOrderNumber = returnOrderList[i].saleOrderNumber;
                var returnOrderStatu = returnOrderList[i].returnOrderStatu;
                var returnType = returnOrderList[i].returnType;
                var oWarehouseName = returnOrderList[i].owarehouseName;
                var returnedWarehouseName = returnOrderList[i].returnedWarehouseName;
                var memberName = returnOrderList[i].memberName;
                var returnedPayment = returnOrderList[i].returnedPayment;
                if (returnedPayment === null) {
                    returnedPayment = '';
                }
                switch (returnOrderStatu) {
                    case 1: {
                        returnOrderStatu = "待收货";
                        break;
                    }
                    case 2: {
                        returnOrderStatu = "已取消";
                        break;
                    }
                    case 3: {
                        returnOrderStatu = "待审核";
                        break;
                    }
                    case 4: {
                        returnOrderStatu = "已驳回";
                        break;
                    }
                    case 5: {
                        returnOrderStatu = "待审核结算";
                        break;
                    }
                    case 6: {
                        returnOrderStatu = "已审核结算";
                        break;
                    }
                    case 7: {
                        returnOrderStatu = "已完成";
                        break;
                    }
                    default:
                        break
                }

                //拼接
                var html = '';
                html += "<tr><td>" + returnOrderNumber + "</td>" +
                    "<td>" + returnOrderDate + "</td>" +
                    "<td>" + saleOrderNumber + "</td>" +
                    "<td>" + returnedPayment + "</td>" +
                    "<td>" + returnOrderStatu + "</td>" +
                    "<td>" + returnType + "</td>" +
                    "<td>" + memberName + "</td>" +
                    "<td>" + oWarehouseName + "</td>" +
                    "<td>" + returnedWarehouseName + "</td>" +
                    "<td>" + "<a>-</a>" + "</td>" + "</tr>";
                //alert(returnOrderDate + "  " + returnedPayment + "  " + parseInt(returnedPayment));
                $('#transferlistshow').append(html);
            }
        }
    });
}