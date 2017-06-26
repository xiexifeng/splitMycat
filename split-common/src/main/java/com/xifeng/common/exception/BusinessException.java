package com.xifeng.common.exception;

/**
 * 业务层操作异常
 * @author xiezbmf
 *
 */
public class BusinessException extends BaseException{
	private static final long serialVersionUID = -6331823123583400194L;
	public BusinessException(){
		super();
		this.errCode = "20000";
		this.message = "业务操作异常";
	}
	public BusinessException(String errCode,String errMsg){
		super(errCode,errMsg);
		String startCode = "2";
		int codeLength = 5;
		if(!isLegalErrCode(startCode,codeLength)){
			throw new IllegalArgumentException("错误的errCode，应以"+startCode+"开头，长度为"+codeLength);
		}
	}
}
