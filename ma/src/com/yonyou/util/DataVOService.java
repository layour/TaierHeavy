package com.yonyou.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yonyou.vo.CadVO;
import com.yonyou.vo.CheckDeviceRecordVO;
import com.yonyou.vo.DeviceVO;
import com.yonyou.vo.UserVO;
import com.yyuap.upush.common.utils.Constant.Device;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class DataVOService {

	public void initTableVO()
	{
			BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
		try {
			 Collection users = dao.retrieveAll(UserVO.class);
			 if(users==null || users.size()==0)
			 {
					initUserVO();
			 }
			 Collection cads = dao.retrieveAll(CadVO.class);
			 if(cads==null || cads.size()==0)
			 {
				 initCadVO();
			 }
			 Collection devices = dao.retrieveAll(DeviceVO.class);
			 if(devices==null || devices.size()==0)
			 {
				 initDeviceVO();
				 initDeviceRecordVO();
			 }
			 
			 
		} catch (DAOException e) {
			e.printStackTrace();
		}
			
	}
	private void initCadVO() {
		
		try {
			BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
			CadVO cad = null;
			Collection users = dao.retrieveAll(UserVO.class);
			List<UserVO> userArray = new ArrayList<UserVO>();
			userArray.addAll(users);
			for (int i = 0; i < 5; i++)
			{
				UserVO userVO = userArray.get(i%userArray.size());
				cad = new CadVO();
				cad.setName("2016款 1.5L 手动舒适型");
				cad.setImgsrc("http://car2.autoimg.cn/cardfs/product/g7/M13/E9/6A/t_autohomecar__wKgHzlZPAwGAAjLoAAaT2HxQ-Sw900.jpg");
				cad.setRef_pk_user(userVO.getPrimaryKey());
				cad.setCode("cad_pdm_0000"+i);
				cad.setUpdatetime(new UFDateTime());
				dao.insertVO(cad);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	private void initDeviceRecordVO(){
		
		BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
		CheckDeviceRecordVO checkDeviceRecordVO = null;
		
		try {
			Collection users = dao.retrieveAll(UserVO.class);
			Collection devices = dao.retrieveAll(DeviceVO.class);
			
			List<UserVO> userArray = new ArrayList<UserVO>();
			userArray.addAll(users);
			
			List<DeviceVO> deviceArray = new ArrayList<DeviceVO>();
			deviceArray.addAll(devices);
			
			for (int i = 0; i < 5; i++) {
				
				UserVO userVO = userArray.get(i%userArray.size());
				DeviceVO deviceVO = deviceArray.get(i%deviceArray.size());
				
				checkDeviceRecordVO = new CheckDeviceRecordVO();
				checkDeviceRecordVO.setRef_pk_user(userVO.getPrimaryKey());
				checkDeviceRecordVO.setRef_pk_device(deviceVO.getPrimaryKey());
				checkDeviceRecordVO.setChecktime(new UFDateTime());
				checkDeviceRecordVO.setLatitude(deviceVO.getLatitude());
				checkDeviceRecordVO.setLongitude(deviceVO.getLongitude());
				checkDeviceRecordVO.setIsfinished(0);
				
				dao.insertVO(checkDeviceRecordVO);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	private void initUserVO() {
		
		BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
		UserVO user = null;
		String[] Deptname = {"销售部","市场部","行政部","采购部","研发部"};
		String[] usernames = {"张三峰","艾李四","王小武","张全蛋","马化藤","贾聪明","王大伟"};
		
		try {
			for (int i = 0; i < usernames.length; i++) 
			{
				user = new UserVO();
				user.setDeptname(Deptname[i%Deptname.length]);
				user.setName(usernames[i]);
				user.setPassword("pass123456");
				dao.insertVO(user);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	private void initDeviceVO() {
		
		BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
		DeviceVO device  = null;
		String[] deviceNames = {"机床A","机床B","机床C","机床D","机床E","机床F","机床G","机床H","机床I","机床J"};
		String[] setLatitude = {"31.627762","31.647307","31.645124","31.646254","31.647061","31.646423","31.629023","31.629115","31.627931","31.628215"};
		String[] setLongitude = {"118.496971","118.493438","118.494193","118.4958","118.496474","118.494916","118.496807","118.497364","118.497687","118.498082"};
		
		try {
			Collection users = dao.retrieveAll(UserVO.class);
			
			List<UserVO> userArray = new ArrayList<UserVO>();
			userArray.addAll(users);
			
			for (int i = 0; i < deviceNames.length; i++)
			{
				device = new DeviceVO(); 
				device.setCode("iuap201704011020"+i);
				device.setName(deviceNames[i]);
				device.setCreationtime(new UFDateTime());
				device.setCreatorid(userArray.get(i%userArray.size()).getPrimaryKey());
				device.setLatitude(setLatitude[i]); //经度
				device.setLongitude(setLongitude[i]); //纬度
				dao.insertVO(device);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	public <T extends SuperVO> Collection<T> fetchAll(Class<T> t) throws DAOException
	{
		try {
			BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
			Collection<T> all = dao.retrieveAll(t);
			return  all;
		} catch (DAOException e) {
			throw e;
		}
	}
	
	public <T extends SuperVO> Collection<T> fetch(Class<T> t,Object[] pks) throws DAOException
	{
		try {
			BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
			if(pks==null || pks.length==0)
			{
				throw new DAOException("pk list is not allowd empty!");
			}
			Collection<T> list = new ArrayList<T>();
			for (int i = 0; i < pks.length; i++) {
			
				T tInstance = (T) dao.retrieveByPK(t, (String) pks[i]);
				list.add(tInstance);
			}
			return  list;
		} catch (DAOException e) {
			throw e;
		}
	}
	
	public <T extends SuperVO> T fetch(Class<T> t,String pk) throws DAOException
	{
		try {
			BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
			Collection<T> list = new ArrayList<T>();
			
			T tInstance = (T) dao.retrieveByPK(t, pk);
				list.add(tInstance);
			return tInstance;
		} catch (DAOException e) {
			throw e;
		}
	}
	
	public <T extends SuperVO> void update(Class<T> t, List<CheckDeviceRecordVO> list) throws DAOException
	{
		BaseDAO dao = BaseDaoFactory.shareInstance().getBaseDao();
		
		try {
			if(list!=null && list.size()>0)
			{
				dao.updateVOArray((CheckDeviceRecordVO[]) list.toArray(), new String[]{CheckDeviceRecordVO.CHECKTIME,CheckDeviceRecordVO.ISFINISHED});
			}else{
				throw new DAOException(" update VO list is empty!");
			}
		} catch (DAOException e) {
			throw e;
		}
		
	}
	
	
}
