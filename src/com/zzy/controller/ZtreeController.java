package com.zzy.controller;

import com.zzy.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ztree")
public class ZtreeController {
    @Autowired
    private UtilService utilService;


    /**
     * 获取ztree的数据
     */
    @RequestMapping(value="info")
    public void ztreeInfo(HttpServletRequest request, HttpServletResponse response){

    }

}
