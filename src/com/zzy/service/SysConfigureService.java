package com.zzy.service;

import java.util.List;

import com.zzy.model.SysConfigure;
/**接口*/
public interface SysConfigureService {
	/**根据ID获取实体*/
	public SysConfigure getSysConfigureModel(Integer id);
	/**获取全部信息*/
	public List<SysConfigure> getAllSysConfigure(String hql,Object param[]);
	/**根据条件查询获取实体(分页)*/
	public List<SysConfigure> getSysConfigurePage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from SysConfigure
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getSysConfigureTotalPage(Integer num,Integer rows);
	/**根据ID删除实体1成功2异常*/
	public Integer delSysConfigureByID(Integer id);
	/**保存实体1成功2异常*/
	public Integer saveSysConfigure(SysConfigure sysConfigure);
	/**修改实体1成功2异常*/
	public Integer updateSysConfigure(SysConfigure sysConfigure);
	/**保存或者修改实体1成功2异常*/
	public Integer saveorupSysConfigure(SysConfigure sysConfigure);
}
