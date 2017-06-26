package com.xifeng.sequence.model;

import org.apache.ibatis.type.Alias;

/**
 * @author xiezbmf
 *
 */
@Alias("mycatSequence")
public class MycatSequence {

	/**
	 * 序列号名
	 */
	private String name;
	/**
	 * 当前序列号
	 */
	private Long currentValue;
	/**
	 * 增长步数
	 */
	private Integer increment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

}
