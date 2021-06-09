package com.technical.facade.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础异常类
 * @author gaogba
 *
 */
@Getter
@Setter
public class BaseException extends Exception {

	protected String code;

	protected String message;
}
