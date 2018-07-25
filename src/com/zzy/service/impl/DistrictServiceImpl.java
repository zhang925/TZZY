package com.zzy.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.district.District;
import com.zzy.service.DistrictService;

/**地区 详情  接口的实现*/
@Transactional
public class DistrictServiceImpl implements DistrictService{
	private BaseDao<District> basedao;
	
	public BaseDao<District> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<District> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除District*/
	public Integer delDistrictByID(Integer id) {
		Integer i=1;
		District o = new District();
		try {
			o=getDistrictModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取District*/
	public District getDistrictModel(Integer id) {
		String hql="from District where id=?";
		Object []param=new Object[]{id};
		District o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存District*/
	public Integer saveDistrict(District District) {
		Integer i=1;
		try {
			basedao.save(District);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新District*/
	public Integer updateDistrict(District District) {
		Integer i=1;
		try {
			basedao.update(District);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupDistrict(District District) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(District);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<District>*/
	public List<District> getAllDistrict(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<District>*/
	public List<District> getDistrictPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getDistrictTotalPage(Integer num,Integer rows) {
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
