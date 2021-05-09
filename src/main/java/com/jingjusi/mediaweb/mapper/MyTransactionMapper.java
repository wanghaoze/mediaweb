package com.jingjusi.mediaweb.mapper;

import com.jingjusi.mediaweb.common.domain.MyTransaction;
import com.jingjusi.mediaweb.common.domain.MyTransactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTransactionMapper {
    long countByExample(MyTransactionExample example);

    int deleteByExample(MyTransactionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyTransaction record);

    int insertSelective(MyTransaction record);

    List<MyTransaction> selectByExample(MyTransactionExample example);

    MyTransaction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyTransaction record, @Param("example") MyTransactionExample example);

    int updateByExample(@Param("record") MyTransaction record, @Param("example") MyTransactionExample example);

    int updateByPrimaryKeySelective(MyTransaction record);

    int updateByPrimaryKey(MyTransaction record);
}