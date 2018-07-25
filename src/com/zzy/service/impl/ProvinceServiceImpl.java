package com.zzy.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.district.Province;
import com.zzy.service.ProvinceService;
import com.zzy.util.page.PageZzy;

/**省份 接口的实现*/
@Transactional
public class ProvinceServiceImpl implements ProvinceService{
	private BaseDao<Province> basedao;
	
	public BaseDao<Province> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Province> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Province*/
	public Integer delProvinceByID(Integer id) {
		Integer i=1;
		Province o = new Province();
		try {
			o=getProvinceModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Province*/
	public Province getProvinceModel(Integer id) {
		String hql="from Province where id=?";
		Object []param=new Object[]{id};
		Province o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Province*/
	public Integer saveProvince(Province Province) {
		Integer i=1;
		try {
			basedao.save(Province);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Province*/
	public Integer updateProvince(Province Province) {
		Integer i=1;
		try {
			basedao.update(Province);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupProvince(Province Province) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Province);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Province>*/
	public List<Province> getAllProvince(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Province>*/
	public List<Province> getProvincePage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	
	public PageZzy getProvincePageZzy(String hql,Object[] param,Integer page,Integer rows) {
		List list=basedao.find(hql, param, page, rows);
		PageZzy pageZzy=new PageZzy();
		pageZzy.setList(list);
		pageZzy.setTotal(getTotalNum(hql, param));
		pageZzy.setPage(page);
		pageZzy.setRows(rows);
		return pageZzy;
	}
	
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getProvinceTotalPage(Integer num,Integer rows) {
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
