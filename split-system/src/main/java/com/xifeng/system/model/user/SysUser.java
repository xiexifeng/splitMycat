/**
 * Project Name:split-system <br>
 * File Name:SysUser.java <br>
 * Package Name:com.xifeng.system.model.user <br>
 * @author xiezbmf
 * Date:2017年6月26日下午2:28:52 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.model.user;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * ClassName: SysUser <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月26日下午2:28:52 <br>
 * @version
 * @since JDK 1.6
 */
@Alias("sysUser")
public class SysUser {

	/**
	 * userId:用户账户id.
	 */
	private String userId;
	/**
	 * userAccount:用户账号.
	 */
	private String userAccount;
	/**
	 * userName:用户名.
	 */
	private String userName;
	/**
	 * accPwd:账号密码.
	 */
	private String accPwd;
	/**
	 * isEnable:是否可用.
	 */
	private Boolean isEnable;
	/**
	 * telephone:手机号码.
	 */
	private String telephone;
	private Date createTime;
	private Date updateTime;
	private String remark;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccPwd() {
		return accPwd;
	}
	public void setAccPwd(String accPwd) {
		this.accPwd = accPwd;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}

	