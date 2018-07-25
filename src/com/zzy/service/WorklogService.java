package com.zzy.service;

import java.util.List;

import com.zzy.model.Worklog;
/**记事本服务的接口*/
public interface WorklogService {
	/**根据ID获取记事本实体*/
	public Worklog getWorklogModel(Integer id);
	/**获取全部记事本信息*/
	public List<Worklog> getAllWorklog(String hql,Object param[]);
	/**根据条件查询获取记事本实体(分页)*/
	public List<Worklog> getWorklogPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Worklog
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getWorklogTotalPage(Integer num,Integer rows);
	/**根据ID删除记事本实体1成功2异常*/
	public Integer delWorklogByID(Integer id);
	/**保存记事本实体1成功2异常*/
	public Integer saveWorklog(Worklog worklog);
	/**修改记事本实体1成功2异常*/
	public Integer updateWorklog(Worklog worklog);
	/**保存或者修改记事本实体1成功2异常*/
	public Integer saveorupWorklog(Worklog worklog);
}
