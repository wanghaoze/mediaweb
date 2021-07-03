package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.MyTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface MyTransactionService {
    public String addTransaction(MyTransaction transaction);
    public String deleteTransaction(Long id);
    public Boolean testTransaction(Long id);
    PageInfo<MyTransaction> getTransactionByTargrt(String target, Integer pageNo, Integer pageSize);
}
