package com.zzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.SysConfigure;
import com.zzy.service.SysConfigureService;;

/**市区 接口的实现*/
@Transactional
@Service()
public class SysConfigureServiceImpl implements SysConfigureService{

	@Autowired
	private BaseDao<SysConfigure> basedao;
	

	/**根据实体ID删除SysConfigure*/
	public Integer delSysConfigureByID(Integer id) {
		Integer i=1;
		SysConfigure o = new SysConfigure();
		try {
			o=getSysConfigureModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取SysConfigure*/
	public SysConfigure getSysConfigureModel(Integer id) {
		String hql="from SysConfigure where id=?";
		Object []param=new Object[]{id};
		SysConfigure o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存SysConfigure*/
	public Integer saveSysConfigure(SysConfigure sysConfigure) {
		Integer i=1;
		try {
			basedao.save(sysConfigure);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新SysConfigure*/
	public Integer updateSysConfigure(SysConfigure sysConfigure) {
		Integer i=1;
		try {
			basedao.update(sysConfigure);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupSysConfigure(SysConfigure sysConfigure) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(sysConfigure);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<SysConfigure>*/
	public List<SysConfigure> getAllSysConfigure(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<SysConfigure>*/
	public List<SysConfigure> getSysConfigurePage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getSysConfigureTotalPage(Integer num,Integer rows) {
		int totalpage=1;
		if(num >= rows){
			if(num % rows == 0){
				totalpage = num / rows;
			}else{
				totalpage = (num / rows)+1;
			}
		}
		return totalpage;
	}

}
