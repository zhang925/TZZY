package com.zzy.service;

import java.util.List;

import com.zzy.model.mail.Txl;

/**市区  的接口*/
public interface TxlService {
	/**根据ID获取实体*/
	public Txl getTxlModel(Integer id);
	/**获取全部信息*/
	public List<Txl> getAllTxl(String hql,Object param[]);
	/**根据条件查询获取实体(分页)*/
	public List<Txl> getTxlPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Txl
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getTxlTotalPage(Integer num,Integer rows);
	/**根据ID删除实体1成功2异常*/
	public Integer delTxlByID(Integer id);
	/**保存实体1成功2异常*/
	public Integer saveTxl(Txl Txl);
	/**修改实体1成功2异常*/
	public Integer updateTxl(Txl Txl);
	/**保存或者修改实体1成功2异常*/
	public Integer saveorupTxl(Txl Txl);
}
