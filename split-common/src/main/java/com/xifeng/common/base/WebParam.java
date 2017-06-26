/**
 * Project Name:split-common <br>
 * File Name:WebParam.java <br>
 * Package Name:com.xifeng.common.base <br>
 * @author xiezbmf
 * Date:2017年6月22日下午3:04:58 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.base;

import com.xifeng.common.auth.UserInfo;

/**
 * ClassName: WebParam <br>
 * Description: web页面访问参数类
 * @author xiezbmf
 * @Date 2017年6月22日下午3:04:58 <br>
 * @version
 * @since JDK 1.6
 */
public class WebParam {
	
	/**
	 * func:请求功能.
	 */
	private String func;
	/**
	 * tid:单个请求标识.
	 */
	private String tid;
	/**
	 * sid:会话标识.
	 */
	private String sid;
	/**
	 * user:认证用户信息.
	 */
	private UserInfo user;
	
	/**
	 * pageNo:分页，请求页.
	 */
	private Integer pageNo;
	/**
	 * pageSize:分页大小.
	 */
	private Integer pageSize;
	/**
	 * key:
	 */
	private String key;
	
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}

	