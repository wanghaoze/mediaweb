package com.jingjusi.mediaweb.common.domain;

import java.util.Date;

public class Tablet {
    private Long id;

    private String location;

    private Integer rowNum;

    private Integer colNum;

    private Integer height;

    private String donors;

    private String tabletType;

    private Date createTime;

    private Date expireTime;

    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDonors() {
        return donors;
    }

    public void setDonors(String donors) {
        this.donors = donors == null ? null : donors.trim();
    }

    public String getTabletType() {
        return tabletType;
    }

    public void setTabletType(String tabletType) {
        this.tabletType = tabletType == null ? null : tabletType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}