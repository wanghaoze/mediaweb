package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.BookExample;
import com.jingjusi.mediaweb.common.domain.Borrow;
import com.jingjusi.mediaweb.common.domain.BorrowExample;
import com.jingjusi.mediaweb.mapper.BookMapper;
import com.jingjusi.mediaweb.mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    BookMapper bookMapper;
    @Override
    public String addBorrow(Borrow borrow) {
        try {
            borrowMapper.insert(borrow);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public PageInfo<Borrow> getBorrows(String bookName) {
        try {
            BorrowExample example = new BorrowExample();
            BookExample bookExample = new BookExample();
            bookExample.createCriteria().andBookNameLike("%"+bookName+"%");
            List<Borrow> borrows = new ArrayList<>();
            List<Book> books = bookMapper.selectByExample(bookExample);
            for (Book book:books) {
                BorrowExample borrowExample = new BorrowExample();
                borrowExample.createCriteria().andBookIdEqualTo(book.getId());
                List<Borrow> borrows_now = new ArrayList<>(borrowMapper.selectByExample(borrowExample));
                borrows.addAll(borrows_now);
            }
            return new PageInfo<>(borrows);
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String updateBorrowByID(Long id, Borrow borrow) {
        try {
            BorrowExample borrowExample = new BorrowExample();
            borrowExample.createCriteria().andBookIdEqualTo(id);
            borrowMapper.updateByExampleSelective(borrow,borrowExample);
            return "更新成功";
        } catch (Exception e) {
            System.out.println(e);
            return "更新失败";
        }
    }

    @Override
    public PageInfo<Borrow> getBorrowByUserID(Long userID) {
        try {
            BorrowExample borrowExample = new BorrowExample();
            borrowExample.createCriteria().andUserIdEqualTo(userID);
            List<Borrow> borrows = new ArrayList<>(borrowMapper.selectByExample(borrowExample));
            return new PageInfo<>(borrows);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
