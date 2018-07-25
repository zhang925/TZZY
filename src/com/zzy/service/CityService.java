package com.zzy.service;

import java.util.List;

import com.zzy.model.district.City;
/**市区  的接口*/
public interface CityService {
	/**根据ID获取省份实体*/
	public City getCityModel(Integer id);
	/**获取全部省份信息*/
	public List<City> getAllCity(String hql,Object param[]);
	/**根据条件查询获取省份实体(分页)*/
	public List<City> getCityPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from City
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getCityTotalPage(Integer num,Integer rows);
	/**根据ID删除省份实体1成功2异常*/
	public Integer delCityByID(Integer id);
	/**保存省份实体1成功2异常*/
	public Integer saveCity(City City);
	/**修改省份实体1成功2异常*/
	public Integer updateCity(City city);
	/**保存或者修改省份实体1成功2异常*/
	public Integer saveorupCity(City City);
}
