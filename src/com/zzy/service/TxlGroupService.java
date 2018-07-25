package com.zzy.service;

import java.util.List;

import com.zzy.model.mail.TxlGroup;

/**市区  的接口*/
public interface TxlGroupService {
	/**根据ID获取实体*/
	public TxlGroup getTxlGroupModel(Integer id);
	/**获取全部信息*/
	public List<TxlGroup> getAllTxlGroup(String hql,Object param[]);
	/**根据条件查询获取实体(分页)*/
	public List<TxlGroup> getTxlGroupPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from TxlGroup
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getTxlGroupTotalPage(Integer num,Integer rows);
	/**根据ID删除实体1成功2异常*/
	public Integer delTxlGroupByID(Integer id);
	/**保存实体1成功2异常*/
	public Integer saveTxlGroup(TxlGroup TxlGroup);
	/**修改实体1成功2异常*/
	public Integer updateTxlGroup(TxlGroup TxlGroup);
	/**保存或者修改实体1成功2异常*/
	public Integer saveorupTxlGroup(TxlGroup TxlGroup);
}
