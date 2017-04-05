package com.yonyou.vo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class DeviceVO extends SuperVO {

	private String pk_device;
	private String name;
	private String code;
	private String creatorid;
	private String longitude;
	private String latitude;
	private UFDateTime creationtime;
	private UFDateTime ts;
	
	public static final String PK_DEVICE = "pk_device";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String CREATORID = "creatorid";
	public static final String CREATIONTIME = "creationtime";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String TS = "ts";
	
	public static final String[] ATTRIBUTES = {"name","pk_device","code","creationtime","longitude","latitude","creatorid","ts"};
	
	
	@Override
	public String getTableName() {
		return "yyuap_device";
	}
	
	@Override
	public String getPKFieldName() {
		return PK_DEVICE;
	}
	
	@Override
	public String[] getAttributeNames() {
		// TODO 自动生成的方法存根
		return ATTRIBUTES;
	}
	
	
	public static String getDefaultTableName() {
		return "yyuap_device";
	}

	public String getPk_device() {
		return pk_device;
	}

	public void setPk_device(String pk_device) {
		this.pk_device = pk_device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public UFDateTime getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
}
