package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Tablet;
import com.jingjusi.mediaweb.common.domain.TabletExample;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface TabletService {
    public String addTablet(Tablet tablet);
    public String deleteTablet(Long id);
    public String updateTablet(Long id,Tablet tablet);
    public PageInfo<Tablet> findTabletByDonors(String donors, Integer pageNo, Integer pageSize);
    public PageInfo<Tablet> getTabletsByExample(Tablet tablet, Integer pageNo, Integer pageSize);
}
