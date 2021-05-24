package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.BookExample;
import com.jingjusi.mediaweb.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;
    @Override
    public String addBook(Book book) {

        try {
            bookMapper.insert(book);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String deleteBookByID(Long id) {
        try {
            bookMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public String deleteBookByISBN(String isbn) {
        try {
            BookExample bookExample = new BookExample();
            bookExample.createCriteria().andIsbnEqualTo(isbn);
            bookMapper.deleteByExample(bookExample);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }


    @Override
    public PageInfo<Book> getBooksByName(String bookName) {
        try {
            BookExample bookExample = new BookExample();
            bookExample.createCriteria().andBookNameLike("%"+bookName+"%");
            List<Book>books = new ArrayList<>(bookMapper.selectByExample(bookExample)) ;
            return new PageInfo<>(books);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<>();
        }
    }

    @Override
    public Book getBookByID(Long id) {
        try {
            return bookMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        try {
            BookExample bookExample = new BookExample();
            bookExample.createCriteria().andIsbnEqualTo(isbn);
            List<Book> books =  bookMapper.selectByExample(bookExample);
            if (!books.isEmpty()){
                return books.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String updateBookByID(Long id, Book book) {
        try {
            BookExample example =new BookExample();
            example.createCriteria().andIdEqualTo(id);
            bookMapper.updateByExampleSelective(book,example);
            return "还书成功";
        } catch (Exception e) {
            System.out.println(e);
            return "还书失败";
        }
    }
}
