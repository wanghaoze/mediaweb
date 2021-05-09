package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Image;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface ImageService {
    public String addImage(Image image);
    public Image getImageInfoById(Long imageID);
    public PageInfo<Image> getImageByName(String imName, Integer pageNo, Integer pageSize);
    public String deleteImageById(Long imageID);
    public String updateImageById(Long imageID, Image image);
}
