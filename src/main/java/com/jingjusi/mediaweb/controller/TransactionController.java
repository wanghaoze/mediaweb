package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.service.MyTransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="记账controller",tags={"记账信息操作接口"})
public class TransactionController {
    @Autowired
    MyTransactionService myTransactionService;
}
