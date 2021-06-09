package com.technical.facade.exception.enums;

import org.apache.commons.lang3.StringUtils;

public enum BaseCoreExceptionEnums {
	
	/*数据库级别*/
	EXECUTE_DATABASE_FAIL("00000001", "数据库执行异常"),
	INSERT_FAILED("00000002","插入失败"),
	UPDATE_FAILED("00000003","更新失败"),
	DELETE_FAILED("00000004","删除失败"),
	SELECT_FAILED("00000005","查询失败"),

	/*用户提示级别*/
	DB_OBJECT_NOT_NULL("00100001", "数据库记录对象不能为空"),
	PARAMS_NOT_NULL("00100002","参数不能为空"),
	USER_ID_NOT_NULL("00100003","用户ID不能为空"),
	ENTITY_OBJECT_NOT_NULL("00100004","实体对象不能为空"),
	ENTITY_OBJECT_ID_NOT_NULL("00100005","实体对象ID不能为空"),
	USER_NOT_EQUALS("00100006","非法操作（用户不一致）"),
	HSM_FAILURE("00100007","加解密失败!"),
	;
	
	private String value;
	private String key;
	
	private BaseCoreExceptionEnums(String index, String name)
	{
		this.key = index;
		this.value = name;
	}
	
	/**
	 * 通过下标获得枚举
	 * 
	 * @param key
	 */
	public static BaseCoreExceptionEnums getByKey(String key) {
		if (null == key) {
			return null;
		}
		for (BaseCoreExceptionEnums at : BaseCoreExceptionEnums.values()) {
			if (at.key.equals(key)) {
				return at;
			}
		}
		return null;
	}
	
	/**
	 * 通过名称获得枚举
	 * 
	 * @param value
	 */
	public static BaseCoreExceptionEnums getByValue(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		for (BaseCoreExceptionEnums at : BaseCoreExceptionEnums.values()) {
			if (at.value.equals(value)) {
				return at;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.key + ":" + this.value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
