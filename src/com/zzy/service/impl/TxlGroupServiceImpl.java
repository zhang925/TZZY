package com.zzy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.mail.TxlGroup;
import com.zzy.service.TxlGroupService;;

/**市区 接口的实现*/
@Transactional
@Service()
public class TxlGroupServiceImpl implements TxlGroupService{
	private BaseDao<TxlGroup> basedao;
	
	public BaseDao<TxlGroup> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<TxlGroup> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除TxlGroup*/
	public Integer delTxlGroupByID(Integer id) {
		Integer i=1;
		TxlGroup o = new TxlGroup();
		try {
			o=getTxlGroupModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取TxlGroup*/
	public TxlGroup getTxlGroupModel(Integer id) {
		String hql="from TxlGroup where id=?";
		Object []param=new Object[]{id};
		TxlGroup o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存TxlGroup*/
	public Integer saveTxlGroup(TxlGroup TxlGroup) {
		Integer i=1;
		try {
			basedao.save(TxlGroup);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新TxlGroup*/
	public Integer updateTxlGroup(TxlGroup TxlGroup) {
		Integer i=1;
		try {
			basedao.update(TxlGroup);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupTxlGroup(TxlGroup TxlGroup) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(TxlGroup);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<TxlGroup>*/
	public List<TxlGroup> getAllTxlGroup(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<TxlGroup>*/
	public List<TxlGroup> getTxlGroupPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getTxlGroupTotalPage(Integer num,Integer rows) {
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
