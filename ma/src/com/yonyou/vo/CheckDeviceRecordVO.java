package com.yonyou.vo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;


public class CheckDeviceRecordVO extends SuperVO{

	private String ref_pk_device;
	private String ref_pk_user;
	private String pk_record;
	private UFDateTime checktime;
	private int isfinished;
	private UFDateTime ts;
	private String longitude;
	private String latitude;
	
	private String deptname;

	public static final String PK_RECORD = "pk_record";
	public static final String REF_PK_DEVICE = "ref_pk_device";
	public static final String REF_PK_USER = "ref_pk_user";
	public static final String CHECKTIME = "checktime";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String ISFINISHED = "isfinished";
	public static final String TS = "ts";
	
	
	public static final String[] ATTRIBUTES = {"pk_record","ref_pk_device","ref_pk_user","checktime","longitude","latitude","ts"};
	
	
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public int getIsfinished() {
		return isfinished;
	}

	public void setIsfinished(int isfinished) {
		this.isfinished = isfinished;
	}

	public String getRef_pk_device() {
		return ref_pk_device;
	}

	public void setRef_pk_device(String ref_pk_device) {
		this.ref_pk_device = ref_pk_device;
	}

	public String getRef_pk_user() {
		return ref_pk_user;
	}

	public void setRef_pk_user(String ref_pk_user) {
		this.ref_pk_user = ref_pk_user;
	}

	public String getPk_record() {
		return pk_record;
	}

	public void setPk_record(String pk_record) {
		this.pk_record = pk_record;
	}

	public UFDateTime getChecktime() {
		return checktime;
	}

	public void setChecktime(UFDateTime checktime) {
		this.checktime = checktime;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
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

	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "yyuap_check_record";
	}
	
	public static String getDefaultTableName() {
		return "yyuap_check_record";
	}
	
	@Override
	public String[] getAttributeNames() {
		return ATTRIBUTES;
	}
	
	@Override
	public String getPKFieldName() {
		return PK_RECORD;
	}
}
