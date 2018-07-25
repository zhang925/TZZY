package com.zzy.service;

import java.util.List;

import com.zzy.model.mail.ZmailRelationShip;

/**市区  的接口*/
public interface ZmailRelationShipService {
	/**根据ID获取省份实体*/
	public ZmailRelationShip getZmailRelationShipModel(Integer id);
	/**获取全部省份信息*/
	public List<ZmailRelationShip> getAllZmailRelationShip(String hql,Object param[]);
	/**根据条件查询获取省份实体(分页)*/
	public List<ZmailRelationShip> getZmailRelationShipPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from ZmailRelationShip
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getZmailRelationShipTotalPage(Integer num,Integer rows);
	/**根据ID删除省份实体1成功2异常*/
	public Integer delZmailRelationShipByID(Integer id);
	/**保存省份实体1成功2异常*/
	public Integer saveZmailRelationShip(ZmailRelationShip ZmailRelationShip);
	/**修改省份实体1成功2异常*/
	public Integer updateZmailRelationShip(ZmailRelationShip ZmailRelationShip);
	/**保存或者修改省份实体1成功2异常*/
	public Integer saveorupZmailRelationShip(ZmailRelationShip ZmailRelationShip);
}
