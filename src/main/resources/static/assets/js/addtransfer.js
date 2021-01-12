var prodID;                 // 产品编号
var prodID_normal = null;   // 产品编号 不是电商备货订单
var calloutO;               // 调出仓库机构名称
var calloutN;               // 调出仓库名称
var wID;                    // 调出仓库编号
var callinO;                // 调入仓库机构名称
var callinN;                // 调入仓库名称
var wID_in;                 // 调入仓库编号
var flag_stock = false;     // 是否选择了调拨单
var flag_wOut = false;      // 是否选择了调出仓库
var flag_wIn = false;       // 是否选择了调入仓库
var flag_repo = true;       // 可发货库存是否有

var orgName;   // 全局变量
// 获取 仓库机构
function getAllWOrganization()
{
	$.ajax({
		type: "get",
		url: "getallworganization",
		data: null,
		success: function (result)
		{
			var data = result;
			if (data.code === 0)
				orgName = data.list;
			else
				orgName = data.message;
			// 将列表放在select中
			var pro = $('#calloutOrg');
			var pro2 = $('#callinOrg');

			var options = '<option value="0" disabled selected style="display: none;">请选择</option>';
			for (var i = 0; i < orgName.length; ++i)
			{
				options += '<option value="'+ i + '">' + orgName[i] + '</option>';
			}
			pro.append(options);
			pro2.append(options);
		}
	});
}

// 根据 机构名称 获取该机构下的所有 仓库名称 调出仓库
var wName;   // 调出仓库名称列表 全局变量
function getWNameByWOrganizationOut()
{
	// 清空之前的option
	let size = $('#calloutwName').children().length;
	for (let i = 0; i < size; ++i)
	{
		$('#calloutwName').children().get($('#calloutwName').children().length - 1).remove();
	}

	// 拿到被选择的option的value
	var index = $('#calloutOrg').find("option:selected").val();
	calloutO = orgName[index];

	$.ajax({
		type: "get",
		url: "getwnamebyworganization?wOrganization=" + orgName[index],
		data: null,
		success: function (result)
		{
			var data = result;
			if (data.code === 0)
			{
				wName = data.list;
				// 将列表放在select中
				var pro = $('#calloutwName');
				var options = '<option value="0" disabled selected style="display: none;">请选择</option>';

				for (var i = 0; i < wName.length; ++i)
				{
					options += '<option value="'+ i + '">' + wName[i] + '</option>';
				}
				pro.append(options);
			}
			else
			{
				wName = data.message;
			}
		}
	});
}

// 当 调出仓库 选择后 获取选择的 仓库名称
function getSelectedWName()
{
	// 拿到被选择的option的value
	var index = $('#calloutwName').find("option:selected").val();
	calloutN = wName[index];
}

// 根据 机构名称 获取该机构下的所有 仓库名称 调入仓库
var wName_in;   // 调入仓库名称列表 全局变量
function getWNameByWOrganizationIn()
{
	// 清空之前的option
	let size = $('#callinwName').children().length;
	for (let i = 0; i < size; ++i)
	{
		$('#callinwName').children().get($('#callinwName').children().length - 1).remove();
	}

	// 拿到被选择的option的value
	var index = $('#callinOrg').find("option:selected").val();
	callinO = orgName[index];   // 获得选择的调入机构的名称

	// 修改发往城市字段
	$('#city').html(orgName[index]);

	$.ajax({
		type: "get",
		url: "getwnamebyworganization?wOrganization=" + orgName[index],
		data: null,
		success: function (result)
		{
			var data = result;
			if (data.code === 0)
			{
				wName_in = data.list;
				// 将列表放在select中
				var pro = $('#callinwName');
				var options = '<option value="0" disabled selected style="display: none;">请选择</option>';

				for (var i = 0; i < wName_in.length; ++i)
				{
					options += '<option value="'+ i + '">' + wName_in[i] + '</option>';
				}
				pro.append(options);
			}
			else
			{
				wName_in = data.message;
			}
		}
	});
}

