package com.jingjusi.mediaweb.mapper;

import com.jingjusi.mediaweb.common.domain.CourseUser;
import com.jingjusi.mediaweb.common.domain.CourseUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserMapper {
    long countByExample(CourseUserExample example);

    int deleteByExample(CourseUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseUser record);

    int insertSelective(CourseUser record);

    List<CourseUser> selectByExample(CourseUserExample example);

    CourseUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseUser record, @Param("example") CourseUserExample example);

    int updateByExample(@Param("record") CourseUser record, @Param("example") CourseUserExample example);

    int updateByPrimaryKeySelective(CourseUser record);

    int updateByPrimaryKey(CourseUser record);
}