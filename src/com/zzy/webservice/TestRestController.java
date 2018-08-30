package com.zzy.webservice;

import com.zzy.service.AreaService;
import com.zzy.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sso.model.AjaxMsg;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestRestController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping(value="/list",method= RequestMethod.GET)
    @ResponseBody
    public AjaxMsg list(String parentid, HttpServletRequest request){
        if(parentid==null || parentid.equals("")){
            parentid = "410000";
        }
        List list = districtService.getAllDistrict("from District where parentid=? ",new Object []{parentid});
        return AjaxMsg.returnAjaxMsg(list);
    }
}
