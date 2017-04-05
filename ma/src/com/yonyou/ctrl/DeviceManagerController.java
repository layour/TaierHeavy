package com.yonyou.ctrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yonyou.uap.um.controller.AbstractUMController;
import com.yonyou.util.BaseDaoFactory;
import com.yonyou.util.DataVOService;
import com.yonyou.vo.CadVO;
import com.yonyou.vo.CheckDeviceRecordVO;
import com.yonyou.vo.DeviceVO;
import com.yonyou.vo.UserVO;

import nc.bs.dao.BaseDAO;

public class DeviceManagerController  extends  AbstractUMController {

	DataVOService dataVOService = new DataVOService();
	@Override
	public String load(String arg0) throws Exception {
		dataVOService.initTableVO();
		return "{code:1000}";
	}
	
	public String  getCads(String msg)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			Collection<CadVO> collection = dataVOService.fetchAll(CadVO.class);
			jsonObject.put("code", "1000");
			if(collection!=null )
			{
				Gson gson = new Gson();
				String json = gson.toJson(collection,new TypeToken<Collection<CadVO>>(){}.getType());
				jsonObject.put("datalist", json);
			}else{
				jsonObject.put("datalist", new ArrayList<CadVO>());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonObject.put("code", "-1000");
			} catch (JSONException e1) {
			}
		}
		String retString =  jsonObject.toString();
		System.out.println(retString);
		return retString;
		
	}
	
	public String  getNeedCheckDevices(String msg)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			Collection<CheckDeviceRecordVO> collection = dataVOService.fetchAll(CheckDeviceRecordVO.class);
			jsonObject.put("code", "1000");
			if(collection!=null )
			{
				List<CheckDeviceRecordVO> devicelist = new ArrayList<CheckDeviceRecordVO>();
				devicelist.addAll(collection);
				int len = devicelist.size();
				
				for (int i = 0; i < len; i++) 
				{
					String pk = devicelist.get(i).getRef_pk_user();
					UserVO user = dataVOService.fetch(UserVO.class, pk);
					devicelist.get(i).setDeptname(user.getDeptname());
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(devicelist,new TypeToken<List<CheckDeviceRecordVO>>(){}.getType());
				jsonObject.put("datalist", json);
			}else{
				jsonObject.put("datalist", new ArrayList<CadVO>());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonObject.put("code", "-1000");
			} catch (JSONException e1) {
			}
		}
		String retString =  jsonObject.toString();
		System.out.println(retString);
		return retString;
	}
	
	@Override
	public String save(String arg0) throws Exception {
		
		try {
			JSONObject jsonObject = new JSONObject(arg0);
			String jsonlist = jsonObject.optString("datalist","[]");
			if(jsonlist!=null && jsonlist.length()>0)
			{
				List<CheckDeviceRecordVO> list = new Gson().fromJson(jsonlist, new TypeToken<List<CheckDeviceRecordVO>>(){}.getType());
				dataVOService.update(CheckDeviceRecordVO.class,list);
			}
			return "{code:1000}";
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "{code:-1000}";
	}
	
	private String __emptyMethod(String arg0)
	{
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "3000");
		result.put("msg", "no implemention !");
		return new JSONObject(result).toString();
	}
	
}