package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Tablet;
import com.jingjusi.mediaweb.common.domain.TabletExample;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.TabletService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Api(value="牌位controller",tags={"牌位信息操作接口"})
public class TabletController {

    @Autowired
    TabletService tabletService;
    @RequestMapping(value = "/tabletManage/addTablet",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> addTablet(HttpServletRequest request,
                                        @RequestBody Tablet tablet) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            if (tablet.getDistribution()==null|| tablet.getDistribution().equals("")) {
                return new CommonResult<>(200,"区号不能为空");
            }
            if (tablet.getRowNum()==null) {
                return new CommonResult<>(200,"行数不能为空");
            }

            if (tablet.getColNum()==null) {
                return new CommonResult<>(200,"列数不能为空");
            }

            Tablet tabletA = new Tablet();
            tabletA.setDistribution(tablet.getDistribution());
            tabletA.setColNum(tablet.getColNum());
            tabletA.setRowNum(tablet.getRowNum());
            List<Tablet> list = tabletService.getTabletsByExample(tabletA,0,20).getList();
            if (!list.isEmpty()){
                return new CommonResult<>(200,"该位置已存在，请先将其删除后添加");
            }
            CommonResult<String> scresa;
            System.out.println(tablet.getRemarks());
            if (tablet.getCreateTime()==null){
                tablet.setCreateTime(new Date());
            }
            System.out.println(tablet.getExpireTime());
            if (tablet.getExpireTime()==null){
                Date date = tablet.getCreateTime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.YEAR, 1);
                tablet.setExpireTime(cal.getTime());
            }
            tablet.setLocation("海会塔");
            String message = tabletService.addTablet(tablet);
            scresa = new CommonResult<>(200, message);
            return scresa;
        }catch (Exception e){
            return new CommonResult<>(200,"添加失败");
        }

    }
    @RequestMapping(value = "/tabletManage/deleteTablet",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> delTablet(
            Model model,
            HttpServletRequest request,@RequestBody Tablet tablet) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            return new CommonResult<>(200,"登录过期，请重新登录");
        }

        CommonResult<String> scres;

        if (tablet.getDistribution()==null|| tablet.getDistribution().equals("")) {
            return new CommonResult<>(200,"区号不能为空");
        }
        if (tablet.getRowNum()==null) {
            return new CommonResult<>(200,"行数不能为空");
        }

        if (tablet.getColNum()==null) {
            return new CommonResult<>(200,"列数不能为空");
        }

        List<Tablet> list = tabletService.getTabletsByExample(tablet,0,20).getList();
        if (list.isEmpty()){
            return new CommonResult<>(200,"该位置不存在");
        }
        tablet = list.get(0);
        try{
            String message = tabletService.deleteTablet(tablet.getId());
            scres=new CommonResult<>(200,message);
        }catch (Exception e){
            scres=new CommonResult<>(200,"删除失败");
        }
        return scres;
    }
}
