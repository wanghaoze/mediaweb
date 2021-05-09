package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Transaction;
import com.jingjusi.mediaweb.common.domain.TransactionExample;
import com.jingjusi.mediaweb.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyTransactionServiceImpl implements MyTransactionService {
    @Autowired
    TransactionMapper mapper;
    @Override
    public String addTransaction(Transaction transaction) {
        try {
            mapper.insert(transaction);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String deleteTransaction(Long id) {
        try {
            mapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public PageInfo<Transaction> getTransactionByTargrt(String target) {
        try {
            TransactionExample example = new TransactionExample();
            example.createCriteria().andTargetLike("%"+target+"%");
            List<Transaction> list = new ArrayList<>(mapper.selectByExample(example));
            return new PageInfo<Transaction>(list);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<Transaction>();
        }
    }
}
