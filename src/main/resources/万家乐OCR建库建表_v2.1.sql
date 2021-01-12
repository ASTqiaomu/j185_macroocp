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
	unitVolume VARCHAR (20) NOT NULL,
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
		'admin',
		'OR0',
		'管理员',
		1,
		0
	);

INSERT INTO ORT
VALUES
	('test', 'OR1', '测试', 0, 1);

-- 运营账号
INSERT INTO OAT
VALUES
	(
		'zhangsan',
		'OA0',
		'zhangsan',
		'张三',
		'张三有限公司',
		'张三流水线',
		'产品经理',
		'M',
		'18967891230',
		'zhangsan@gmail.com',
		1
	);

-- 日志
INSERT INTO LogT
VALUES
	(
		0,
		'ADD',
		'OR0',
		NOW(),
		'增加：名称：admin，编号：OR0，描述：管理员，状态：1，权限：0'
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

