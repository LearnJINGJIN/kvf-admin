package com.kalvin.kvf.modules.mts.constant;

/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */
public class CommonConstant {
	
	public static final String AQC_ENCODING = "GB18030";
	public final static String DES_KEY = "rO0ABXNyAB5jb20uc3VuLmNyeXB0by5wcm92aWRlci5ERVNLZXlrNJw12hVomAIAAVsAA2tleXQAAltCeHB1cgACW0Ks8xf4BghU4AIAAHhwAAAACB87KtWbwuoc";//和运维平台的密钥保持一致
	/**
	 * 数据采集数据源名称
	 */
	public static final String DATA_AQC_DATASOURCE = "MTS_DATASOURCE";
	
	public static final String TOKEN_FILE_NAME = "Token.lock";

	/**
	 * 发送xml文件主路径-key
	 */
	public static final String MAIN_XMLFILE_PATH_KEY = "back_main_path";
	
	/**
	 * 金融机构代码-key
	 */
	public static final String BANK_CODE_KEY = "bank_code";
	
	public static final String XML_EXT = ".xml";
	/**
	 * 反馈错误xml文件主路径-key
	 */
	public static final String BACK_ERR_FILE_PATH_KEY = "back_err_file_path";
	/**
	 * 反馈错误xml历史文件主路径-key
	 */
	public static final String BACK_ERR_FILE_HIS_PATH_KEY = "back_err_file_his_path";
	/**
	 * 接受外汇局违规交易文件主路径-key
	 */
	public static final String CARD_EXP_FILE_PATH_KEY = "card_exp_file_path";
	/**
	 * 外汇局违规交易数据文件扩展名-key
	 */
	public static final String CARD_EXP_FILE_EXT_KEY = "card_exp_file_ext";
	/**
	 * 外汇局违规交易数据文件类型起点位置-key
	 */
	public static final String CARD_EXP_FILE_TYPE_BEG_KEY = "card_exp_file_type_beg";
	/**
	 * 外汇局违规交易数据文件类型终点位置-key
	 */
	public static final String CARD_EXP_FILE_TYPE_END_KEY = "card_exp_file_type_end";
	/**
	 * 外汇局反馈文件名后三位
	 */
	public static final String WHJ_SEND_ERR_FILE_LAST_THREE="whj_send_err_file_last_three";
	/**
	 * 同一批次最大序号
	 */
	public static final String BATCH_NO_MAX_SEQ_KEY="batch_no_max_seq";
	/**
	 * 提现应用类型
	 */
	public static final String CRD_APP_TYPE = "CRD";
	/**
	 * 消费应用类型
	 */
	public static final String CRX_APP_TYPE = "CRX";
	/**
	 * 提现控制文件类型
	 */
	public static final String CRD_CONTROL_FILE_TYPE = "TT";
	/**
	 * 提现不区分卡类型文件类型
	 */
	public static final String CRD_OTHER_FILE_TYPE = "TA";
	/**
	 * 提现借记文件类型
	 */
	public static final String CRD_DBIT_FILE_TYPE = "TB";
	/**
	 * 提现贷记文件类型
	 */
	public static final String CRD_CRDT_FILE_TYPE = "TC";
	/**
	 * 消费控制文件类型
	 */
	public static final String CRX_CONTROL_FILE_TYPE = "TT";
	/**
	 * 消费不区分卡类型文件类型
	 */
	public static final String CRX_OTHER_FILE_TYPE = "XA";
	/**
	 * 消费借记文件类型
	 */
	public static final String CRX_DBIT_FILE_TYPE = "XB";
	/**
	 * 消费贷记文件类型
	 */
	public static final String CRX_CRDT_FILE_TYPE = "XC";
	/**
	 * 金融机构输入类型
	 */
	public static final String BANK_INPUT_TYPE = "IN";
	/**
	 * 外汇局接crd收文件路径-key
	 */
	public static final String WHJ_ACCEPT_CRD_FILE_KEY = "whj_accept_crd_path";

	/**
	 * 外汇局接crx收文件路径-key
	 */
	public static final String WHJ_ACCEPT_CRX_FILE_KEY = "whj_accept_crx_path";
	/**
	 * 外汇局发送CRD回执错误文件路径-key
	 */
	public static final String WHJ_SEND_ERR_CRD_FILE_KEY = "whj_send_err_crd_path";
	/**
	 * 外汇局发送CRX回执错误文件路径-key
	 */
	public static final String WHJ_SEND_ERR_CRX_FILE_KEY = "whj_send_err_crx_path";	
	
	/**
	 * 外汇局发送CRD错误文件路径
	 */
	public static final String WHJ_SEND_ERR_TTCRD_KEY="whj_send_err_ttcrd_path";
	/**
	 * 外汇局发送CRX错误文件路径
	 */
	public static final String WHJ_SEND_ERR_TTCRX_KEY="whj_send_err_ttcrx_path";
	
	/**
	 * 外汇局发送违规交易文件路径-key
	 */
	public static final String WHJ_SEND_EXP_FILE_KEY = "whj_send_exp_path";
	/**
	 * 检查token文件最大等待次数
	 */
	public static final String TOKEN_RETRY_TIME_KEY = "token_retry_time";
	/**
	 * 
	 */
	public static final String CARD_EXP_FILE_END_KEY = "card_exp_file_end";
	/**
	 * 
	 */
	public static final String BACK_FILE_ENCODE_KEY = "back_file_encode"; 
	/**
	 * 
	 */
	public static final String TRAN_EXP_FILE_ENCODE_KEY = "tran_exp_file_encode"; 
	/**
	 *
	 */
	public static final String SJ_INSERT_CR_PATH = "sj_insert_cr_path";
	/**
	 *
	 */
	public static final String REDIS_OUT_TIME = "redis_out_time";

}
