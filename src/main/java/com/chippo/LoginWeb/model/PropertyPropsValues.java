package com.chippo.LoginWeb.model;

public class PropertyPropsValues {

	private String vid;
	private String nameValueString;
	private String images;
	
	public PropertyPropsValues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyPropsValues(String vid, String nameValueString) {
		super();
		this.vid = vid;
		this.nameValueString = nameValueString;
	}
	
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getNameValueString() {
		return nameValueString;
	}
	public void setNameValueString(String nameValueString) {
		this.nameValueString = nameValueString;
	}
	
	
}
