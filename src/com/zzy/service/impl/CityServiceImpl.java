package com.zzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.district.City;
import com.zzy.service.CityService;;

/**市区 接口的实现*/
@Transactional
@Service()
public class CityServiceImpl implements CityService{
	@Autowired
	private BaseDao<City> basedao;
	

	/**根据实体ID删除City*/
	public Integer delCityByID(Integer id) {
		Integer i=1;
		City o = new City();
		try {
			o=getCityModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取City*/
	public City getCityModel(Integer id) {
		String hql="from City where id=?";
		Object []param=new Object[]{id};
		City o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存City*/
	public Integer saveCity(City City) {
		Integer i=1;
		try {
			basedao.save(City);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新City*/
	public Integer updateCity(City city) {
		Integer i=1;
		try {
			basedao.update(city);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupCity(City city) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(city);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<City>*/
	public List<City> getAllCity(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<City>*/
	public List<City> getCityPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getCityTotalPage(Integer num,Integer rows) {
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
