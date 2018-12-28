package com.zzy.service;

import com.zzy.model.Menu;
import com.zzy.model.MenuVo;

import java.util.List;

/**菜单*/
public interface MenuService {
    /**根据ID获取实体*/
    public Menu getModel(Integer id);
    /**根据HQL获取实体*/
    public Menu getModelByHql(String hql , Object param[]);
    /**获取全部信息*/
    public List<Menu> getAll(String hql, Object param[]);
    /**根据条件查询获取实体(分页)*/
    public List<Menu> getPage(String hql,Object[] param,Integer page,Integer rows);
    /**根据查询条件获取全部信息的条数
     * select count(*) from Classic
     * */
    public Integer getTotalNum(String hql,Object[] param);
    /**根据查询条件获取全部信息的页数*/
    public Integer getTotalPage(Integer num,Integer rows);
    /**根据ID删除实体1成功2异常*/
    public Integer delByID(Integer id);
    /**保存实体1成功2异常*/
    public Integer save(Menu model);
    /**修改实体1成功2异常*/
    public Integer update(Menu model);
    /**保存或者修改实体1成功2异常*/
    public Integer saveOrUpdate(Menu model);


    public List<MenuVo> getTree(String parentId);
    public String getTreeHtml(String parentId);


}
