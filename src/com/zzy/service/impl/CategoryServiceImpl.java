package com.zzy.service.impl;

import com.zzy.dao.BaseDao;
import com.zzy.model.Category;
import com.zzy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**经典语录服务接口的实现*/
@Transactional
@Service()
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private BaseDao<Category> basedao;

	/**根据实体ID删除Category*/
	public Integer delCategoryByID(String id) {
		Integer i=1;
		Category o = new Category();
		try {
			o=getCategoryModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Category*/
	public Category getCategoryModel(String id) {
		String hql="from Category where id=?";
		Object []param=new Object[]{id};
		Category o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Category*/
	
	public Integer saveCategory(Category Category) {
		Integer i=1;
		try {
			basedao.save(Category);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Category*/
	public Integer updateCategory(Category Category) {
		Integer i=1;
		try {
			basedao.update(Category);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}

	public Integer saveOrUpdateCategory(Category Category) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Category);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Category>*/
	public List<Category> getAllCategory(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Category>*/
	public List<Category> getCategoryPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getCategoryTotalPage(Integer num,Integer rows) {
		int totalpage=1;
		if(num >= rows){
			if(num % rows == 0){
				totalpage = num / rows;
			}else{
				totalpage = (num / rows)+1;
			}
		}
		return totalpage;
	}

	public Category getCategoryModelByHql(String hql, Object[] param) {
		return basedao.get(hql, param);
	}

}
