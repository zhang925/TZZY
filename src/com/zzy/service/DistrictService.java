package com.zzy.service;

import java.util.List;

import com.zzy.model.district.District;
/**地区详情   的接口*/
public interface DistrictService {
	/**根据ID获取市实体*/
	public District getDistrictModel(Integer id);
	/**获取全部市信息*/
	public List<District> getAllDistrict(String hql,Object param[]);
	/**根据条件查询获取市 实体(分页)*/
	public List<District> getDistrictPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from District
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getDistrictTotalPage(Integer num,Integer rows);
	/**根据ID删除市实体1成功2异常*/
	public Integer delDistrictByID(Integer id);
	/**保存市市实体1成功2异常*/
	public Integer saveDistrict(District District);
	/**修改市市实体1成功2异常*/
	public Integer updateDistrict(District District);
	/**保存或者修改市 实体1成功2异常*/
	public Integer saveorupDistrict(District District);
}
