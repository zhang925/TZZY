package com.zzy.service;

import java.util.List;

import com.zzy.model.Classic;
/**经典语录服务的接口*/
public interface ClassicService {
	/**根据ID获取经典语录实体*/
	public Classic getClassicModel(Integer id);
	/**根据HQL获取实体*/
	public Classic getClassicModelByHql(String hql , Object param[]);
	/**获取全部经典语录信息*/
	public List<Classic> getAllClassic(String hql,Object param[]);
	/**根据条件查询获取经典语录实体(分页)*/
	public List<Classic> getClassicPage(String hql,Object[] param,Integer page,Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Classic
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getClassicTotalPage(Integer num,Integer rows);
	/**根据ID删除经典语录实体1成功2异常*/
	public Integer delClassicByID(Integer id);
	/**保存经典语录实体1成功2异常*/
	public Integer saveClassic(Classic classic);
	/**修改经典语录实体1成功2异常*/
	public Integer updateClassic(Classic classic);
	/**保存或者修改经典语录实体1成功2异常*/
	public Integer saveorupClassic(Classic classic);
}
