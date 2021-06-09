package com.technical.facade.constant;

public class ServiceConstants {

    public static final String HYSTRIX_RES_CODE = "01";                //熔断
    public static final String EXCEPTION_RES_CODE = "02";

    public static final String HYSTRIX_RES_MESSAGE = "服务器通讯异常!";
    public static final String EXCEPTION_RES_MESSAGE = "系统异常!";

    public static final String RES_SUCCESS_CODE = "00";
    public static final String RES_SUCCESS_MESSAGE = "操作成功";
	
    public static final String RES_SYSTEM_ERROR_CODE = "0102";        //系统异常
    public static final String RES_lOGIN_USER_FAIL = "0103";        //用户未登录
    public static final String RES_BUSINESS_CHECK_FAIL = "0104";    //业务校验

    public static final String FIELD_VALID_ERROR = "0104";            //字段验证错误
    public static final String ALREADY_PROCESSED = "0105";    //文件已处理

    public static final String RES_EXECUTE_DB_ERROR_CODE = "0106";//表示执行数据库异常

    public static final String RES_lOGIN_FAIL_MESSAGE = "用户未登录!";

    public static final String QRCODE_POSP_WITHDRAW = "QRCODE";



    public static final String TASK_RES_SUCCESS_CODE = "000000";
    public static final String TASK_RES_SUCCESS_MESSAGE = "SUCCESS";
    
    /**
     *  00	操作成功
	 *	20	业务异常
	 *	30	系统异常
	 *	40	校验异常
	 *	50	网络异常
     */
    public static final String RES_BUSI_FAIL_CODE = "20";
    public static final String RES_BUSI_FAIL_MESSAGE = "业务异常";
    
    public static final String RES_SYS_FAIL_CODE = "30";
    public static final String RES_SYS_FAIL_MESSAGE = "系统异常";
    
    public static final String RES_CHECK_FAIL_CODE = "40";
    public static final String RES_CHECK_FAIL_MESSAGE = "校验异常";
    
    public static final String RES_NET_FAIL_CODE = "50";
    public static final String RES_NET_FAIL_MESSAGE = "网络异常";
}
