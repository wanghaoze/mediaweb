package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Override
    public String addBook(Book book) {
        return null;
    }

    @Override
    public String deleteBook(Book book) {
        return null;
    }

    @Override
    public PageInfo<Book> getBooksByName(String bookName) {
        return null;
    }

    @Override
    public Book getBookByID(Long id) {
        return null;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        return null;
    }
}
