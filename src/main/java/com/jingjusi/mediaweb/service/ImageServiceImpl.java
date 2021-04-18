package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Image;
import com.jingjusi.mediaweb.common.domain.ImageExample;
import com.jingjusi.mediaweb.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Resource
    @Autowired
    ImageMapper imageMapper;

    @Override
    public String addImage(Image image) {
        try {
            imageMapper.insert(image);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "添加失败";
    }

    @Override
    public Image getImageInfoById(Long imageID) {
        try {
            return imageMapper.selectByPrimaryKey(imageID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PageInfo<Image> getImageByName(String imName, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public String deleteImageById(Long imageID) {
        return null;
    }

    @Override
    public String updateImageById(Long imageID, Image image) {
        return null;
    }

    @Override
    public PageInfo<Image> getImagesByVideoId(Long videoId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andVideoIdEqualTo(videoId);
        List<Image> images = new ArrayList<>(imageMapper.selectByExample(imageExample));
        if (images.isEmpty())
            return null;
        return new PageInfo<>(images);
    }

}
