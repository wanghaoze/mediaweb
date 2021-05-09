package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.ProcessResult;
import com.jingjusi.mediaweb.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(value="图书controller",tags={"图书信息操作接口"})
public class BookController {
    @Autowired
    BookService bookService;
    @RequestMapping(value = "/admin/books",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addbook(HttpServletRequest request,
                                 @RequestBody Book book) {
        User user = (User) request.getSession().getAttribute("user");
        ProcessResult ar;
        book.setOperator(user.getUsername());
        bookService.addBook(book);
        ar = new ProcessResult(true);
        return ar;
    }
}