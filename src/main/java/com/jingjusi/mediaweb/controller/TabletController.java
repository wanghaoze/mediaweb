package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.service.TabletService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="牌位controller",tags={"牌位信息操作接口"})
public class TabletController {

    @Autowired
    TabletService tabletService;
}