// 当 调入仓库 选择后 获取选择的 仓库名称
function getSelectedWName_in()
{
	// 拿到被选择的option的value
	var index = $('#callinwName').find("option:selected").val();
	callinN = wName_in[index];
}

// 获取 经销商姓名
var agencyName;   // 全局变量
var agencyName_trans;
function getAgencyName()
{
	$.ajax({
		type: "get",
		url: "getagencyname",
		data: null,
		success: function (result)
		{
			var data = result;
			if (data.code === 0)
			{
				agencyName = data.list;
				// 将列表放在select中
				var pro = $('#agencyName');

				var options = '<option value="0" disabled selected style="display: none;">请选择</option>';
				for (var i = 0; i < agencyName.length; ++i)
				{
					options += '<option value="'+ i + '">' + agencyName[i] + '</option>';
				}
				pro.append(options);
			}
			else
			{
				agencyName = data.message;
			}
		}
	});
}

// 通过 经销商名称 获取 其送货地址
var deliverAd;   // 送货地址 全局变量
function getDeliverAdByAgencyName()
{
	// 清空之前的option
	let size = $('#deliverAd').children().length;
	for (let i = 0; i < size; ++i)
	{
		$('#deliverAd').children().get($('#deliverAd').children().length - 1).remove();
	}

	// 拿到被选择的option的value
	var index = $('#agencyName').find("option:selected").val();
	agencyName_trans = agencyName[index];

	$.ajax({
		type: "get",
		url: "getdeliveradbyagencyname?agencyName=" + agencyName[index],
		data: null,
		success: function (result)
		{
			var data = result;
			if (data.code === 0)
			{
				deliverAd = data.list;
				// 将列表放在select中
				var pro = $('#deliverAd');

				var options = '<option value="0" disabled selected style="display: none;">请选择</option>';
				for (var i = 0; i < deliverAd.length; ++i)
				{
					options += '<option value="'+ i + '">' + deliverAd[i] + '</option>';
				}
				pro.append(options);
			}
			else
			{
				deliverAd = data.message;
			}
		}
	});
}

// 选择收货地址后 自动反显 收货人名称 收货人电话 收货人身份证
function getAllByDeliverAd()
{
	// 拿到被选择的option的value
	var index = $('#deliverAd').find("option:selected").val();

	var agencyInfo;

	$.ajax({
		type: "get",
		url: "getallbydeliverad?deliverAd=" + deliverAd[index],
		data: null,
		success: function (result)
		{
			let data = result;
			if (data.code === 0)
			{
				agencyInfo = data.list;
				// 将对应信息放入span
				$('#consignee').html(agencyInfo[0].consignee);
				$('#consigneeTel').html(agencyInfo[0].consigneeTel);
				$('#consigneeID').html(agencyInfo[0].consigneeID);
			}
			else
			{
				agencyInfo = data.message;
			}
		}
	});
}

// 获取当前系统时间 yyyy-mm-dd HH:mm:ss
var time;   // 系统时间 全局变量
function getSystemTime()
{
	let date = new Date();
	let year = date.getFullYear();     // 年
	let month = date.getMonth() + 1;   // 月
	if (month.toString().length === 1)
	{
		month = '0' + month.toString();
	}
	let day = date.getDate();          // 日
	if (day.toString().length === 1)
	{
		day = '0' + day.toString();
	}
	let hour = date.getHours();        // 时
	if (hour.toString().length === 1)
	{
		hour = '0' + hour.toString();
	}
	let min = date.getMinutes();       // 分
	if (min.toString().length === 1)
	{
		min = '0' + min.toString();
	}
	let sec = date.getSeconds();       // 秒
	if (sec.toString().length === 1)
	{
		sec = '0' + sec.toString();
	}
	time = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
	$('#createDate').html(time);
}

