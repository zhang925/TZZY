package com.zzy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.Classic;
import com.zzy.service.ClassicService;

/**经典语录服务接口的实现*/
@Transactional
@Service()
public class ClassicServiceImpl implements ClassicService{
	private BaseDao<Classic> basedao;
	
	public BaseDao<Classic> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Classic> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Classic*/
	public Integer delClassicByID(Integer id) {
		Integer i=1;
		Classic o = new Classic();
		try {
			o=getClassicModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Classic*/
	public Classic getClassicModel(Integer id) {
		String hql="from Classic where id=?";
		Object []param=new Object[]{id};
		Classic o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Classic*/
	
	public Integer saveClassic(Classic Classic) {
		Integer i=1;
		try {
			basedao.save(Classic);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Classic*/
	public Integer updateClassic(Classic Classic) {
		Integer i=1;
		try {
			basedao.update(Classic);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupClassic(Classic Classic) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Classic);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Classic>*/
	public List<Classic> getAllClassic(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Classic>*/
	public List<Classic> getClassicPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getClassicTotalPage(Integer num,Integer rows) {
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

	public Classic getClassicModelByHql(String hql, Object[] param) {
		return basedao.get(hql, param);
	}

}
