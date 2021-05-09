package com.jingjusi.mediaweb.mapper;

import com.jingjusi.mediaweb.common.domain.Tablet;
import com.jingjusi.mediaweb.common.domain.TabletExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletMapper {
    long countByExample(TabletExample example);

    int deleteByExample(TabletExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tablet record);

    int insertSelective(Tablet record);

    List<Tablet> selectByExample(TabletExample example);

    Tablet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tablet record, @Param("example") TabletExample example);

    int updateByExample(@Param("record") Tablet record, @Param("example") TabletExample example);

    int updateByPrimaryKeySelective(Tablet record);

    int updateByPrimaryKey(Tablet record);
}