// 当 调拨类型 选择 电商备货调拨 时 出现 备货计划订单 选项
var id = null;   // stockOrderID 列表 全局变量
function getStockOrderID()
{
	// 拿到被选择的option的value
	let index = $('#tsType').find("option:selected").val();

	let display = document.getElementById('stockOrderID-div');   // 设置 该字段是否显示

	if (index == 3)   // 选择了 电商备货调拨
	{
		flag_stock = true;   // 标记选择了 电商备货订单

		display.style.display = "inline-block";
		$.ajax({
			type: "get",
			url: "getallstockorderid",
			data: null,
			success: function (result)
			{
				let data = result;
				if (data.code === 0)
				{
					id = data.list;
					// 将列表放在select中
					var pro = $('#stockOrderID');

					var options = '<option value="0" disabled selected style="display: none;">请选择</option>';
					for (var i = 0; i < id.length; ++i)
					{
						options += '<option value="'+ i + '">' + id[i].stockOrderID + '</option>';
					}
					pro.append(options);
				}
				else
				{
					id = id.message;
				}
			}
		});
	}
	else
	{
		display.style.display = "none";

		flag_stock = false;   // 如果选的不是，则false
	}
}

// 当备货订单编号被选择后，反显出产品信息
let productInfo;    // 获取的产品信息 全局变量
var stockOrderID = null;   // 电商备货订单编号 全局变量
function getAllByStockOrderID()
{
	// 拿到被选择的option的value
	let index = $('#stockOrderID').find("option:selected").val();
	stockOrderID = id[index].stockOrderID;

	$.ajax({
		type: "get",
		url: "getallbystockorderid?stockOrderID=" + id[index].stockOrderID,
		data: null,
		success: function (result)
		{
			let data = result;
			if (data.code === 0)
			{
				productInfo = data.list;
				prodID = productInfo[0].productID;
				// 将结果放在表格中
				$('#productID').html(productInfo[0].productID);
				$('#productName').html(productInfo[0].productName);
				$('#tsQuantity').html(productInfo[0].tsQuantity);
				$('#tsVolumeDose').val("");
				$('#tsVolumeDose').val(productInfo[0].tsQuantity);
				$('#actualShipments').html(productInfo[0].actualShipments);
				$('#checkQuantity').html(productInfo[0].checkQuantity);
				$('#signedQuantity').html(productInfo[0].signedQuantity);
				$('#unit').html(productInfo[0].unit);
				$('#unitVolume').html(productInfo[0].unitVolume);
				$('#tsTotalVolume').html(productInfo[0].unitVolume * productInfo[0].tsQuantity);

				// 拿库存 放到表格
				getRepertory(wID, prodID);
				getRepertory_in(wID_in, prodID);

				// 设置 调拨总量可修改
				document.getElementById('tsVolumeDose').readOnly = false;
			}
			else
			{
				productInfo = data.message;
			}
		}
	});
}

// 根据 机构 和 仓库名称 得到 仓库编号
function getWID(str)
{
	if (str === "out")
	{
		$.ajax({
			type: "get",
			url: 'getwidbyworgandwname?wOrganization=' + calloutO + '&wName=' + calloutN,
			data: null,
			success: function (result)
			{
				let data = result;
				if (data.code === 0)
				{
					wID = data.list[0];
					// 将 发出库位编号 和 发出库位名称 放入 表格
					$('#calloutwID-td').html(wID);
					$('#calloutwName-td').html(calloutN);

					// 置 是否选择调出仓库 为 true
					flag_wOut = true;
				}
				else
				{
					wID = data.message;
				}
			}
		});
	}
	else if (str === "in")
	{
		$.ajax({
			type: "get",
			url: 'getwidbyworgandwname?wOrganization=' + callinO + '&wName=' + callinN,
			data: null,
			success: function (result)
			{
				let data = result;
				if (data.code === 0)
				{
					wID_in = data.list[0];
					// 将 接收库位编号 和 接收库位名称 放入 表格
					$('#callinwID-td').html(wID_in);
					$('#callinwName-td').html(callinN);

					// 置 是否选择调入仓库 为 true
					flag_wIn = true;
				}
				else
				{
					wID_in = data.message;
				}
			}
		});
	}
}

