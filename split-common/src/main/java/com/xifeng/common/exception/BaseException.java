package com.xifeng.common.exception;

import org.apache.commons.lang.StringUtils;

/**
 * 异常基类
 * @author xiezbmf
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 8964125350368022571L;
	String errCode;
	String message;

	public String getErrCode() {
		return errCode;
	}

	public String getMessage() {
		return message;
	}

	BaseException() {
		super();
	}

	BaseException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}
	
	boolean isLegalErrCode(String startCode,int codeLength){
		if(StringUtils.isEmpty(errCode)||!errCode.startsWith(startCode)||errCode.length()!=codeLength){
			return false;
		}
		return true;
	}
}
