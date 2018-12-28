package com.zzy.controller;

import com.zzy.model.Menu;
import com.zzy.model.SysConfigure;
import com.zzy.service.MenuService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private UtilService utilService;


    @Autowired
    private MenuService menuService;


    /**
     * 查看[load=detail]、修改[load=update]、添加[load=add]的页面跳转
     */
    @RequestMapping(value="jump")
    public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){

        request.setAttribute("load", request.getParameter("load"));

        return "/webjsp/other/ztree/ztree_menu_editor.jsp";
    }



    /**
     * 保存
     */
    @RequestMapping(value="save")
    public void ztreeInfo(Menu model, HttpServletRequest request, HttpServletResponse response){
        System.out.println(1);
    }

    /**
     * 修改
     */
    @RequestMapping(value="update")
    public void update(Menu model,HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * 修改
     */
    @RequestMapping(value="delete")
    public void delete(HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * 树形菜单
     */
    @RequestMapping(value="tree")
    public void tree(HttpServletRequest request, HttpServletResponse response){

    }
}
