package com.zzy.service;

import java.util.List;

import com.zzy.model.mail.Zmail;

/**市区  的接口*/
public interface ZmailService {
	/**根据ID获取省份实体*/
	public Zmail getZmailModel(Integer id);
	/**获取全部省份信息*/
	public List<Zmail> getAllZmail(String hql,Object param[]);
	/**根据条件查询获取省份实体(分页)*/
	public List<Zmail> getZmailPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Zmail
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getZmailTotalPage(Integer num,Integer rows);
	/**根据ID删除省份实体1成功2异常*/
	public Integer delZmailByID(Integer id);
	/**保存省份实体1成功2异常*/
	public Integer saveZmail(Zmail Zmail);
	/**修改省份实体1成功2异常*/
	public Integer updateZmail(Zmail Zmail);
	/**保存或者修改省份实体1成功2异常*/
	public Integer saveorupZmail(Zmail Zmail);
}
