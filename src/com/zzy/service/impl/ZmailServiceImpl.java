package com.zzy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.mail.Zmail;
import com.zzy.service.ZmailService;;

/**市区 接口的实现*/
@Transactional
@Service()
public class ZmailServiceImpl implements ZmailService{
	private BaseDao<Zmail> basedao;
	
	public BaseDao<Zmail> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Zmail> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Zmail*/
	public Integer delZmailByID(Integer id) {
		Integer i=1;
		Zmail o = new Zmail();
		try {
			o=getZmailModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Zmail*/
	public Zmail getZmailModel(Integer id) {
		String hql="from Zmail where id=?";
		Object []param=new Object[]{id};
		Zmail o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Zmail*/
	public Integer saveZmail(Zmail Zmail) {
		Integer i=1;
		try {
			basedao.save(Zmail);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Zmail*/
	public Integer updateZmail(Zmail Zmail) {
		Integer i=1;
		try {
			basedao.update(Zmail);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupZmail(Zmail Zmail) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Zmail);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Zmail>*/
	public List<Zmail> getAllZmail(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Zmail>*/
	public List<Zmail> getZmailPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getZmailTotalPage(Integer num,Integer rows) {
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
