package com.chippo.LoginWeb.model;

import java.util.List;

public class PropertyProps {

	private String pid;
	private String nameProps;
	private List<PropertyPropsValues> propertyPropsValues;
	
	public PropertyProps() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyProps(String pid, String nameProps, List<PropertyPropsValues> propertyPropsValues) {
		super();
		this.pid = pid;
		this.nameProps = nameProps;
		this.propertyPropsValues = propertyPropsValues;
	}

	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getNameProps() {
		return nameProps;
	}
	public void setNameProps(String nameProps) {
		this.nameProps = nameProps;
	}

	public List<PropertyPropsValues> getPropertyPropsValues() {
		return propertyPropsValues;
	}

	public void setPropertyPropsValues(List<PropertyPropsValues> propertyPropsValues) {
		this.propertyPropsValues = propertyPropsValues;
	}
	
	
}
