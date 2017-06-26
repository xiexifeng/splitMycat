/**
 * Project Name:split-sequence <br>
 * File Name:SequenceDetail.java <br>
 * Package Name:com.xifeng.sequence.model <br>
 * @author xiezbmf
 * Date:2017年6月15日下午5:00:35 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.sequence.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * ClassName: SequenceDetail <br>
 * Description: 序列号详细设置实体类
 * @author xiezbmf
 * @Date 2017年6月15日下午5:00:35 <br>
 * @version
 * @since JDK 1.6
 */
@Alias("sequenceDetail")
public class SequenceDetail {
	/**
	 * name:序列号名称.
	 */
	private String name;
	/**
	 * prefixNo:序列号前缀 可以用模板.
	 */
	private String prefixNo;
	/**
	 * seqLength:序列号长度.
	 */
	private Integer seqLength;
	/**
	 * seqDesc:序列号描述.
	 */
	private String seqDesc;
	private Date createTime;
	private Date updateTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefixNo() {
		return prefixNo;
	}
	public void setPrefixNo(String prefixNo) {
		this.prefixNo = prefixNo;
	}
	public Integer getSeqLength() {
		return seqLength;
	}
	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}
	public String getSeqDesc() {
		return seqDesc;
	}
	public void setSeqDesc(String seqDesc) {
		this.seqDesc = seqDesc;
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
}

	