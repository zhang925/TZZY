package com.zzy.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.mail.Txl;
import com.zzy.service.TxlService;;

/**市区 接口的实现*/
@Transactional
public class TxlServiceImpl implements TxlService{
	private BaseDao<Txl> basedao;
	
	public BaseDao<Txl> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Txl> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Txl*/
	public Integer delTxlByID(Integer id) {
		Integer i=1;
		Txl o = new Txl();
		try {
			o=getTxlModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Txl*/
	public Txl getTxlModel(Integer id) {
		String hql="from Txl where id=?";
		Object []param=new Object[]{id};
		Txl o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Txl*/
	public Integer saveTxl(Txl Txl) {
		Integer i=1;
		try {
			basedao.save(Txl);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Txl*/
	public Integer updateTxl(Txl Txl) {
		Integer i=1;
		try {
			basedao.update(Txl);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupTxl(Txl Txl) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Txl);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Txl>*/
	public List<Txl> getAllTxl(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Txl>*/
	public List<Txl> getTxlPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getTxlTotalPage(Integer num,Integer rows) {
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
