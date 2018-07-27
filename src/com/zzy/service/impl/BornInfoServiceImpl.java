package com.zzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.BornInfo;
import com.zzy.service.BornInfoService;

@Transactional
@Service()
public class BornInfoServiceImpl implements BornInfoService{
	@Autowired
	private BaseDao<BornInfo> basedao;

	public int delBornInfo(BornInfo bornInfo) {
		int i = 1;
		try {
			basedao.delete(bornInfo);
		} catch (Exception e) {
			i=2;
			return i;
		}
		return i;
	}

	public List<BornInfo> getBornInfoList(String hql, Object[] param) {
		return basedao.find(hql, param);
	}

	public List<BornInfo> getBornInfoList(String hql, Object[] param,
			Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}

	public BornInfo getBornInfoModel(String hql, Object[] param) {
		return basedao.get(hql, param);
	}

	public BornInfo getBornInfoModelByID(String id) {
		return basedao.get("from BornInfo where id=?", new Object[]{id});
	}
	public int saveBornInfo(BornInfo bornInfo) {
		int i = 1;
		try {
			basedao.save(bornInfo);
		} catch (Exception e) {
			i=2;
			return i;
		}
		return i;
	}

	public int saveOrUpdateBornInfo(BornInfo bornInfo) {
		int i = 1;
		try {
			basedao.saveOrUpdate(bornInfo);
		} catch (Exception e) {
			i=2;
			return i;
		}
		return i;
	}

	public int updateBornInfo(BornInfo bornInfo) {
		int i = 1;
		try {
			basedao.update(bornInfo);
		} catch (Exception e) {
			i=2;
			return i;
		}
		return i;
	}
	
	/**
	 * 
	 */
	public int getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	
	
}
