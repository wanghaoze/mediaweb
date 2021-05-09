package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Book;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface BookService {
    public String addBook(Book book);
    public String deleteBook(Book book);
    public PageInfo<Book> getBooksByName(String bookName);
    public Book getBookByID(Long id);
    public Book getBookByISBN(String isbn);
}
