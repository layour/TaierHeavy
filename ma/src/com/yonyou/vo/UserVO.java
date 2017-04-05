package com.yonyou.vo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class UserVO extends SuperVO {

	private String name;
	private String deptname;
	private String pk_user;
	private String password;
	private UFDateTime ts;
	
	public static final String NAME = "name";
	public static final String PK_USER = "pk_user";
	public static final String PASSWORD = "password";
	public static final String DEPTNAME = "deptname";
	public static final String TS = "ts";
	
	public static final String[] ATTRIBUTES = {"name","pk_user","password","deptname","ts"};
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "yyuap_user";
	}
	
	public static String getDefaultTableName() {
		return "yyuap_user";
	}
	
	@Override
	public String getPKFieldName() {
		// TODO 自动生成的方法存根
		return PK_USER;
	}
	
	@Override
	public String[] getAttributeNames() {
		// TODO 自动生成的方法存根
		return ATTRIBUTES;
	}
	
}
