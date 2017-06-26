package com.xifeng.common.exception;


/**
 * 持久层数据操作异常
 * @author xiezbmf
 *
 */
public class DataException extends BaseException{
	private static final long serialVersionUID = 4093014278554938973L;
	
	public DataException(){
		super();
		this.errCode = "10000";
		this.message = "数据操作异常";
	}
	public DataException(String errCode,String errMsg){
		super(errCode,errMsg);
		String startCode = "1";
		int codeLength = 5;
		if(!isLegalErrCode(startCode,codeLength)){
			throw new IllegalArgumentException("错误的errCode，应以"+startCode+"开头，长度为"+codeLength);
		}
	}

}
