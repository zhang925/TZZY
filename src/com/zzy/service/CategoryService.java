package com.zzy.service;

import com.zzy.model.Category;

import java.util.List;

/**分类管理 的接口*/
public interface CategoryService {
	/**根据ID获取实体*/
	public Category getCategoryModel(String id);
	/**根据HQL获取实体*/
	public Category getCategoryModelByHql(String hql, Object param[]);
	/**获取全部信息*/
	public List<Category> getAllCategory(String hql, Object param[]);
	/**根据条件查询获取实体(分页)*/
	public List<Category> getCategoryPage(String hql, Object[] param, Integer page, Integer rows);
	/**根据查询条件获取全部信息的条数
	 * select count(*) from Category
	 * */
	public Integer getTotalNum(String hql, Object[] param);
	/**根据查询条件获取全部信息的页数*/
	public Integer getCategoryTotalPage(Integer num, Integer rows);
	/**根据ID删除实体1成功2异常*/
	public Integer delCategoryByID(String id);
	/**保存实体1成功2异常*/
	public Integer saveCategory(Category category);
	/**修改实体1成功2异常*/
	public Integer updateCategory(Category category);
	/**保存或者修改实体1成功2异常*/
	public Integer saveOrUpdateCategory(Category category);
}
