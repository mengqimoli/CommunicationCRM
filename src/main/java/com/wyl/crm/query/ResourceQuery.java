package com.wyl.crm.query;

public class ResourceQuery extends BaseQuery {

	private String controller;

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	@Override
	public String toString() {
		return "ResourceQuery [controller=" + controller + "]";
	}

}
