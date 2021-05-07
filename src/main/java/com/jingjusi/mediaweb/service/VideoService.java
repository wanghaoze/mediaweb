package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Video;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface VideoService {
    public String addVideo(Video video);
    public String deleteVideo(Long videoID);
    public Video getVideoInfoById(Long videoID);
    public PageInfo<Video> getVideosByName(String keyword, Integer pageNo, Integer pageSize);
    public PageInfo<Video> getVideosByCourse(Long courseId, Integer pageNo, Integer pageSize);
    public String addVideoToCourse(Long videoId, Long courseId);
    public String deleteVideoFromCourse(Long videoId, Long courseId);
    public String updateVideoById(Long videoId, Video video);
}
