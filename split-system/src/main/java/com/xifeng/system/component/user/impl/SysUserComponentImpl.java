/**
 * Project Name:split-system <br>
 * File Name:SysUserComponentImpl.java <br>
 * Package Name:com.xifeng.system.component.user.impl <br>
 * @author xiezbmf
 * Date:2017年6月26日下午2:28:00 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.component.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.xifeng.common.exception.DataException;
import com.xifeng.common.util.AssertUtil;
import com.xifeng.system.component.user.SysUserComponent;
import com.xifeng.system.dao.user.SysUserDao;
import com.xifeng.system.model.user.SysUser;

/**
 * ClassName: SysUserComponentImpl <br>
 * Description: 系统用户数据操作组件接口实现类
 * @author xiezbmf
 * @Date 2017年6月26日下午2:28:00 <br>
 * @version
 * @since JDK 1.6
 */
@Component("sysUserComponent")
public class SysUserComponentImpl implements SysUserComponent {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public void saveSysUserInfo(SysUser sysUser) throws DataException {
		AssertUtil.notNull(sysUser, "保存sysUser不能为空");
		int saveNum = sysUserDao.saveSysUser(sysUser);
		if(saveNum!=1){
			throw new DataException();
		}
	}

	@Override
	public SysUser querySysUserByAccount(String userAccount) {
		AssertUtil.hasText(userAccount);
		Map<String,String> params = new HashMap<String,String>();
		params.put("userAccount", userAccount);
		List<SysUser> list = sysUserDao.querySysUserByParams(params);
		if(!CollectionUtils.isEmpty(list)&&list.size()==1){
			return list.get(0);
		}
	    return null;
	}

	@Override
	public SysUser querySysUserById(String userId) {
		AssertUtil.hasText(userId);
		Map<String,String> params = new HashMap<String,String>();
		params.put("userId", userId);
		List<SysUser> list = sysUserDao.querySysUserByParams(params);
		if(!CollectionUtils.isEmpty(list)&&list.size()==1){
			return list.get(0);
		}
	    return null;
	}

	@Override
	public SysUser querySysUserByAuth(String userAccount, String accPwd) {
		
		AssertUtil.hasText(userAccount);
		AssertUtil.hasText(accPwd);
		Map<String,String> params = new HashMap<String,String>();
		params.put("userAccount", userAccount);
		params.put("accPwd", accPwd);
		List<SysUser> list = sysUserDao.querySysUserByParams(params);
		if(!CollectionUtils.isEmpty(list)&&list.size()==1){
			return list.get(0);
		}
	    return null;
	}

}

	