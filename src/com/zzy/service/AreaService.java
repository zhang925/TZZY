package com.zzy.service;

import java.util.List;

import com.zzy.model.district.Area;
/**地区  的接口*/
public interface AreaService {
	/**根据ID获取市实体*/
	public Area getAreaModel(Integer id);
	/**获取全部市信息*/
	public List<Area> getAllArea(String hql,Object param[]);
	/**根据条件查询获取市 实体(分页)*/
	public List<Area> getAreaPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Area
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getAreaTotalPage(Integer num,Integer rows);
	/**根据ID删除市实体1成功2异常*/
	public Integer delAreaByID(Integer id);
	/**保存市市实体1成功2异常*/
	public Integer saveArea(Area Area);
	/**修改市市实体1成功2异常*/
	public Integer updateArea(Area Area);
	/**保存或者修改市 实体1成功2异常*/
	public Integer saveorupArea(Area Area);
}
