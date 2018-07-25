package com.zzy.service;

import java.util.List;

import com.zzy.model.district.Province;
import com.zzy.util.page.PageZzy;
/**省份服务的接口*/
public interface ProvinceService {
	/**根据ID获取省份实体*/
	public Province getProvinceModel(Integer id);
	/**获取全部省份信息*/
	public List<Province> getAllProvince(String hql,Object param[]);
	/**根据条件查询获取省份实体(分页1)*/
	public List<Province> getProvincePage(String hql,Object[] param,Integer page,Integer rows);
	/**根据条件查询获取省份实体(分页2)*/
	public PageZzy getProvincePageZzy(String hql,Object[] param,Integer page,Integer rows);
	
	
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Province
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getProvinceTotalPage(Integer num,Integer rows);
	/**根据ID删除省份实体1成功2异常*/
	public Integer delProvinceByID(Integer id);
	/**保存省份实体1成功2异常*/
	public Integer saveProvince(Province province);
	/**修改省份实体1成功2异常*/
	public Integer updateProvince(Province province);
	/**保存或者修改省份实体1成功2异常*/
	public Integer saveorupProvince(Province province);
}
