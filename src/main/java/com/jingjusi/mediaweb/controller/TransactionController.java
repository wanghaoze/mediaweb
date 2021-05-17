package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.MyTransaction;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.MyTransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Api(value="记账controller",tags={"记账信息操作接口"})
public class TransactionController {
    @Autowired
    MyTransactionService myTransactionService;
    @RequestMapping(value = "/transactionManage/addTransaction",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> addTransaction(HttpServletRequest request,
                                          @RequestBody MyTransaction myTransaction) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            myTransaction.setOperator(user.getUsername());
            CommonResult<String> scresa;
            System.out.println(myTransaction.getReason());
            if (myTransaction.getCnt()==null) {
                return new CommonResult<>(200,"记账金额不能为空");
            }
            if (myTransaction.getTarget()==null&&myTransaction.getReason()==null) {
                return new CommonResult<>(200,"收款方和事由不能都为空");
            }

            if (myTransaction.getCreateTime()==null){
                myTransaction.setCreateTime(new Date());
            }

            String message = myTransactionService.addTransaction(myTransaction);
            scresa = new CommonResult<>(200, message);
            return scresa;
        }catch (Exception e){
            return new CommonResult<>(200,"添加失败");
        }

    }
    @RequestMapping(value = "/transactionManage/deleteTransaction",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> deleteTransaction(
            HttpServletRequest request,@RequestBody MyTransaction myTransaction) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            return new CommonResult<>(200,"登录过期，请重新登录");
        }

        CommonResult<String> scres;

        try{
            String message = myTransactionService.deleteTransaction(myTransaction.getId());
            scres=new CommonResult<>(200,message);
        }catch (Exception e){
            scres=new CommonResult<>(200,"删除失败");
        }
        return scres;
    }
}
