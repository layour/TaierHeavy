package com.yonyou.util;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import nc.bs.dao.BaseDAO;

public class BaseDaoFactory {

	private static BaseDaoFactory instance = null;
	private Lock lockOfBaseDao = null;
	private SoftReference<BaseDAO> softrefBaseDao;
	
	public BaseDaoFactory()
	{
		lockOfBaseDao = new ReentrantLock();
		softrefBaseDao = new SoftReference<BaseDAO>(new BaseDAO());
	}
	public static BaseDaoFactory shareInstance() {
		
		if(instance==null){
			synchronized (BaseDaoFactory.class) {
				
				if(instance==null){
					instance = new BaseDaoFactory();
				}
			}
		}
		return instance;
	}
	public BaseDAO getBaseDao() {
		BaseDAO dao = softrefBaseDao.get();
		if(dao==null && lockOfBaseDao.tryLock())
		{
			try {
				if(dao==null)
				{
					dao = new BaseDAO();
					softrefBaseDao = new SoftReference<BaseDAO>(dao);
				}
			} catch (Exception e) {
					e.printStackTrace();
			}finally{
					lockOfBaseDao.unlock();
			}
		}
		return dao;
	}
	
	
}