// 根据 仓库编号和产品编号，查找该产品在该仓库中的 库存
function getRepertory(wID, prodID)
{
	let repertory;   // 库存
	// 修改字体颜色，防止混淆
	let style = document.getElementById('repertory');
	style.style.color = "black";

	$.ajax({
		type: "get",
		url: "getrepertory?wID=" + wID + '&productID=' + prodID,
		data: null,
		success: function (result)
		{
			let data = result;
			if (data.code === 0)
			{
				repertory = data.list;
				// 将 库存 放到表格中
				flag_repo = true;   // 有库存
				$('#repertory').html(repertory[0]);
				document.getElementById('tsVolumeDose').readOnly = false;
			}
			else
			{
				repertory = data.message;
				style = document.getElementById('repertory');
				style.style.color = "red";
				$('#repertory').html("库存不足");
				flag_repo = false;   // 库存不足
				$('#tsVolumeDose').val(0);
				document.getElementById('tsVolumeDose').readOnly = true;
				$('#tsQuantity').html("-");
				$('#actualShipments').html("-");
				$('#checkQuantity').html("-");
				$('#signedQuantity').html("-");
			}
		}
	});
}

// 根据 仓库编号和产品编号，查找该产品在 放入仓库 中的 库存
function getRepertory_in(wID_in, prodID)
{
	let repertory_in;   // 放入仓库 的 库存

	$.ajax({
		type: "get",
		url: "getrepertory?wID=" + wID_in + '&productID=' + prodID,
		data: null,
		success: function (result)
		{
			let data = result;
			if (data.code === 0)
			{
				repertory_in = data.list;
				// 将 放入仓库 的 库存 放到表格中
				$('#repertory-td').html(repertory_in[0]);
			}
			else if (data.code === 1)
			{
				$('#repertory-td').html(0);
			}
		}
	});
}

// 当 调拨数量 被修改时 传入数据库
function updateTsQuantity()
{
	let tsQuantity = parseInt($('#tsVolumeDose').val());

	if (tsQuantity >= 1000000000 || tsQuantity <= -1)   // 超过Integer上限
	{
		alert("输入不合法，大于仓库库存数量或小于0\n\n请重新输入");
		let num = parseInt(productInfo[0].tsQuantity);
		$('#tsVolumeDose').val(num);
	}
	else
	{
		$.ajax({
			type: "get",
			url: "updatetsquantity?productID=" + prodID_normal + "&tsQuantity=" + tsQuantity + "&wID=" + wID,
			data: null,
			success: function (result)
			{
				if (result.code === 1)
				{
					alert("输入不合法，大于仓库库存数量或小于0\n\n请重新输入");
					let num = parseInt(productInfo[0].tsQuantity);
					$('#tsVolumeDose').val(num);
				}
				else if (result.code === 0)
				{
					if (flag_stock)   // 是 电商备货订单
					{
						// 重新调用 getAllByStockOrderID 更新productInfo和表格
						getAllByStockOrderID();
					}
					else   // 是其他订单
					{
						// 重新调用 getAllByProductID 更新productInfo和表格
						getAllByProductID();
					}
				}
			}
		});
	}
}

