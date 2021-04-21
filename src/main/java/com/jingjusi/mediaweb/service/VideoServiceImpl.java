package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.*;
import com.jingjusi.mediaweb.mapper.CourseVideoMapper;
import com.jingjusi.mediaweb.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{
    @Resource
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    CourseVideoMapper courseVideoMapper;
    @Override
    public String addVideo(Video video) {
        try
        {
            videoMapper.insert(video);
            return "添加成功";
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Video getVideoInfoById(Long videoID) {
        try {
            return videoMapper.selectByPrimaryKey(videoID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PageInfo<Video> getVideosByName(String keyword, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        try {
            VideoExample videoExample = new VideoExample();
            videoExample.createCriteria().andVideoNameLike("%"+keyword+"%");
            List<Video> videos = new ArrayList<>(videoMapper.selectByExample(videoExample));
            return new PageInfo<>(videos);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PageInfo<Video> getVideosByCourse(Long courseId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        try {
            CourseVideoExample courseVideoExample = new CourseVideoExample();
            courseVideoExample.createCriteria().andCourseIdEqualTo(courseId);
            List<CourseVideo> courseVideos = new ArrayList<>(courseVideoMapper.selectByExample(courseVideoExample));
            if (courseVideos.isEmpty()) {
                return null;
            }
            List<Video> videos = new ArrayList<>();
            for (CourseVideo courseVideo:courseVideos) {
                videos.add(videoMapper.selectByPrimaryKey(courseVideo.getVideoId()));
            }
            return new PageInfo<>(videos);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String addVideoToCourse(Long videoId, Long courseId) {
        try {
            CourseVideo courseVideo = new CourseVideo();
            courseVideo.setCourseId(courseId);
            courseVideo.setVideoId(videoId);
            courseVideoMapper.insert(courseVideo);
            return "添加视频成功";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "添加视频失败";
    }

    @Override
    public String deleteVideoFromCourse(Long videoId, Long courseId) {

        try {
            CourseVideoExample courseVideoExample = new CourseVideoExample();
            courseVideoExample.createCriteria().andCourseIdEqualTo(courseId).andVideoIdEqualTo(videoId);
            List<CourseVideo> courseVideos = courseVideoMapper.selectByExample(courseVideoExample);
            for (CourseVideo courseVideo:courseVideos) {
                courseVideoMapper.deleteByPrimaryKey(courseVideo.getId());
            }
            return "删除视频成功";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "删除视频失败";
    }
}
