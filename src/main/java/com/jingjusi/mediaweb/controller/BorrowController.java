package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.Borrow;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.ProcessResult;
import com.jingjusi.mediaweb.service.BorrowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value="借阅controller",tags={"图书借阅操作接口"})
public class BorrowController {

    @Autowired
    BorrowService borrowService;
    @RequestMapping(value = "/admin/books",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addBorrow(HttpServletRequest request,
                                 @RequestBody Borrow borrow) {
        User user = (User) request.getSession().getAttribute("user");
        ProcessResult ar;
        borrow.setOperator(user.getUsername());
        borrowService.addBorrow(borrow);
        ar = new ProcessResult(true);
        return ar;
    }
}
