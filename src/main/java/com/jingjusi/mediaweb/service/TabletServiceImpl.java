package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Tablet;
import com.jingjusi.mediaweb.common.domain.TabletExample;
import com.jingjusi.mediaweb.mapper.TabletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TabletServiceImpl implements TabletService{
    @Autowired
    TabletMapper tabletMapper;
    @Override
    public String addTablet(Tablet tablet) {
        try {
            tabletMapper.insert(tablet);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String deleteTablet(Long id) {
        try {
            tabletMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public String updateTablet(Long id, Tablet tablet) {
        try {
            TabletExample example = new TabletExample();
            example.createCriteria().andIdEqualTo(id);
            tabletMapper.updateByExampleSelective(tablet,example);
            return "更新成功";
        } catch (Exception e) {
            System.out.println(e);
            return "更新失败";
        }
    }

    @Override
    public PageInfo<Tablet> findTabletByDonors(String donors, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo,pageSize);
            TabletExample example = new TabletExample();
            example.createCriteria().andDonorsLike("%"+donors+"%");
            List<Tablet> tablets = new ArrayList<>(tabletMapper.selectByExample(example));
            return new PageInfo<>(tablets);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public PageInfo<Tablet> getTabletsByExample(Tablet tablet, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo,pageSize);
            TabletExample tabletExample = new TabletExample();
            if (tablet.getDistribution()!=null)
                tabletExample.createCriteria()
                        .andDistributionEqualTo(tablet.getDistribution())
                        .andRowNumEqualTo(tablet.getRowNum())
                        .andColNumEqualTo(tablet.getColNum());
            else if (tablet.getDonors()!=null)
                tabletExample.createCriteria().andDonorsLike(tablet.getDonors());
            else if (tablet.getContacts()!=null)
                tabletExample.createCriteria().andContactsEqualTo(tablet.getContacts());
            else tabletExample.createCriteria().andDonorsLike("%"+"%");
            List<Tablet> tables = new ArrayList<>(tabletMapper.selectByExample(tabletExample));
            return new PageInfo<>(tables);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