// 点击 导入 弹出一个div 列出所有产品信息 可以选择 适用于类型不是 电商备货调拨 的
let productList;   // 所有产品信息 全局变量
function outDivByCLick()
{
	// 显示 两个div
	document.getElementById('light-down').style.display = "block";
	document.getElementById('div-pop-product').style.display = "block";
	document.documentElement.style.overflow = "hidden";

	// 显示 所有的 产品信息
	$.ajax({
		type: 'get',
		url: 'getallproductinfo',
		data: null,
		success: function (result)
		{
			if (result.code === 0)
			{
				productList = result.list;

				var pro = $('#tab-pro');
				var trs = '<tr style="background-color: #f2f2f2">' +
					'   <th>序号</th>' +
					'   <th>产品编号</th>' +
					'   <th>产品名称</th>' +
					'   <th>品类</th>' +
					'   <th>单位</th>' +
					'   <th>单位体积</th>' +
					'   <th>产品单价</th>' +
					'   <th>选择</th>' +
					'</tr>';
				// 放入表格
				for (let i = 0; i < productList.length; ++i)
				{
					trs += '<tr id=' + i + ' onclick="getTrID(this)">' +
						'   <td>' + productList[i].serialNum + '</td>' +
						'   <td id="productID-' + i + '">' + productList[i].productID + '</td>' +
						'   <td>' + productList[i].productName + '</td>' +
						'   <td>' + productList[i].type + '</td>' +
						'   <td>' + productList[i].unit + '</td>' +
						'   <td>' + productList[i].unitVolume + '</td>' +
						'   <td>' + productList[i].price + '</td>' +
						'   <td id="isChecked-td-' + i + '" onclick="isChecked(this)"><i id="isChecked-i-' + i + '" class="fa fa-check fa-lg" style="color: #00C853; display: none;"></i></td>' +
						'</tr>';
				}
				pro.append(trs);
			}
			else
			{
				productList = result.message;
			}
		}
	});
}

// 弹出div 选择产品 单选 点击出现 √
function isChecked(obj)
{
	// 清除其他打钩的 行 的√
	for (let i = 0; i < productList.length; ++i)
	{
		document.getElementById('isChecked-i-' + i).style.display = "none";
	}
	// 点击的行打√
	let str = obj.id;
	let strArr = str.split('-');
	let id_i = strArr[0] + '-' + 'i' + '-' + strArr[2];
	// alert(id_i)
	document.getElementById(id_i).style.display = "inline";
}

// 获取一行 tr 的id
function getTrID(obj)
{
	// 拿到对应的 productID
	prodID_normal = productList[parseInt(obj.id)].productID;
}

// 关闭 弹出的div
function closeDiv()
{
	// 隐藏两个 div
	document.getElementById('light-down').style.display = "none";
	document.getElementById('div-pop-product').style.display = "none";
	document.documentElement.style.overflow = "auto";

	// 清空之前的 tr
	$('#tab-pro').find('tr').remove();
}

// 根据 产品编号 查找其所有信息
function getAllByProductID()
{
	if (prodID_normal != null)
	{
		$.ajax({
			type: "get",
			url: "getallbyproductid?productID=" + prodID_normal,
			data: null,
			success: function (result)
			{
				let data = result;
				if (data.code === 0)
				{
					productInfo = data.list;
					prodID_normal = productInfo[0].productID;
					// 将结果放在表格中
					$('#productID').html(productInfo[0].productID);
					$('#productName').html(productInfo[0].productName);
					$('#tsQuantity').html(productInfo[0].tsQuantity);
					$('#tsVolumeDose').val("");
					$('#tsVolumeDose').val(productInfo[0].tsQuantity);
					$('#actualShipments').html(productInfo[0].actualShipments);
					$('#checkQuantity').html(productInfo[0].checkQuantity);
					$('#signedQuantity').html(productInfo[0].signedQuantity);
					$('#unit').html(productInfo[0].unit);
					$('#unitVolume').html(productInfo[0].unitVolume);
					$('#tsTotalVolume').html(productInfo[0].unitVolume * productInfo[0].tsQuantity);

					// 拿库存 放到表格
					getRepertory(wID, prodID_normal);
					getRepertory_in(wID_in, prodID_normal);
				}
				else
				{
					productInfo = data.message;
				}
			}
		});
	}
}

