DROP DATABASE
IF EXISTS MacroOCP;

CREATE DATABASE MacroOCP;

USE MacroOCP;

DROP TABLE
IF EXISTS ORT;

DROP TABLE
IF EXISTS OAT;

DROP TABLE
IF EXISTS LogT;

DROP TABLE
IF EXISTS BuyerT;

DROP TABLE
IF EXISTS WarehouseInfoT;

DROP TABLE
IF EXISTS StockOrderT;

DROP TABLE
IF EXISTS AgencyInfoT;

DROP TABLE
IF EXISTS TransferInfoT;

DROP TABLE
IF EXISTS ProductInfoT;

DROP TABLE
IF EXISTS ACRT;

DROP TABLE
IF EXISTS OrderInfoT;

DROP TABLE
IF EXISTS ReturnOrderInfoT;

DROP TABLE
IF EXISTS WRepertoryT;

-- 运营角色表（OperationalRoleTable）
CREATE TABLE ORT (
	-- 01_角色名称_主键
	rName NVARCHAR (20) NOT NULL,
	-- 02_角色编号_唯一，格式ORxxx，OR代表运营角色，xxx为具体编号
	rID VARCHAR (10) NOT NULL,
	-- 03_角色描述，默认为空
	rDescription NVARCHAR (20) DEFAULT NULL,
	-- 04_角色状态，0为禁用，1为启用，默认启用
	rStatus INT DEFAULT 1,
	-- 05_权限等级，0为最高权限，数字越大，权限越低
	rPermissionLevel INT NOT NULL,
	PRIMARY KEY (rName),
	UNIQUE KEY (rID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 运营账号表（OperatingAccountTable）
CREATE TABLE OAT (
	-- 01_用户名_主键
	userName NVARCHAR (20) NOT NULL,
	-- 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
	userID VARCHAR (10) NOT NULL,
	-- 03_密码
	PASSWORD VARCHAR (20) NOT NULL,
	-- 04_真实姓名
	trueName NVARCHAR (20) NOT NULL,
	-- 05_所属机构
	organization NVARCHAR (20) NOT NULL,
	-- 06_所属产品线
	productLines NVARCHAR (20) NOT NULL,
	-- 07_角色名称，可以同时是多个角色，以字段拼接实现
	rName NVARCHAR (50) DEFAULT '全部',
	-- 08_性别，M为男性，F为女性
	sex VARCHAR (1) NOT NULL,
	-- 09_手机号码，11位为手机号码，不考虑国家代码
	phone VARCHAR (11) NOT NULL,
	-- 10_电子邮箱
	email VARCHAR (40) NOT NULL,
	-- 11_账号状态，0为禁用，1为启用，默认启用
	aStatus INT NOT NULL DEFAULT 1,
	PRIMARY KEY (userName),
	UNIQUE KEY (userID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 日志表（LogTable）
CREATE TABLE LogT (
	-- 01_日志编号_主键
	logID INT NOT NULL,
	-- 02_操作类型，增加ADD，删除DEL，编辑EDT
	opType VARCHAR (3) NOT NULL,
	-- 03_操作对象，必须是ORxxx或者OAxxx，逻辑上来说是外键
	opObject VARCHAR (10) NOT NULL,
	-- 04_操作时间，操作时由JAVA后端获取当前时间
	opTime DATETIME NOT NULL,
	-- 05_操作内容，采用字段拼接，具体参考测试数据
	opDescription NVARCHAR (200) NOT NULL,
	PRIMARY KEY (logID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 抬头表（BuyerTable）
CREATE TABLE BuyerT (
	-- 01_事业部_主键
	businessUnit NVARCHAR (20) NOT NULL,
	-- 02_所属公司名称
	companyName NVARCHAR (50) NOT NULL,
	PRIMARY KEY (businessUnit)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 仓库信息表（WarehouseInfoTable）
CREATE TABLE WarehouseInfoT (
	-- 01_仓库编号_主键
	wID VARCHAR (10) NOT NULL,
	-- 02_仓库机构
	wOrganization NVARCHAR (50) NOT NULL,
	-- 03_仓库名称_唯一
	wName NVARCHAR (20) NOT NULL,
	PRIMARY KEY (wID),
	UNIQUE KEY (wName)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 备货订单表（StockOrderTable）
CREATE TABLE StockOrderT (
	-- 01_备货订单编号_主键
	stockOrderID VARCHAR (20) NOT NULL,
	PRIMARY KEY (stockOrderID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 经销商信息表（AgencyInfoTable）
CREATE TABLE AgencyInfoT (
	-- 01_经销商编号_主键
	agencyID VARCHAR (20) NOT NULL,
	-- 02_经销商名称
	agencyName NVARCHAR (50) NOT NULL,
	-- 03_送货地址_唯一
	deliverAd NVARCHAR (50) NOT NULL,
	-- 04_收货人
	consignee NVARCHAR (20) NOT NULL,
	-- 05_收货人电话
	consigneeTel VARCHAR (11) NOT NULL,
	-- 06_收货人身份证
	consigneeID VARCHAR (18) NOT NULL,
	PRIMARY KEY (agencyID),
	UNIQUE KEY (deliverAd)
) ENGINE = INNODB DEFAULT CHARSET = utf8;


-- 产品信息表（ProductInfoTable）
CREATE TABLE ProductInfoT (
	-- 01_序号_唯一
	serialNum INT NOT NULL,
	-- 02_产品编码_主键
	productID VARCHAR (30) NOT NULL,
	-- 03_产品名称
	productName NVARCHAR (50) NOT NULL,
	-- 04_品类
	type VARCHAR (20) NOT NULL,
	-- 05_调入仓库库存
	callinWInventory INT NOT NULL,
	-- 06_调拨数量
	tsQuantity INT NOT NULL,
	-- 07_审核数量
	checkQuantity INT NOT NULL,
	-- 08_实际发货数量
	actualShipments INT NOT NULL,
	-- 09_已签收数量
	signedQuantity INT NOT NULL,
	-- 10_单位
	unit VARCHAR (10) NOT NULL,
	-- 11 单位体积
	unitVolume DOUBLE NOT NULL,
	-- 12 备货订单编号_外键，与StockOrderT中的stockOrderID关联
	stockOrderID VARCHAR (30) NOT NULL,
	-- 13 产品单价
	price DOUBLE NOT NULL,
	PRIMARY KEY (productID),
	UNIQUE KEY (serialNum),
	FOREIGN KEY (stockOrderID) REFERENCES StockOrderT (stockOrderID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 调拨信息表（TransferInfoTable）
CREATE TABLE TransferInfoT (
	-- 01_调拨单号_主键
	tsID VARCHAR (30) NOT NULL,
	-- 02_调拨类型，工厂调拨，普通搬仓，电商备货调拨
	tsType VARCHAR (6) NOT NULL,
	-- 03_创建者_外键，与OAT中的userName关联
	creator NVARCHAR (20) DEFAULT NULL,
	-- 04_调出仓库编号_外键，与WarehouseInfoT中的wID关联
	calloutwID VARCHAR (10) NOT NULL,
	-- 05_调入仓库编号_外键，与WarehouseInfoT中的wID关联
	callinwID VARCHAR (10) NOT NULL,
	-- 06_发运方式，空运，陆运，海运
	transMode VARCHAR (4) NOT NULL,
	-- 07_备货订单编号_外键，与StockOrderT中的stockOrderID关联
	stockOrderID VARCHAR (30),
	-- 08_经销商编号_外键，与AgencyInfoT中的agencyID关联
	agencyID VARCHAR (20) NOT NULL,
	-- 09_调拨总量，通过产品信息表获取计算得出
	tsVolumeDose INT NOT NULL,
	-- 10_调拨总体积，通过产品信息表获取计算得出
	tsTotalVolume DOUBLE NOT NULL,
	-- 11_创建日期，在生成调拨单时由JAVA后端直接获取当前系统时间
	createDate DATETIME NOT NULL,
	-- 12_产品编码_外键，绑定产品信息
	productID VARCHAR(30) NOT NULL,
	-- 13_备注
	remark nvarchar (256) DEFAULT NULL,
	PRIMARY KEY (tsID),
	FOREIGN KEY (creator) REFERENCES OAT (userName),
	FOREIGN KEY (calloutwID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (callinwID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (stockOrderID) REFERENCES StockOrderT (stockOrderID),
	FOREIGN KEY (agencyID) REFERENCES AgencyInfoT (agencyID),
	FOREIGN KEY (productID) REFERENCES ProductInfoT (productID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 经销商-客户合作表（Agency_CustomerRelationshipTable）
CREATE TABLE ACRT (
	-- 01_合作编号_主键
	acrID INT NOT NULL,
	-- 02_经销商编号_外键，与AgencyInfoT中的agencyID关联，用于反显经销商名称
	agencyID NVARCHAR (50) NOT NULL,
	-- 03_经销商区域
	agencyArea NVARCHAR (50) NOT NULL,
	-- 04_客户编
	customerID VARCHAR (50) NOT NULL,
	-- 05_客户名称
	customerName NVARCHAR (50) NOT NULL,
	PRIMARY KEY (acrID),
	FOREIGN KEY (agencyID) REFERENCES AgencyInfoT (agencyID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 订单信息表（OrderInfoTable）
CREATE TABLE OrderInfoT (
	-- 01_订单编号_主键
	orderNumber VARCHAR (16) NOT NULL,
	-- 02_订单状态，0为已确认收货、1为未确认收货、2为已发货、3为未发货
	orderStatus INT NOT NULL,
	-- 03_销售订单来源单号
	sourceNumber VARCHAR (16) NOT NULL,
	-- 04_经销商名称
	agencyName NVARCHAR (50) NOT NULL,
	-- 05_所属网店（即会员）
	memberName NVARCHAR (50) NOT NULL,
	-- 06_订单日期
	orderDate DATETIME NOT NULL,
	-- 07_买家昵称
	buyerNickname NVARCHAR (25) NOT NULL,
	-- 08_快递公司
	expressCompany NVARCHAR (25) NOT NULL,
	-- 09_快递单号_唯一
	expressNumber VARCHAR (15) NOT NULL,
	-- 10_发货仓名称
	deliveringWarehouse NVARCHAR (20) NOT NULL,
	
	PRIMARY KEY (orderNumber)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 订单详情表（OrderDetailTable）
CREATE TABLE OrderDetailT (
	-- 01_自增长编号
	id INT (10) unsigned NOT NULL AUTO_INCREMENT,
	-- 02_订单编号_外键
	orderNumber VARCHAR (16) NOT NULL,
	-- 03_商品编号_外键
	productNumber VARCHAR (30) NOT NULL,
	-- 04_商品名称
	productName NVARCHAR (50) NOT NULL,
	-- 05_商品购买数量
	purchaseQuantity INT NOT NULL,
	-- 06_商品单价
	productPrice DOUBLE NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (orderNumber) REFERENCES OrderInfoT (orderNumber),
	FOREIGN KEY (productNumber) REFERENCES ProductInfoT (productID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 订单配送表（OrderShippingTable）
CREATE TABLE OrderShippingT (
	-- 01_订单编号_主键
	orderNumber VARCHAR (16) NOT NULL,
	-- 02_收货地址-省
	receiverProvince NVARCHAR (15) NOT NULL,
	-- 03_收货地址-市
	receiverCity NVARCHAR (15) NOT NULL,
	-- 04_收货地址-区/县
	receiverArea NVARCHAR (15) NOT NULL,
	-- 05_详细收货地址/街道
	receiverStreet NVARCHAR (50) NOT NULL,
	-- 06_邮编
	receiveZip VARCHAR (6) NOT NULL,
	-- 07_收货人姓名
	receiverName NVARCHAR (15) NOT NULL,
	-- 08_收货人手机
	receiverMobile VARCHAR (11) NOT NULL,

	PRIMARY KEY (orderNumber)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 退货信息表（ReturnOrderInfoTable）
CREATE TABLE ReturnOrderInfoT (
	-- 01_退货订单编号_主键
	returnOrderNumber VARCHAR (16) NOT NULL,
	-- 02_退货订单提交日期
	returnOrderDate DATETIME DEFAULT NULL,
	-- 03_原销售订单编号_外键，与OrderInfoT中的orderNumber关联
	saleOrderNumber VARCHAR (16) NOT NULL,
	-- 04_退货订单状态
	returnOrderStatu INT NOT NULL,
	-- 05_退货方式，默认客户发货
	returnType NVARCHAR (10) DEFAULT '客户发货',
	-- 06_退货原因
	returnReason NVARCHAR (20) DEFAULT NULL,
	-- 07_快递公司
	expressCompany NVARCHAR (25) DEFAULT NULL,
	-- 08_快递单号
	expressNumber VARCHAR (15) DEFAULT NULL,
	-- 09_客户备注
	customerRemark NVARCHAR (100) DEFAULT NULL,
	-- 10_原发货仓库名称
	oWarehouseName NVARCHAR (20) DEFAULT NULL,
	-- 11_退回仓库名称
	returnedWarehouseName NVARCHAR (20) DEFAULT NULL,
	-- 12_经销商账号名称
	agencyName NVARCHAR (50) DEFAULT NULL,
	-- 13_会员名称
	memberName NVARCHAR (50) DEFAULT NULL,
	-- 14_应恢复货款金额
	returnedPayment DOUBLE,
	
	PRIMARY KEY (returnOrderNumber),
	FOREIGN KEY (saleOrderNumber) REFERENCES OrderInfoT (orderNumber)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 退货订单详情表（ReturnOrderDetailTable）
CREATE TABLE ReturnOrderDetailT (
	-- 01_自增长编号_主键
	id INT (10) unsigned NOT NULL AUTO_INCREMENT,
	-- 02_退货订单编号_外键
	returnOrderNumber VARCHAR (16) NOT NULL,
	-- 03_退货产品编号_外键
	returnProductNumber VARCHAR (30) DEFAULT NULL,
	-- 04_退货产品名称
	returnProductName NVARCHAR (50) DEFAULT NULL,
	-- 05_退回数量
	returnProductAmount INT DEFAULT NULL,
	-- 06_退货金额
	refundAmount DOUBLE DEFAULT '0',
	
	PRIMARY KEY (id),
	FOREIGN KEY (returnOrderNumber) REFERENCES ReturnOrderInfoT (returnOrderNumber),
	FOREIGN KEY (returnProductNumber) REFERENCES ProductInfoT (productID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 退货订单审核表（ReturnOrderAuditTable）
CREATE TABLE ReturnOrderAuditT (
	-- 01_退货订单编号_主键
	returnOrderNumber VARCHAR (16) NOT NULL,
	-- 02_审核人名称
	auditorName NVARCHAR (20) DEFAULT NULL,
	-- 03_审核结算人名称
	settlementAuditName NVARCHAR (20) DEFAULT NULL,
	-- 04_审核时间
	auditDate DATETIME DEFAULT NULL,
	-- 05_审核结算时间
	settlementAuditDate DATETIME DEFAULT NULL,
	-- 06_驳回原因
	rejectedReason NVARCHAR (30) DEFAULT NULL,
	
	PRIMARY KEY (returnOrderNumber)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 仓库库存表 (WRepertoryTable)
CREATE TABLE WRepertoryT (
	-- 1 库存编号 主键
	wrID VARCHAR (10) NOT NULL,
	-- 2 仓库编号 外键
	wID VARCHAR (10) NOT NULL,
	-- 3 产品编号 外键
	productID VARCHAR (30) NOT NULL,
	-- 4 仓库库存 
	repertory INT NOT NULL,
	PRIMARY KEY (wrID),
	FOREIGN KEY (wID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (productID) REFERENCES ProductInfoT (productID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 以下为测试数据
-- 运营角色
INSERT INTO ORT
VALUES
	(
		'admin',	-- 01_角色名称_主键
		'OR0',		-- 02_角色编号_唯一，格式ORxxx，OR代表运营角色，xxx为具体编号
		'管理员',	-- 03_角色描述，默认为空
		1,			-- 04_角色状态，0为禁用，1为启用，默认启用
		0			-- 05_权限等级，0为最高权限，数字越大，权限越低
	);
	
INSERT INTO ORT
VALUES
	(
		'test', 
		'OR1', 
		'测试', 
		0, 
		1
	);
	
INSERT INTO ORT
VALUES
	(
		'generalmanager', 
		'OR2', 
		'总经理', 
		1, 
		2
	);
	
INSERT INTO ORT
VALUES
	(
		'productmanager', 
		'OR3', 
		'产品经理', 
		1, 
		3
	);
	
INSERT INTO ORT
VALUES
	(
		'trainee', 
		'OR4', 
		'实习生', 
		1, 
		4
	);
	
-- 运营账号
INSERT INTO OAT
VALUES(
	'zhangsan',				-- 01_用户名_主键
	'OA0',					-- 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
	'zhangsan',				-- 03_密码
	'张三',					-- 04_真实姓名
	'张三有限公司',			-- 05_所属机构
	'张三流水线',			-- 06_所属产品线
	'管理员',				-- 07_角色名称，可以同时是多个角色，以字段拼接实现
	'M',					-- 08_性别，M为男性，F为女性
	'18967891230',			-- 09_手机号码，11位为手机号码，不考虑国家代码
	'zhangsan@gmail.com',	-- 10_电子邮箱
	1						-- 11_账号状态，0为禁用，1为启用，默认启用
);
INSERT INTO OAT
VALUES(
	'lisi',
	'OA0011',
	'lisi',
	'李四',
	'广东万家乐厨房科技具有限公司',
	'运营部',
	'总经理',
	'M',
	'17723443687',
	'lisi@gmail.com',
	1
);
INSERT INTO OAT
VALUES(
	'wanghongsheng',
	'OA0205',
	'wanghongsheng',
	'王洪生',
	'广东万家乐燃气具有限公司',
	'运营部',
	'产品经理',
	'M',
	'13455773280',
	'wanghongsheng@gmail.com',
	1
);
INSERT INTO OAT
VALUES(
	'yihangliuli',
	'OA0300',
	'yihangliuli',
	'一行琉璃',
	'广东万家乐有限公司',
	'运营部',
	'实习生',
	'F',
	'15625763679',
	'yihangliuli@gmail.com',
	1
);
	
-- 日志
INSERT INTO LogT
VALUES
	(
		0,		-- 01_日志编号_主键
		'ADD',	-- 02_操作类型，增加ADD，删除DEL，编辑EDT
		'OR0',	-- 03_操作对象，必须是ORxxx或者OAxxx，逻辑上来说是外键
		NOW(),	-- 04_操作时间，操作时由JAVA后端获取当前时间
		'增加：名称：admin，编号：OR0，描述：管理员，状态：1，权限：0'	-- 05_操作内容，采用字段拼接，具体参考测试数据
	);
	
INSERT INTO LogT
VALUES
	(
		1,
		'ADD',
		'OR1',
		NOW(),
		'增加：名称：test，编号：OR1，描述：测试，状态：0，权限：1'
	);

INSERT INTO LogT
VALUES
	(
		2,
		'ADD',
		'OA0',
		NOW(),
		'增加：名称：zhangsan，编号：OA0，密码：zhangsan，真实姓名：张三，机构：张三有限公司，产品线：张三流水线，角色：产品经理，性别：M，电话：18967891230，邮箱：zhangsan@gmail.com，状态：1'
	);

-- 抬头表（BuyerT）
INSERT INTO BuyerT
VALUES('热水事业部', '广东万家乐燃气具有限公司');
INSERT INTO BuyerT
VALUES('厨电事业部', '广东万家乐厨房科技具有限公司');
INSERT INTO BuyerT
VALUES('热能事业部', '广东万家乐热能科技有限公司');
INSERT INTO BuyerT
VALUES('空气事业部', '广东万家乐空气能科技有限公司');

-- 仓库信息表（WarehouseInfoT）：000,00,0前3位：城市；4-5：分区；6：0表示终端仓，1表示展柜仓
INSERT INTO WarehouseInfoT
VALUES('CD100000', '成都', '成都终端仓');
INSERT INTO WarehouseInfoT
VALUES('CD100111', '成都', '成都双流展柜仓');
INSERT INTO WarehouseInfoT
VALUES('NJ999000', '南京', '南京终端仓');
INSERT INTO WarehouseInfoT
VALUES('BJ1000', '北京', '北京终端仓');

-- 备货订单表（StockOrderT） PG 0，1，2==>暂且表示大中小订单
INSERT INTO StockOrderT
VALUES('PG000011');
INSERT INTO StockOrderT
VALUES('PG120050');
INSERT INTO StockOrderT
VALUES('PG200105');
INSERT INTO StockOrderT
VALUES('PG200311');


-- 经销商信息表（AgencyInfoT）
INSERT INTO AgencyInfoT
VALUES('1000001211', '成都诚信贸易公司', '成都市双流区学府路111号', '程心桉', 18836994222, '510100197404251111');
INSERT INTO AgencyInfoT
VALUES('9992227788', '金枝玉叶贸易公司', '南京市凤凰街道梧桐树329号', '贡铭晟', 18955662899, '320104195811017523');
INSERT INTO AgencyInfoT
VALUES('0013451010', '四方来朝贸易公司', '北京市朝阳区牛街225号', '姜甄', 15266669977, '110106198301313939');
INSERT INTO AgencyInfoT
VALUES('1009996789', '郎特姆公司', '腐都盛铭街建设路996号', 'Tom', 13277825786, '360361199005154582');
INSERT INTO AgencyInfoT
VALUES('9990001000', '万家乐南京分公司', '南京玄武厚德街128号', '张申', 18944453366, '510100198503053457');

-- 产品信息表（ProductInfoT）
INSERT INTO ProductInfoT
VALUES(
	10001,				-- 01_序号_唯一
	'WY9280010012211',	-- 02_产品编码_主键
	'卫浴挂件套装',		-- 03_产品名称
	'617',				-- 04_品类
	20000,				-- 05_调入仓库库存
	2300,				-- 06_调拨数量
	1600,				-- 07_审核数量
	1000,				-- 08_实际发货数量
	750,				-- 09_已签收数量
	'件',				-- 10_单位
	0.5,				-- 11_单位体积
	'PG200105',			-- 12_备货订单编号_外键，与StockOrderT中的stockOrderID关联
	800					-- 13_金额
);
INSERT INTO ProductInfoT
VALUES(
	20001,
	'C00205001508',
	'嵌入式燃气灶',
	'产品-燃气灶具',
	200000,
	50000,
	35000,
	22000,
	180000,
	'台',
	0.5,
	'PG120050',
	1000
);
INSERT INTO ProductInfoT
VALUES(
	20003,
	'XY505001293193',
	'吸油烟机',
	'产品-吸油烟机',
	3000000,
	100000,
	80000,
	60000,
	40000,
	'台',
	0.5,
	'PG000011',
	1500
);
INSERT INTO ProductInfoT
VALUES(
	40004,
	'展柜折扣',
	'展柜折扣',
	'602',
	3000,
	1000,
	800,
	450,
	378,
	'件',
	0.5,
	'PG200311',
	500
);

-- 调拨信息表（TransferInfoT）CDNJ: 成都到南京的单子,BJ：北京的单子，后面类比,001000参照上面仓库信息表区域
INSERT INTO TransferInfoT
VALUES(
	'BJ202016131213',	-- 01_调拨单号_主键
	'普通搬仓',			-- 02_调拨类型，工厂调拨，普通搬仓，电商备货调拨
	'lisi',				-- 03_创建者_外键，与OAT中的userName关联
	'BJ1000',			-- 04_调出仓库编号_外键，与WarehouseInfoT中的wID关联
	'BJ1000',			-- 05_调入仓库编号_外键，与WarehouseInfoT中的wID关联
	'陆运',				-- 06_发运方式，空运，陆运，海运
	NULL,				-- 07_备货订单编号_外键，与StockOrderT中的stockOrderID关联
	'0013451010',		-- 08_经销商编号_外键，与AgencyInfoT中的agencyID关联
	2300,				-- 09_调拨总量，通过产品信息表获取计算得出
	1150,				-- 10_调拨总体积，通过产品信息表获取计算得出
	NOW(),				-- 11_创建日期，在生成调拨单时由JAVA后端直接获取当前系统时间
	'WY9280010012211',	-- 12_产品主键
	'加急'				-- 13_备注
);
INSERT INTO TransferInfoT
VALUES(
	'CDNJ201812031030',
	'工厂调拨',
	'yihangliuli',
	'CD100000',
	'NJ999000',
	'陆运',
	NULL,
	'9990001000',
	100000,
	50000,
	NOW(),
	'XY505001293193',
	'加急'
);
INSERT INTO TransferInfoT
VALUES(
	'FD201911231415',
	'电商备货调拨',
	'wanghongsheng',
	'CD100111',
	'CD100111',
	'空运',
	'PG200311',
	'1009996789',
	1000,
	500,
	NOW(),
	'展柜折扣',
	'加急'
);
INSERT INTO TransferInfoT
VALUES(
	'CD20207062311',
	'普通搬仓',
	'lisi',
	'CD100000',
	'CD100000',
	'陆运',
	NULL,
	'1000001211',
	50000,
	25000,
	NOW(),
	'C00205001508',
	'加急'
);

-- 仓库库存表 (WRepertoryTable)
INSERT INTO WRepertoryT
VALUES(
	'WCD0001',			-- 1 库存编号 主键
	'CD100000',			-- 2 仓库编号 外键
	'WY9280010012211',	-- 3 产品编号 外键
	120000				-- 4 仓库库存 
);
INSERT INTO WRepertoryT
VALUES(
	'WCD0002',
	'CD100000',
	'C00205001508',
	100000
);
INSERT INTO WRepertoryT
VALUES(
	'WCD0003',
	'CD100000',
	'XY505001293193',
	120000
);
INSERT INTO WRepertoryT
VALUES(
	'WBJ0001',			-- 1 库存编号 主键
	'BJ1000',			-- 2 仓库编号 外键
	'WY9280010012211',	-- 3 产品编号 外键
	120000				-- 4 仓库库存 
);
INSERT INTO WRepertoryT
VALUES(
	'WBJ0002',
	'BJ1000',
	'C00205001508',
	100000
);
INSERT INTO WRepertoryT
VALUES(
	'WBJ0003',
	'BJ1000',
	'XY505001293193',
	120000
);
INSERT INTO WRepertoryT
VALUES(
	'WNJ0001',			-- 1 库存编号 主键
	'NJ999000',			-- 2 仓库编号 外键
	'WY9280010012211',	-- 3 产品编号 外键
	120000				-- 4 仓库库存 
);
INSERT INTO WRepertoryT
VALUES(
	'WNJ0002',
	'NJ999000',
	'C00205001508',
	100000
);
INSERT INTO WRepertoryT
VALUES(
	'WNJ0003',
	'NJ999000',
	'XY505001293193',
	120000
);
INSERT INTO WRepertoryT
VALUES(
	'WCD-Z0001',			-- 1 库存编号 主键
	'CD100111',				-- 2 仓库编号 外键
	'展柜折扣',				-- 3 产品编号 外键
	80000					-- 4 仓库库存 
);
-- 添加订单信息（OrderInfoTable、OrderDetailTable、OrderShippingTable）
-- 订单1
INSERT INTO OrderInfoT
VALUES(
	'SIW2019030700060',	    -- 01_销售订单号_主键
	0,			            -- 02_订单状态_已确认收货
	'1223987832262580',	    -- 03_销售订单来源单号
	'金枝玉叶贸易公司',	    -- 04_经销商名称
	'爱慕旗舰店',		    -- 05_所属网店（即会员）
	'2020-07-02 15:22:22',	-- 06_订单日期
	'葬、爱',				-- 07_买家昵称
	'顺丰',		            -- 08_快递公司
	'827364017325',		    -- 09_快递单号
	'成都终端仓'			-- 10_发货仓名称
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'SIW2019030700060',	  -- 02_订单编号_外键
	'C00205001508',	      -- 03_商品编号_外键
	'嵌入式燃气灶',	      -- 04_商品名称
	5,		              -- 05_商品购买数量
	1000	              -- 06_商品单价
);
INSERT INTO OrderShippingT
VALUES(
	'SIW2019030700060',	    -- 01_订单编号_主键
	'四川省',			    -- 02_收货地址_省
	'成都市',	            -- 03_收货地址_市
	'金牛区',	            -- 04_收货地址_区
	'建设路105号',		    -- 05_详细收货地址/街道
	'610000',	            -- 06_邮编
	'赵狂一',				-- 07_收货人
	'13276589017'		    -- 08_收货人手机
);
-- 订单2
INSERT INTO OrderInfoT
VALUES(
	'KHF2020090700211',	    -- 01_销售订单号_主键
	1,			            -- 02_订单状态_未确认收货
	'1227657832109580',	    -- 03_销售订单来源单号
	'郎特姆公司',	        -- 04_经销商名称
	'友家旗舰店',		    -- 05_所属网店（即会员）
	'2020-01-02 11:12:22',	-- 06_订单日期
	'忘了、爱',				-- 07_买家昵称
	'韵达',		            -- 08_快递公司
	'153324010925',		    -- 09_快递单号
	'南京终端仓'			-- 10_发货仓名称
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'KHF2020090700211',	  -- 02_订单编号_外键
	'WY9280010012211',	  -- 03_商品编号_外键
	'卫浴挂件套装',	      -- 04_商品名称
	2,		              -- 05_商品购买数量
	800	                  -- 06_商品单价
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'KHF2020090700211',	  -- 02_订单编号_外键
	'XY505001293193',	  -- 03_商品编号_外键
	'吸油烟机',	          -- 04_商品名称
	1,		              -- 05_商品购买数量
	1500	              -- 06_商品单价
);
INSERT INTO OrderShippingT
VALUES(
	'KHF2020090700211',	    -- 01_订单编号_主键
	'广东省',			    -- 02_收货地址_省
	'佛山市',	            -- 03_收货地址_市
	'南海区',	            -- 04_收货地址_区
	'四川东路光明屯小区三栋五单元15号',	-- 05_详细收货地址/街道
	'344100',	            -- 06_邮编
	'肖苞北',				-- 07_收货人
	'18976542101'		    -- 08_收货人手机
);
-- 订单3
INSERT INTO OrderInfoT
VALUES(
	'JBK2020112900211',	    -- 01_销售订单号_主键
	2,			            -- 02_订单状态_已发货
	'9752457209101186',	    -- 03_销售订单来源单号
	'郎特姆公司',	        -- 04_经销商名称
	'莱曼旗舰店',		    -- 05_所属网店（即会员）
	'2020-11-28 21:38:12',	-- 06_订单日期
	'罪、恶⭐网管',			-- 07_买家昵称
	'百世',		            -- 08_快递公司
	'771094010912',		    -- 09_快递单号
	'北京终端仓'			-- 10_发货仓名称
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'JBK2020112900211',	  -- 02_订单编号_外键
	'XY505001293193',	  -- 03_商品编号_外键
	'吸油烟机',	          -- 04_商品名称
	3,		              -- 05_商品购买数量
	1500	              -- 06_商品单价
);
INSERT INTO OrderShippingT
VALUES(
	'JBK2020112900211',	    -- 01_订单编号_主键
	'云南省',			    -- 02_收货地址_省
	'昭通市',	            -- 03_收货地址_市
	'顺义区',	            -- 04_收货地址_区
	'临港御府2单元3楼302',	-- 05_详细收货地址/街道
	'611200',	            -- 06_邮编
	'范思乐',				-- 07_收货人
	'17435672298'		    -- 08_收货人手机
);
-- 订单4
INSERT INTO OrderInfoT
VALUES(
	'GNN2020122113221',	    -- 01_销售订单号_主键
	3,			            -- 02_订单状态_未发货
	'8362897209118376',	    -- 03_销售订单来源单号
	'成都诚信贸易公司',	    -- 04_经销商名称
	'茗信工厂店',		    -- 05_所属网店（即会员）
	'2020-12-18 12:18:59',	-- 06_订单日期
	'独孤、殇',			-- 07_买家昵称
	'京东',		            -- 08_快递公司
	'JD1026310912',		    -- 09_快递单号
	'成都终端仓'			-- 10_发货仓名称
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'GNN2020122113221',	  -- 02_订单编号_外键
	'WY9280010012211',	  -- 03_商品编号_外键
	'卫浴挂件套装',	      -- 04_商品名称
	2,		              -- 05_商品购买数量
	800	                  -- 06_商品单价
);
INSERT INTO OrderDetailT
VALUES(
	NULL,	              -- 01_自增长编号
	'GNN2020122113221',	  -- 02_订单编号_外键
	'C00205001508',	      -- 03_商品编号_外键
	'嵌入式燃气灶',	      -- 04_商品名称
	1,		              -- 05_商品购买数量
	1000	              -- 06_商品单价
);
INSERT INTO OrderShippingT
VALUES(
	'GNN2020122113221',	    -- 01_订单编号_主键
	'四川省',			    -- 02_收货地址_省
	'乐山市',	            -- 03_收货地址_市
	'信义区',	            -- 04_收货地址_区
	'临港街道215号',	    -- 05_详细收货地址/街道
	'637000',	            -- 06_邮编
	'鸠丽璞',				-- 07_收货人
	'13971126543'		    -- 08_收货人手机
);