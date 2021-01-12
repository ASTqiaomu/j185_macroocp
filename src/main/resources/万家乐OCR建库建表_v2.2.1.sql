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
	-- 03_送货地址
	deliverAd NVARCHAR (50) NOT NULL,
	-- 04_收货人
	consignee NVARCHAR (20) NOT NULL,
	-- 05_收货人电话
	consigneeTel VARCHAR (11) NOT NULL,
	-- 06_收货人身份证
	consigneeID VARCHAR (18) NOT NULL,
	PRIMARY KEY (agencyID)
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
	stockOrderID VARCHAR (30) NOT NULL,
	-- 08_经销商编号_外键，与AgencyInfoT中的agencyID关联
	agencyID VARCHAR (20) NOT NULL,
	-- 09_调拨总量，通过产品信息表获取计算得出
	tsVolumeDose INT NOT NULL,
	-- 10_调拨总体积，通过产品信息表获取计算得出
	tsTotalVolume DOUBLE NOT NULL,
	-- 11_创建日期，在生成调拨单时由JAVA后端直接获取当前系统时间
	createDate DATETIME NOT NULL,
	-- 12_备注
	remark nvarchar (256) DEFAULT NULL,
	PRIMARY KEY (tsID),
	FOREIGN KEY (creator) REFERENCES OAT (userName),
	FOREIGN KEY (calloutwID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (callinwID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (stockOrderID) REFERENCES StockOrderT (stockOrderID),
	FOREIGN KEY (agencyID) REFERENCES AgencyInfoT (agencyID)
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
	PRIMARY KEY (productID),
	UNIQUE KEY (serialNum),
	FOREIGN KEY (stockOrderID) REFERENCES StockOrderT (stockOrderID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 经销商-客户合作表（Agency_CustomerRelationshipTable）
CREATE TABLE ACRT (
	-- 01_合作编号_主键
	acrID INT NOT NULL,
	-- 02_经销商编号_外键，与AgencyInfoT中的agencyID关联，用于反显经销商名称
	agencyID NVARCHAR (50) NOT NULL,
	-- 03_经销商区域
	agencyArea NVARCHAR (50) NOT NULL,
	-- 04_客户编号
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
	-- 03_收货人
	consigneeInfo NVARCHAR (50) NOT NULL,
	-- 04_收货人电话
	consigneeTel VARCHAR (11) NOT NULL,
	-- 05_收货人地址
	consigneeAd NVARCHAR (50) NOT NULL,
	-- 06_收货人邮编
	consigneePostCode VARCHAR (6) NOT NULL,
	-- 07_产品编码_外键，与ProductInfoT中的productID关联，用于反显产品名称
	productID VARCHAR (30) NOT NULL,
	-- 08_快递公司
	expressCompany NVARCHAR (50) NOT NULL,
	-- 08_快递单号_唯一
	expressNumber VARCHAR (15) NOT NULL,
	-- 09_原发货仓库编号_外键，与WarehouseInfoT中的wID关联，用于反显原发货仓库名称
	ODWID VARCHAR (10) NOT NULL,
	-- 10_购买数量
	purchaseQty INT NOT NULL,
	-- 11_开票价
	ticketPrice DOUBLE DEFAULT NULL,
	-- 12_卖家名称
	sellerName NVARCHAR (30) NOT NULL,
	PRIMARY KEY (orderNumber),
	FOREIGN KEY (productID) REFERENCES ProductInfoT (productID),
	FOREIGN KEY (ODWID) REFERENCES WarehouseInfoT (wID)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 退货信息表（ReturnOrderInfoTable）
CREATE TABLE ReturnOrderInfoT (
	-- 01_序号_唯一
	serialNum INT NOT NULL,
	-- 02_合作编号_外键，与ACRT中的acrID关联，用于反显客户名称、编号、区域
	acrID INT NOT NULL,
	-- 03_原销售订单编号_外键，与OrderInfoT中的orderNumber关联，用于反显订单状态、收货人信息、产品信息、购买数量
	oOrderNumber VARCHAR (16) NOT NULL,
	-- 04_原销售订单来源编号_外键，写死，来源同上
	oOrderSourceNumber VARCHAR (16) NOT NULL,
	-- 05_退换货订单编号_主键，与OrderInfoT中的orderNumber关联，用于反显原发货仓库名称、买家备注
	orderNumber VARCHAR (16) NOT NULL,
	-- 06_退货方式，默认客户发货
	returnType NVARCHAR (20) DEFAULT '客户发货',
	-- 07_订单类型
	orderType NVARCHAR (10) DEFAULT NULL,
	-- 08_退回仓库编号_外键，与WarehouseInfoT中的wID关联，用于反显退回仓库名称
	RWID VARCHAR (10) NOT NULL,
	-- 09_退款原因
	refundReason NVARCHAR (50) DEFAULT NULL,
	-- 10_其他原因
	refundReason2 NVARCHAR (50) DEFAULT NULL,
	-- 11_退货金额
	returnMoney DOUBLE DEFAULT 0,
	-- 12_退换货提交日期，在操作时由JAVA后端直接获取当前系统时间
	returnDate DATETIME NOT NULL,
	-- 13_审核状态，0待审核，1审核通过，2审核未通过，3待复核，4复核通过，5复核未通过
	verifyStatus INT NOT NULL DEFAULT 0,
	-- 14_审核人_外键，与OAT中的userName关联
	reviewer NVARCHAR (20) DEFAULT NULL,
	-- 15_审核日期，在操作时由JAVA后端直接获取当前系统时间
	reviewerDate DATETIME DEFAULT NULL,
	-- 16_复核人_外键，与OAT中的userName关联
	reviewer2 NVARCHAR (20) DEFAULT NULL,
	-- 17_复核日期，在操作时由JAVA后端直接获取当前系统时间
	reviewerDate2 DATETIME DEFAULT NULL,
	PRIMARY KEY (orderNumber),
	FOREIGN KEY (acrID) REFERENCES ACRT (acrID),
	FOREIGN KEY (oOrderNumber) REFERENCES OrderInfoT (orderNumber),
	FOREIGN KEY (oOrderSourceNumber) REFERENCES OrderInfoT (orderNumber),
	FOREIGN KEY (RWID) REFERENCES WarehouseInfoT (wID),
	FOREIGN KEY (reviewer) REFERENCES OAT (userName),
	FOREIGN KEY (reviewer2) REFERENCES OAT (userName)
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
	
-- 运营账号
INSERT INTO OAT
VALUES(
	'zhangsan',				-- 01_用户名_主键
	'OA0',					-- 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
	'zhangsan',				-- 03_密码
	'张三',					-- 04_真实姓名
	'张三有限公司',			-- 05_所属机构
	'张三流水线',			-- 06_所属产品线
	'产品经理',				-- 07_角色名称，可以同时是多个角色，以字段拼接实现
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
	'产品运营经理',
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
	'产品运营经理',
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
	'运营总经理',
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
VALUES(100000, '成都', '成都终端仓');
INSERT INTO WarehouseInfoT
VALUES(100040, '成都', '成都青羊分仓');
INSERT INTO WarehouseInfoT
VALUES(100111, '成都', '成都双流展柜仓');
INSERT INTO WarehouseInfoT
VALUES(100051, '成都', '成都金牛展柜仓');
INSERT INTO WarehouseInfoT
VALUES(999000, '南京', '南京终端仓');
INSERT INTO WarehouseInfoT
VALUES(999900, '南京', '南京玄武分仓');
INSERT INTO WarehouseInfoT
VALUES(1000, '北京', '北京终端仓');
INSERT INTO WarehouseInfoT
VALUES(1001, '北京', '北京展柜仓');
INSERT INTO WarehouseInfoT
VALUES(666000, '重庆', '重庆终端仓');
INSERT INTO WarehouseInfoT
VALUES(666101, '重庆', '重庆江北展柜仓');

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
VALUES('1000001211', '成都市双流区学府路111号', '成都诚信贸易公司', '程心桉', 18836994222, '510100197404251111');
INSERT INTO AgencyInfoT
VALUES('9992227788', '南京市凤凰街道梧桐树329号', '金枝玉叶贸易公司', '贡铭晟', 18955662899, '320104195811017523');
INSERT INTO AgencyInfoT
VALUES('0013451010', '北京市朝阳区牛街225号', '四方来朝贸易公司', '姜甄', 15266669977, '110106198301313939');
INSERT INTO AgencyInfoT
VALUES('1009996789', '腐都盛铭街建设路996号', '郎特姆公司', 'Tom', 13277825786, '360361199005154582');
INSERT INTO AgencyInfoT
VALUES('9990001000', '南京玄武厚德街128号', '万家乐南京分公司', '张申', 18944453366, '510100198503053457');

-- 调拨信息表（TransferInfoT）CDNJ: BJ：北京的单子，成都到南京的单子，后面类比,001000参照上面仓库信息表区域
INSERT INTO TransferInfoT
VALUES(
	'BJ202016131213',	-- 01_调拨单号_主键
	'普通搬仓',			-- 02_调拨类型，工厂调拨，普通搬仓，电商备货调拨
	'lisi',				-- 03_创建者_外键，与OAT中的userName关联
	'1000',				-- 04_调出仓库编号_外键，与WarehouseInfoT中的wID关联
	'1000',				-- 05_调入仓库编号_外键，与WarehouseInfoT中的wID关联
	'陆运',				-- 06_发运方式，空运，陆运，海运
	'PG200105',			-- 07_备货订单编号_外键，与StockOrderT中的stockOrderID关联
	'0013451010',		-- 08_经销商编号_外键，与AgencyInfoT中的agencyID关联
	2300,				-- 09_调拨总量，通过产品信息表获取计算得出
	1150,				-- 10_调拨总体积，通过产品信息表获取计算得出
	NOW(),				-- 11_创建日期，在生成调拨单时由JAVA后端直接获取当前系统时间
	'加急'				-- 12_备注
);
INSERT INTO TransferInfoT
VALUES(
	'CDNJ201812031030',
	'工厂调拨',
	'yihangliuli',
	'100000',
	'999000',
	'陆运',
	'PG000011',
	'9990001000',
	100000,
	50000,
	NOW(),
	'加急'
);
INSERT INTO TransferInfoT
VALUES(
	'FD201911231415',
	'电商备货调拨',
	'wanghongsheng',
	'100111',
	'100111',
	'空运',
	'PG200311',
	'1009996789',
	1000,
	500,
	NOW(),
	'加急'
);
INSERT INTO TransferInfoT
VALUES(
	'CD20207062311',
	'普通搬仓',
	'lisi',
	'100040',
	'100040',
	'陆运',
	'PG120050',
	'1000001211',
	50000,
	25000,
	NOW(),
	'加急'
);

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
	0.5,				-- 11 单位体积
	'PG200105'			-- 12 备货订单编号_外键，与StockOrderT中的stockOrderID关联
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
	'PG120050'
);INSERT INTO ProductInfoT
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
	'PG000011'
);INSERT INTO ProductInfoT
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
	'PG200311'
);
