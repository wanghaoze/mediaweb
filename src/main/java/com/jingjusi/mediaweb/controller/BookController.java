package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.ProcessResult;
import com.jingjusi.mediaweb.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Api(value="图书controller",tags={"图书信息操作接口"})
public class BookController {
    @Autowired
    BookService bookService;
    @RequestMapping(value = "/bookManage/addBook",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> addbook(HttpServletRequest request,
                                 @RequestBody Book book) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            CommonResult<String> scresa;
            System.out.println(book.getPublishTime());
            book.setOperator(user.getUsername());
            if (book.getPublishTime()==null){
                book.setPublishTime(new Date());
            }
            if (book.getNumber()==0&&book.getNumber()==null) {
                book.setState(0L);
            } else {
                book.setState(1L);
            }
            String message = bookService.addBook(book);
            scresa = new CommonResult<>(200, message);
            return scresa;
        }catch (Exception e){
            return new CommonResult<>(200,"添加失败");
        }

    }
    @GetMapping(
            value = "/bookManage/deleteBook")
    @ResponseBody
    public CommonResult<String> delBook(
            Model model,
            HttpServletRequest request,@RequestBody Book book) {
        User user = (User) request.getSession().getAttribute("user");
        CommonResult<String> scres;
        try{
            String message = bookService.deleteBookByISBN(book.getIsbn());
            scres=new CommonResult<>(200,message);
        }catch (Exception e){
            scres=new CommonResult<>(200,"删除失败");
        }
        return scres;
    }
}
