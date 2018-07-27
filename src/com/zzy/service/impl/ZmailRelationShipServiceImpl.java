package com.zzy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.mail.ZmailRelationShip;
import com.zzy.service.ZmailRelationShipService;;

/**市区 接口的实现*/
@Transactional
@Service()
public class ZmailRelationShipServiceImpl implements ZmailRelationShipService{
	private BaseDao<ZmailRelationShip> basedao;
	
	public BaseDao<ZmailRelationShip> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<ZmailRelationShip> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除ZmailRelationShip*/
	public Integer delZmailRelationShipByID(Integer id) {
		Integer i=1;
		ZmailRelationShip o = new ZmailRelationShip();
		try {
			o=getZmailRelationShipModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取ZmailRelationShip*/
	public ZmailRelationShip getZmailRelationShipModel(Integer id) {
		String hql="from ZmailRelationShip where id=?";
		Object []param=new Object[]{id};
		ZmailRelationShip o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存ZmailRelationShip*/
	public Integer saveZmailRelationShip(ZmailRelationShip ZmailRelationShip) {
		Integer i=1;
		try {
			basedao.save(ZmailRelationShip);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新ZmailRelationShip*/
	public Integer updateZmailRelationShip(ZmailRelationShip ZmailRelationShip) {
		Integer i=1;
		try {
			basedao.update(ZmailRelationShip);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupZmailRelationShip(ZmailRelationShip ZmailRelationShip) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(ZmailRelationShip);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<ZmailRelationShip>*/
	public List<ZmailRelationShip> getAllZmailRelationShip(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<ZmailRelationShip>*/
	public List<ZmailRelationShip> getZmailRelationShipPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getZmailRelationShipTotalPage(Integer num,Integer rows) {
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
