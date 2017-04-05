package com.yonyou.vo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class CadVO extends SuperVO {

	private String name;
	private String pk_cad;
	private String code;
	private String ref_pk_user;
	private String imgsrc;
	private UFDateTime updatetime;
	private UFDateTime ts;
	
	public static final String NAME = "name";
	public static final String PK_CAD = "pk_cad";
	public static final String REF_PK_USER = "ref_pk_user";
	public static final String CODE = "code";
	public static final String IMG_SRC = "imgsrc";
	public static final String UPDATETIME = "updatetime";
	public static final String TS = "ts";
	public static final String[] ATTRIBUTES = {"name","pk_cad","ref_pk_user","code","imgsrc","updatetime","ts"};
	@Override
	public String getTableName() {
		return "yyuap_cad";
	}
	@Override
	public String getPKFieldName() {
		// TODO 自动生成的方法存根
		return PK_CAD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPk_cad() {
		return pk_cad;
	}
	public void setPk_cad(String pk_cad) {
		this.pk_cad = pk_cad;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRef_pk_user() {
		return ref_pk_user;
	}
	public void setRef_pk_user(String pk_user) {
		this.ref_pk_user = pk_user;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public UFDateTime getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(UFDateTime updatetime) {
		this.updatetime = updatetime;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
	
}
