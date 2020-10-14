CREATE TABLE `DC_MTS_CRD` (
`oper_type_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型' ,
`reason_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改/删除原因' ,
`refno`  varchar(27) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务参号' ,
`cert_type_code`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人国家/地区' ,
`id_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码' ,
`person_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人姓名' ,
`acctno`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号' ,
`jy_ccy_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易货币币种' ,
`jy_amt`  double(27,0) NULL DEFAULT NULL COMMENT '交易货币金额' ,
`qs_amt_rmb`  double(27,0) NULL DEFAULT NULL COMMENT '交易货币折人民币金额' ,
`mcc_code`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MCC 码' ,
`card_type_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型' ,
`card_chnl_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡清算渠道' ,
`bank_code`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡行金融机构代码' ,
`branch_safecode`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡网点所在地外汇局代码' ,
`biz_deal_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '交易授权日期及时间' ,
`country_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易国家或地区' ,
`bank_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行内部流水号' ,
`card_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡组织单号' ,
`data_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='银行卡境外提现明细数据'
ROW_FORMAT=DYNAMIC
;
CREATE TABLE `DC_MTS_CRX` (
`oper_type_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型' ,
`reason_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改/删除原因' ,
`refno`  varchar(27) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务参号' ,
`cert_type_code`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人国家/地区' ,
`id_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码' ,
`person_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人姓名' ,
`acctno`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号' ,
`jy_ccy_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易货币币种' ,
`jy_amt`  double(27,0) NULL DEFAULT NULL COMMENT '交易货币金额' ,
`qs_amt_rmb`  double(27,0) NULL DEFAULT NULL COMMENT '交易货币折人民币金额' ,
`mcc_code`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MCC 码' ,
`card_type_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型' ,
`card_chnl_code`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡清算渠道' ,
`bank_code`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡行金融机构代码' ,
`branch_safecode`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡网点所在地外汇局代码' ,
`biz_deal_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '交易授权日期及时间' ,
`country_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易国家或地区' ,
`bank_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行内部流水号' ,
`card_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡组织单号' ,
`sh_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易商户名称' ,
`jy_chnl`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '交易商户类型' ,
`data_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='银行卡境外消费明细数据'
ROW_FORMAT=DYNAMIC
;



CREATE TABLE `MTS_CARD_EXP_INFO` (
`cert_type_code`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持卡人国家/地区' ,
`id_code`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证件号码' ,
`input_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入日期' ,
`data_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='银行卡境外违规交易记录'
ROW_FORMAT=DYNAMIC
;
CREATE TABLE `mts_ctrl_err_file` (
`batch_no`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '批次号' ,
`input_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入日期' ,
PRIMARY KEY (`batch_no`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='外汇局反馈错误控制文件信息'
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `mts_feed_back_field` (
`buss_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`err_field`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`err_field_cn`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`err_desc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`input_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`data_type`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`buss_no`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=DYNAMIC
;



