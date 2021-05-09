package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Transaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface MyTransactionService {
    public String addTransaction(Transaction transaction);
    public String deleteTransaction(Long id);
    public PageInfo<Transaction> getTransactionByTargrt(String target);
}
