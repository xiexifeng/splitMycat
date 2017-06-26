/**
 * Project Name:split-common <br>
 * File Name:ServiceParam.java <br>
 * Package Name:com.xifeng.common.base <br>
 * @author xiezbmf
 * Date:2017年6月22日下午3:08:26 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.base;

import org.springframework.context.support.MessageSourceAccessor;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.xifeng.common.auth.UserInfo;

/**
 * ClassName: ServiceParam <br>
 * Description: service服务接口参数
 * 
 * @author xiezbmf
 * @Date 2017年6月22日下午3:08:26 <br>
 * @version
 * @since JDK 1.6
 */
public class ServiceParam {
	/**
	 * func:请求接口名.
	 */
	private String func;
	/**
	 * data:请求数据.
	 */
	private String data;
	/**
	 * tid:单次请求标识.
	 */
	private String tid;
	/**
	 * sid:会话标识.
	 */
	private String sid;
	/**
	 * user:用户认证信息.
	 */
	private UserInfo user;
	/**
	 * pageNo:分页编号.
	 */
	private Integer pageNo;
	/**
	 * pageSize:分页大小.
	 */
	private Integer pageSize;
	/**
	 * currentPage:当前页.
	 */
	private Integer currentPage;
	/**
	 * filename:上传文件名.
	 */
	private String filename;
	private SerializeWriter out;
	private JSONSerializer serializer;
	private MessageSourceAccessor messageSourceAccessor;

	public String getFunc() {

		return func;
	}

	public void setFunc(String func) {

		this.func = func;
	}

	public String getData() {

		return data;
	}

	public void setData(String data) {

		this.data = data;
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

	public Integer getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {

		this.currentPage = currentPage;
	}

	public String getFilename() {

		return filename;
	}

	public void setFilename(String filename) {

		this.filename = filename;
	}

	public SerializeWriter getOut() {

		return out;
	}

	public void setOut(SerializeWriter out) {

		this.out = out;
	}

	public JSONSerializer getSerializer() {

		return serializer;
	}

	public void setSerializer(JSONSerializer serializer) {

		this.serializer = serializer;
	}

	public MessageSourceAccessor getMessageSourceAccessor() {

		return messageSourceAccessor;
	}

	public void setMessageSourceAccessor(
			MessageSourceAccessor messageSourceAccessor) {

		this.messageSourceAccessor = messageSourceAccessor;
	}
}
