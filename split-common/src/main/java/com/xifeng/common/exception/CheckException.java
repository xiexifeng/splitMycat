package com.xifeng.common.exception;


public class CheckException extends BaseException{

	private static final long serialVersionUID = 7305413359496028798L;
	
	public CheckException(){
		super();
		this.errCode = "30000";
		this.message = "业务操作异常";
	}
	public CheckException(String errCode,String errMsg) {
		super(errCode,errMsg);
		String startCode = "3";
		int codeLength = 5;
		if(!isLegalErrCode(startCode,codeLength)){
			throw new IllegalArgumentException("错误的errCode，应以"+startCode+"开头，长度为"+codeLength);
		}
	}
}
