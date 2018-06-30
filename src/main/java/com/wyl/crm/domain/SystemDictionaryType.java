package com.wyl.crm.domain;

import java.io.Serializable;

/**
 * 数据字典
 */
public class SystemDictionaryType implements Serializable {
	// 定义2个常量：系统初始化的时候
	public static final String CUSTOMER_SOURCE = "customerSource";// 客户来源

	private Long id;
	private String name;// domain的名称
	private String sn;// 唯一,不能修改
	private String intro;

	public SystemDictionaryType() {

	}

	public SystemDictionaryType(Long id) {
		super();
		this.id = id;
	}

	public SystemDictionaryType(String sn) {
		super();
		this.sn = sn;
	}

	public SystemDictionaryType(String sn, String name) {
		super();
		this.sn = sn;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	// EasyUI兼容
	public String getText() {
		return name;
	}

	@Override
	public String toString() {
		return "SystemDictionaryType [id=" + id + ", sn=" + sn + ", name=" + name + ", intro=" + intro + "]";
	}

}
