package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.MyTransaction;
import com.jingjusi.mediaweb.common.domain.MyTransactionExample;
import com.jingjusi.mediaweb.mapper.MyTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyTransactionServiceImpl implements MyTransactionService {
    @Autowired
    MyTransactionMapper mapper;
    @Override
    public String addTransaction(MyTransaction transaction) {
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
            if (!testTransaction(id)) {
                return "数据库中不存在该项记录";
            }
            mapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public Boolean testTransaction(Long id) {
        try {
            return mapper.selectByPrimaryKey(id) != null;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public PageInfo<MyTransaction> getTransactionByTargrt(String target, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo,pageSize);
            MyTransactionExample example = new MyTransactionExample();
            example.createCriteria().andTargetLike("%"+target+"%");
            List<MyTransaction> list = new ArrayList<>(mapper.selectByExample(example));
            return new PageInfo<MyTransaction>(list);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<MyTransaction>();
        }
    }
}