// 删除选择的产品信息
var typeText = '';    // 调拨类型 文本 全局变量
function deleteProduct()
{
	// 移除表格 重新添加
	$('#tab-on-html').find('tr').remove();
	$('#tab-on-html').append(
		'<tr style="background-color: #f2f2f2">' +
		'   <th>序号</th>' +
		'   <th>发出库位编号</th>' +
		'   <th>发出库位名称</th>' +
		'   <th>接收库位编号</th>' +
		'   <th>接收库位名称</th>' +
		'   <th>产品编号</th>' +
		'   <th>产品名称</th>' +
		'   <th>可发货库存</th>' +
		'   <th>调入仓库库存</th>' +
		'   <th>调拨数量</th>' +
		'   <th>审核数量</th>' +
		'   <th>实际发货数量</th>' +
		'   <th>已签收数量</th>' +
		'   <th>单位</th>' +
		'   <th>单位体积</th>' +
		'   <th>操作</th>' +
		'</tr>' +
		'<tr>' +
		'   <td id="serialNum">1</td>' +
		'   <td id="calloutwID-td"></td>' +
		'   <td id="calloutwName-td"></td>' +
		'   <td id="callinwID-td"></td>' +
		'   <td id="callinwName-td"></td>' +
		'   <td id="productID"></td>' +
		'   <td id="productName"></td>' +
		'   <td id="repertory"></td>' +
		'   <td id="repertory-td"></td>' +
		'   <td id="tsQuantity"></td>' +
		'   <td id="checkQuantity"></td>' +
		'   <td id="actualShipments"></td>' +
		'   <td id="signedQuantity"></td>' +
		'   <td id="unit"></td>' +
		'   <td id="unitVolume"></td>' +
		'   <td id="operation" style="color: red;" onclick="deleteProduct()"><i class="fa fa-times-circle fa-lg"></i></td>' +
		'</tr>'
	);

	// 默认信息不变
	$('#calloutwID-td').html(wID);
	$('#calloutwName-td').html(calloutN);
	$('#callinwID-td').html(wID_in);
	$('#callinwName-td').html(callinN);

	$('#tsVolumeDose').val(0);
	$('#tsTotalVolume').html('');

	if (flag_stock)   // 如果 调拨类型 是 备货订单
	{
		// 休眠一段时间再弹
		setTimeout(function (){alert("已删除选择的电商备货调拨订单的产品信息\n\n请重新选择");}, 50);
	}
	else   // 如果 调拨类型 不是 备货订单
	{
		// 休眠一段时间再弹
		setTimeout(function (){alert("已删除选择的" + typeText + "订单的产品信息\n\n请重新选择");}, 50);
	}
}

// 拿到调拨类型
function getType()
{
	var option = $('#tsType').find("option:selected");
	// alert(option.text());
	if (option.text() !== '电商备货调拨')
	{
		typeText = option.text();
	}
	else
	{
		typeText = '电商备货调拨';
		stockOrderID = null;
	}
}

// 拿到发运方式
var transM;
function getTransMode()
{
	var option = $('#type-send').find("option:selected");
	transM = option.text();
}

// 获取经销商编号
var agencyID;
function getAgencyID()
{
	// 获取索引，进而得到 送货地址
	let index = $('#deliverAd').find("option:selected").val();

	$.ajax({
		type: "get",
		url: "getagencyid?agencyName=" + agencyName_trans + "&deliverAd=" + deliverAd[index],
		data: null,
		success: function (result)
		{
			if (result.code === 0)
			{
				agencyID = result.list[0];
				// alert(agencyID);
			}
			else
			{
				agencyID = result.message;
			}
		}
	});

}

