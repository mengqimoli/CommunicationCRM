package com.wyl.crm.domain;

import java.io.Serializable;

/**
 * 数据字典明细
 */
public class SystemDictionaryItem implements Serializable {

	private Long id;
	private String name; //
	private Integer sequence;
	private Integer status = 1;
	private String intro;

	private SystemDictionaryType type;// 数据字典类型

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SystemDictionaryType getType() {
		return type;
	}

	public void setType(SystemDictionaryType type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public SystemDictionaryItem(Long id, String name, SystemDictionaryType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public SystemDictionaryItem(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SystemDictionaryItem(Long id) {
		super();
		this.id = id;
	}

	public SystemDictionaryItem() {
		super();
	}

	public SystemDictionaryItem(SystemDictionaryType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	// EasyUI兼容
	public String getText() {
		return name;
	}

	@Override
	public String toString() {
		return "SystemDictionaryItem [id=" + id + ", name=" + name + ", sequence=" + sequence + ", status=" + status
				+ ", intro=" + intro + ", type=" + type + "]";
	}

}
