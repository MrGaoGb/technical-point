package com.technical.facade.exception;

import com.technical.facade.constant.ServiceConstants;
import com.technical.facade.exception.enums.BaseCoreExceptionEnums;
import org.apache.commons.lang3.StringUtils;

/**
 * 核心的异常类
 * 
 * @author Warmsheep
 * 
 */
public class BaseCoreException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	public BaseCoreException(String message){
		this.code = ServiceConstants.RES_SYSTEM_ERROR_CODE;
		this.message = message;
	}
	
	public BaseCoreException(BaseCoreExceptionEnums ex){
		this.code = ex.getKey();
		this.message = ex.getValue();
	}
	
	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public String getMessage(){
		if(StringUtils.isNotEmpty(this.message)){
			return this.message;
		} else {
			return BaseCoreExceptionEnums.getByKey(this.code).getValue();
		}
	}
	
	
}
