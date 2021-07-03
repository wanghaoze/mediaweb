package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.Borrow;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.ProcessResult;
import com.jingjusi.mediaweb.service.BookService;
import com.jingjusi.mediaweb.service.BorrowService;
import com.jingjusi.mediaweb.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Controller
@Api(value="图书controller",tags={"图书信息操作接口"})
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    BorrowService borrowService;
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
    @RequestMapping(
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

    @RequestMapping(value = "/bookManage/borrowBook")
    @ResponseBody
    public CommonResult<String> borrowBook(Model model,
                                       HttpServletRequest request, @RequestBody Borrow borrow) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            CommonResult<String> scresa;
            if (borrow.getIsbn()==null) {
                return new CommonResult<>(200,"ISBN不能为空");
            }
            if (borrow.getUserName()==null) {
                return  new CommonResult<>(200,"用户名不能为空");
            }
            Book book = bookService.getBookByISBN(borrow.getIsbn());
            if (book == null) {
                return new CommonResult<>(200,"图书不存在");
            }
            if (book.getNumber()<=0) {
                return new CommonResult<>(200,"图书余量为0");
            }
            User user1 = userService.findUser(borrow.getUserName());
            if (user1 == null) {
                return new CommonResult<>(200,"用户不存在");
            }
            Date date = new Date();
            borrow.setBorrowTime(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            rightNow.add(Calendar.MONTH, 1);
            Date date1 = rightNow.getTime();
            borrow.setDeadline(date1);
            borrow.setOperator(user.getUsername());
            borrow.setBookName(book.getBookName());
            borrow.setUserId(user1.getId());
            borrow.setBookId(book.getId());
            book.setNumber(book.getNumber()-1);
            bookService.updateBookByID(book.getId(),book);
            borrowService.addBorrow(borrow);
            return new CommonResult<>(200,"借书成功");
        }catch (Exception e){
            return new CommonResult<>(200,"借书失败");
        }
    }

    @RequestMapping(value = "/bookManage/returnBook")
    @ResponseBody
    public CommonResult<String> returnBook(Model model,
                                       HttpServletRequest request, @RequestBody Borrow borrow) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                return new CommonResult<>(200, "登录过期，请重新登录");
            }
            CommonResult<String> scresa;
            borrow = borrowService.getBorrow(borrow.getId());
            if (borrow == null) {
                return new CommonResult<>(200, "借阅信息获取失败");
            }
            Book book = bookService.getBookByISBN(borrow.getIsbn());
            if (book == null) {
                return new CommonResult<>(200, "图书已被删除");
            }
            book.setNumber(book.getNumber() + 1);
            borrow.setReturnTime(new Date());
            User user1 = userService.findUser(borrow.getUserName());
            if (user1 == null) {
                return new CommonResult<>(200, "用户已被删除");
            }
            String res = borrowService.updateBorrowByID(borrow.getId(),borrow);

            bookService.updateBookByID(book.getId(), book);
            borrowService.updateBorrowByID(borrow.getId(), borrow);
            return new CommonResult<>(200,"还书成功");

        } catch (Exception e) {
            return new CommonResult<>(200, "还书失败");
        }
    }
}
