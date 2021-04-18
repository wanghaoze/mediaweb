package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Video;
import com.jingjusi.mediaweb.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoServiceImpl implements VideoService{
    @Resource
    @Autowired
    VideoMapper videoMapper;
    @Override
    public String addVideo(Video video) {
        videoMapper.insert(video);
        return "添加成功";
    }

    @Override
    public Video getVideoInfoById(Long videoID) {
        return null;
    }

    @Override
    public PageInfo<Video> getVideosByName(String keyword, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<Video> getVideosByCourse(Long courseId, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public String addVideoToCourse(Long videoId, Long courseId) {
        return null;
    }

    @Override
    public String deleteVideoFromCourse(Long videoId, Long courseId) {
        return null;
    }
}
