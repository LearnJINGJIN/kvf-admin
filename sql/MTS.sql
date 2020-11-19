CREATE TABLE `dc_mts_crd` (
`oper_type_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型' ,
`reason_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改/删除原因' ,
`refno`  char(27) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务参号' ,
`cert_type_code`  char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人国家/地区' ,
`id_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码' ,
`person_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人姓名' ,
`acctno`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号' ,
`jy_ccy_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易货币币种' ,
`jy_amt`  decimal(27,2) NULL DEFAULT NULL COMMENT '交易货币金额' ,
`qs_amt_rmb`  decimal(27,2) NULL DEFAULT NULL COMMENT '交易货币折人民币金额' ,
`mcc_code`  char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MCC 码' ,
`card_type_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型' ,
`card_chnl_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡清算渠道' ,
`bank_code`  char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡行金融机构代码' ,
`branch_safecode`  char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡网点所在地外汇局代码' ,
`biz_deal_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '交易授权日期及时间' ,
`country_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易国家或地区' ,
`bank_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行内部流水号' ,
`card_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡组织单号' ,
`data_date`  char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='银行卡境外提现明细数据'
ROW_FORMAT=DYNAMIC
;
CREATE TABLE `dc_mts_crx` (
`oper_type_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型' ,
`reason_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改/删除原因' ,
`refno`  char(27) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务参号' ,
`cert_type_code`  char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人国家/地区' ,
`id_code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码' ,
`person_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人姓名' ,
`acctno`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号' ,
`jy_ccy_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易货币币种' ,
`jy_amt`  decimal(27,2) NULL DEFAULT NULL COMMENT '交易货币金额' ,
`qs_amt_rmb`  decimal(27,2) NULL DEFAULT NULL COMMENT '交易货币折人民币金额' ,
`mcc_code`  char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MCC 码' ,
`card_type_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡类型' ,
`card_chnl_code`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡清算渠道' ,
`bank_code`  char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡行金融机构代码' ,
`branch_safecode`  char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡网点所在地外汇局代码' ,
`biz_deal_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '交易授权日期及时间' ,
`country_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易国家或地区' ,
`bank_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行内部流水号' ,
`card_self_num`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡组织单号' ,
`sh_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易商户名称' ,
`jy_chnl`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '交易商户类型' ,
`data_date`  char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='银行卡境外消费明细数据'
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `mts_card_exp_info` (
`cert_type_code`  char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持卡人身份证件类型' ,
`pty_country_code`  char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持卡人国家/地区' ,
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
`buss_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务数据主键' ,
`err_field`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出错字段英文标识' ,
`err_field_cn`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出错字段中文标识' ,
`err_desc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出错原因' ,
`input_date`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入日期' ,
`data_type`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型' ,
PRIMARY KEY (`buss_no`),
INDEX `MTS_FEED_BACK_FIELD_IND` (`buss_no`) USING BTREE ,
INDEX `MTS_FEED_BACK_FIELD_IND1` (`input_date`) USING BTREE ,
INDEX `MTS_FEED_BACK_FIELD_IND2` (`input_date`, `data_type`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='外汇局反馈错误字段信息'
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `schedule_job_log` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`bean`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'bean名称' ,
`status`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '0-成功；1-失败' ,
`reason`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='定时任务表执行日志表'
AUTO_INCREMENT=19
ROW_FORMAT=DYNAMIC
;