// 提交调拨单
function submitTransfer()
{
	if (!flag_repo)   // 如果库存不足，无法提交
	{
		alert("库存不足\n\n请重新选择产品或与管理员联系");
		return ;
	}
	
	getSystemTime();   // 调用 刷新 时间
	var transferInfo;
	var tsID = $('#tsID').text();
	var creator = $('#creator').text();
	var tsVolumeDose = document.getElementById('tsVolumeDose').valueOf().value;
	var tsTotalVolume = $('#tsTotalVolume').text();
	var remark = document.getElementById('remark').valueOf().value;
	var date = new Date();
	if (id !== null)   // 是电商备货订单
	{
		transferInfo = {   // 调拨单信息
			tsID: tsID,
			tsType: typeText,
			creator: creator,
			calloutwID: wID,
			callinwID: wID_in,
			transMode: transM,
			stockOrderID: stockOrderID,
			agencyID: agencyID,
			tsVolumeDose: tsVolumeDose,
			tsTotalVolume: tsTotalVolume,
			createDate: date,
			productID: prodID,
			remark: remark
		};
	}
	else   // 不是电商备货订单
	{
		transferInfo = {   // 调拨单信息
			tsID: tsID,
			tsType: typeText,
			creator: creator,
			calloutwID: wID,
			callinwID: wID_in,
			transMode: transM,
			stockOrderID: null,
			agencyID: agencyID,
			tsVolumeDose: tsVolumeDose,
			tsTotalVolume: tsTotalVolume,
			createDate: date,
			productID: prodID_normal,
			remark: remark
		};
	}

	console.log(transferInfo);

	$.ajax({
		type: 'POST',
		url: 'addintotransferinfo',
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: JSON.stringify(transferInfo),
		async: false,
		success: function (result)
		{
			if (result.code === 0)
			{
				alert(result.message);
				window.location.reload();   // 点击确定后 自动刷新页面
			}
			else
			{
				alert(result.message);
				window.location.reload();   // 点击确定后 自动刷新页面
			}
		}
	});
}

// 系统生成 调拨单号
function makeATsID()
{
	let str = '';

	$.ajax({
		type: 'get',
		url: 'gettsidlatest',
		data: null,
		success: function (result)
		{
			if (result.code === 0)
			{
				str = result.list[0];
				if (str.substring(0, 14) !== 'TSIDBYFREE2020')
				{
					str = 'TSIDBYFREE20201231';
					$('#tsID').html(str);
				}
				else
				{
					let num = 0;
					let mul = 1;
					for (let i = 0; i < 4; ++i)
					{
						num += parseInt(str[str.length - 1 - i]) * mul;
						mul *= 10;
					}
					num += 1;
					str = str.substring(0, 14);
					str = str + num.toString();
					$('#tsID').html(str);
				}
			}
			else
			{
				str = result.message;
			}
		}
	});
}

// 当 调入仓库和调出仓库未被选择时，无法导入产品信息
// 或 选择电商备货订单时 无法导入产品信息
function canAddProductInfo()
{
	if (flag_stock)   // 如果电商备货订单
	{
		alert("已选择电商备货调拨，无需导入产品信息\n\n请选择电商备货订单");
	}
	else if (!flag_wOut && !flag_wIn)   // 如果两个仓库未被选择
	{
		alert("未选择调出仓库和调入仓库\n\n请选择后再导入产品信息");
	}
	else
	{
		outDivByCLick();
	}
}

// 拿到登录用户名
var map = null;
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
			$('#login-truename')[0].innerHTML=map["login_truename"];
			$('#login_truename')[0].innerHTML=map["login_truename"];
			$('#login_rName')[0].innerHTML=map["login_rname"];
			$('#creator')[0].innerHTML=map["login_username"];
			$('#organization')[0].innerHTML=map["organization"];
		}
	});
});

// 点击退出按钮 退出
function logout() {
	$.ajax({
		type: "POST",
		url: "logout",
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: null,
		async: false,
		success: function (msg) {
			if (msg === 0){
				window.location.href="login.html";
			} else {
				alert("退出时遇到问题，请查看后台");
			}
		}
	});
}