package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Borrow;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface BorrowService {
    public String addBorrow(Borrow borrow);
    public PageInfo<Borrow> getBorrows(String bookName);
    public String updateBorrowByID(Long id, Borrow borrow);
    public PageInfo<Borrow> getBorrowByUserID(Long userID);
}
