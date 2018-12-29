package com.zzy.service.impl;

import com.zzy.dao.BaseDao;
import com.zzy.model.Menu;
import com.zzy.model.MenuVo;
import com.zzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**省份 接口的实现*/
@Transactional
@Service()
public class MenuServiceImpl implements MenuService {
    @Autowired
    private BaseDao< Menu> basedao;


    /**根据ID获取实体*/
    public Menu getModel(Integer id){
        String hql="from Menu where id=?";
        Object []param=new Object[]{id};
        Menu o = basedao.get(hql, param);
        return o;
    }

    /**根据HQL获取实体*/
    public Menu getModelByHql(String hql , Object param[]){
        return basedao.get(hql, param);
    }

    /**获取全部经典语录信息*/
    public List<Menu> getAll(String hql, Object param[]){
        return basedao.find(hql,param);
    }
    /**根据条件查询获取经典语录实体(分页)*/
    public List<Menu> getPage(String hql,Object[] param,Integer page,Integer rows){
        return basedao.find(hql, param, page, rows);
    }
    /**根据查询条件获取全部信息的条数
     * select count(*) from Classic
     * */
    public Integer getTotalNum(String hql,Object[] param){
        long k = basedao.count(hql,param);
        return (int) k;
    }
    /**根据查询条件获取全部信息的页数*/
    public Integer getTotalPage(Integer num,Integer rows){
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
    /**根据ID删除经典语录实体1成功2异常*/
    public Integer delByID(Integer id){
        Integer i=1;
        Menu o = new Menu();
        try {
            o=getModel(id);
            basedao.delete(o);
        } catch (Exception e) {
            i=2;
            System.out.println("异常！删除失败！");
        }
        return i;
    }
    /**保存经典语录实体1成功2异常*/
    public Integer save(Menu model){
        Integer i=1;
        try {
            basedao.save(model);
        } catch (Exception e) {
            i=2;
            System.out.println("异常！保存失败！");
        }
        return i;
    }
    /**修改经典语录实体1成功2异常*/
    public Integer update(Menu model){
        Integer i=1;
        try {
            basedao.update(model);
        } catch (Exception e) {
            i=2;
            System.out.println("异常！更新失败！");
        }
        return i;
    }
    /**保存或者修改经典语录实体1成功2异常*/
    public Integer saveOrUpdate(Menu model){
        Integer i=1;
        try {
            basedao.saveOrUpdate(model);
        } catch (Exception e) {
            i=2;
            System.out.println("异常！更新失败！");
        }
        return i;
    }








    public String getTreeHtml(String parentId) {
        String treeHTml = "<ul id='shu' class='shu_files_ul'>";
        if(parentId!=null){
            treeHTml = "<ul role='group'>";
        }
        List<Menu> roots = (List) listNextHtml(parentId);
        for (Menu root : roots) {
            String name = root.getMenuname();
            String id= root.getId();
            treeHTml +="<li id='"+id+"' >"
                    +"<a href='javascript:void(0);'>"
                    + name
                    +"<div class='shu_files_icon'>"
                    +"<span onclick='addNode(\""+id+"\");' class='shu_files_icon1'></span>&nbsp;&nbsp;&nbsp;"
                    +"<span onclick='updateNode(\""+id+"\");' class='shu_files_icon2'></span>&nbsp;&nbsp;&nbsp;"
                    +"<span onclick='delNode(\""+id+"\");' class='shu_files_icon3'></span>"
                    +"</div>"
                    +"</a>"
                    //加载第二级
                    + getTreeHtml(id)
                    +"</li>";
        }
        treeHTml += "</ul>";
        return treeHTml;
    }



    public List<Menu> listNextHtml(String parentId) {
        String hql = "from Menu where 1=1 ";
        Object[] val = new Object[]{} ;
        if (StringUtils.hasText(parentId)) {
            hql += " and parentId = ? ";
            val = new Object[]{parentId} ;
        } else {
            hql += "and parentId = '' or parentId is null ";
        }
        return basedao.find(hql,val);
    }






    public List<MenuVo> getTree(String parentId) {
        List<MenuVo> vos = new ArrayList<MenuVo>();
        List<Menu> roots = listNext(parentId);
        for (Menu root : roots) {
            MenuVo vo = new MenuVo();
            vo.setId(root.getId());
            vo.setName(root.getMenuname());
            vo.setUrl(root.getUrl());
            vo.setChildren(getTree(root.getId()));
            vo.setNocheck(false);//是否不让选择
            vo.setChecked(false);//节点的 checkBox / radio 的 勾选状态。
            vo.setOpen(false);//是否展开
            //chkDisabled;//1、设置节点的 checkbox / radio 是否禁用
            //isParent;//记录 treeNode 节点是否为父节点。
            //target;//设置点击节点后在何处打开 url。
            vos.add(vo);
        }
        return vos;
    }

    public List<Menu> listNext(String parentId) {
        String hql = "from Menu where 1=1 ";
        Object[] val = new Object[]{} ;
        if (StringUtils.hasText(parentId)) {
            hql += " and parentId = ? ";
            val = new Object[]{parentId} ;
        } else {
            hql += "and parentId = '' or parentId is null ";
        }
        return basedao.find(hql,val);
    }



}


