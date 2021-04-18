package com.jingjusi.mediaweb.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImageExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageNameIsNull() {
            addCriterion("image_name is null");
            return (Criteria) this;
        }

        public Criteria andImageNameIsNotNull() {
            addCriterion("image_name is not null");
            return (Criteria) this;
        }

        public Criteria andImageNameEqualTo(String value) {
            addCriterion("image_name =", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotEqualTo(String value) {
            addCriterion("image_name <>", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThan(String value) {
            addCriterion("image_name >", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThanOrEqualTo(String value) {
            addCriterion("image_name >=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThan(String value) {
            addCriterion("image_name <", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThanOrEqualTo(String value) {
            addCriterion("image_name <=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLike(String value) {
            addCriterion("image_name like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotLike(String value) {
            addCriterion("image_name not like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameIn(List<String> values) {
            addCriterion("image_name in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotIn(List<String> values) {
            addCriterion("image_name not in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameBetween(String value1, String value2) {
            addCriterion("image_name between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotBetween(String value1, String value2) {
            addCriterion("image_name not between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageSizeIsNull() {
            addCriterion("image_size is null");
            return (Criteria) this;
        }

        public Criteria andImageSizeIsNotNull() {
            addCriterion("image_size is not null");
            return (Criteria) this;
        }

        public Criteria andImageSizeEqualTo(String value) {
            addCriterion("image_size =", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotEqualTo(String value) {
            addCriterion("image_size <>", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeGreaterThan(String value) {
            addCriterion("image_size >", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeGreaterThanOrEqualTo(String value) {
            addCriterion("image_size >=", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeLessThan(String value) {
            addCriterion("image_size <", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeLessThanOrEqualTo(String value) {
            addCriterion("image_size <=", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeLike(String value) {
            addCriterion("image_size like", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotLike(String value) {
            addCriterion("image_size not like", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeIn(List<String> values) {
            addCriterion("image_size in", values, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotIn(List<String> values) {
            addCriterion("image_size not in", values, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeBetween(String value1, String value2) {
            addCriterion("image_size between", value1, value2, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotBetween(String value1, String value2) {
            addCriterion("image_size not between", value1, value2, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageWidthIsNull() {
            addCriterion("image_width is null");
            return (Criteria) this;
        }

        public Criteria andImageWidthIsNotNull() {
            addCriterion("image_width is not null");
            return (Criteria) this;
        }

        public Criteria andImageWidthEqualTo(Integer value) {
            addCriterion("image_width =", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthNotEqualTo(Integer value) {
            addCriterion("image_width <>", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthGreaterThan(Integer value) {
            addCriterion("image_width >", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("image_width >=", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthLessThan(Integer value) {
            addCriterion("image_width <", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthLessThanOrEqualTo(Integer value) {
            addCriterion("image_width <=", value, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthIn(List<Integer> values) {
            addCriterion("image_width in", values, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthNotIn(List<Integer> values) {
            addCriterion("image_width not in", values, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthBetween(Integer value1, Integer value2) {
            addCriterion("image_width between", value1, value2, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("image_width not between", value1, value2, "imageWidth");
            return (Criteria) this;
        }

        public Criteria andImageHeightIsNull() {
            addCriterion("image_height is null");
            return (Criteria) this;
        }

        public Criteria andImageHeightIsNotNull() {
            addCriterion("image_height is not null");
            return (Criteria) this;
        }

        public Criteria andImageHeightEqualTo(Integer value) {
            addCriterion("image_height =", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightNotEqualTo(Integer value) {
            addCriterion("image_height <>", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightGreaterThan(Integer value) {
            addCriterion("image_height >", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("image_height >=", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightLessThan(Integer value) {
            addCriterion("image_height <", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightLessThanOrEqualTo(Integer value) {
            addCriterion("image_height <=", value, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightIn(List<Integer> values) {
            addCriterion("image_height in", values, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightNotIn(List<Integer> values) {
            addCriterion("image_height not in", values, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightBetween(Integer value1, Integer value2) {
            addCriterion("image_height between", value1, value2, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andImageHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("image_height not between", value1, value2, "imageHeight");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNull() {
            addCriterion("upload_user is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNotNull() {
            addCriterion("upload_user is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserEqualTo(String value) {
            addCriterion("upload_user =", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotEqualTo(String value) {
            addCriterion("upload_user <>", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThan(String value) {
            addCriterion("upload_user >", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThanOrEqualTo(String value) {
            addCriterion("upload_user >=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThan(String value) {
            addCriterion("upload_user <", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThanOrEqualTo(String value) {
            addCriterion("upload_user <=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLike(String value) {
            addCriterion("upload_user like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotLike(String value) {
            addCriterion("upload_user not like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserIn(List<String> values) {
            addCriterion("upload_user in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotIn(List<String> values) {
            addCriterion("upload_user not in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserBetween(String value1, String value2) {
            addCriterion("upload_user between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotBetween(String value1, String value2) {
            addCriterion("upload_user not between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andLastRequestIsNull() {
            addCriterion("last_request is null");
            return (Criteria) this;
        }

        public Criteria andLastRequestIsNotNull() {
            addCriterion("last_request is not null");
            return (Criteria) this;
        }

        public Criteria andLastRequestEqualTo(Date value) {
            addCriterion("last_request =", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestNotEqualTo(Date value) {
            addCriterion("last_request <>", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestGreaterThan(Date value) {
            addCriterion("last_request >", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestGreaterThanOrEqualTo(Date value) {
            addCriterion("last_request >=", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestLessThan(Date value) {
            addCriterion("last_request <", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestLessThanOrEqualTo(Date value) {
            addCriterion("last_request <=", value, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestIn(List<Date> values) {
            addCriterion("last_request in", values, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestNotIn(List<Date> values) {
            addCriterion("last_request not in", values, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestBetween(Date value1, Date value2) {
            addCriterion("last_request between", value1, value2, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andLastRequestNotBetween(Date value1, Date value2) {
            addCriterion("last_request not between", value1, value2, "lastRequest");
            return (Criteria) this;
        }

        public Criteria andCntVisitIsNull() {
            addCriterion("cnt_visit is null");
            return (Criteria) this;
        }

        public Criteria andCntVisitIsNotNull() {
            addCriterion("cnt_visit is not null");
            return (Criteria) this;
        }

        public Criteria andCntVisitEqualTo(Long value) {
            addCriterion("cnt_visit =", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitNotEqualTo(Long value) {
            addCriterion("cnt_visit <>", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitGreaterThan(Long value) {
            addCriterion("cnt_visit >", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitGreaterThanOrEqualTo(Long value) {
            addCriterion("cnt_visit >=", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitLessThan(Long value) {
            addCriterion("cnt_visit <", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitLessThanOrEqualTo(Long value) {
            addCriterion("cnt_visit <=", value, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitIn(List<Long> values) {
            addCriterion("cnt_visit in", values, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitNotIn(List<Long> values) {
            addCriterion("cnt_visit not in", values, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitBetween(Long value1, Long value2) {
            addCriterion("cnt_visit between", value1, value2, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andCntVisitNotBetween(Long value1, Long value2) {
            addCriterion("cnt_visit not between", value1, value2, "cntVisit");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNull() {
            addCriterion("video_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNotNull() {
            addCriterion("video_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoIdEqualTo(Long value) {
            addCriterion("video_id =", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotEqualTo(Long value) {
            addCriterion("video_id <>", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThan(Long value) {
            addCriterion("video_id >", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("video_id >=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThan(Long value) {
            addCriterion("video_id <", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThanOrEqualTo(Long value) {
            addCriterion("video_id <=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIn(List<Long> values) {
            addCriterion("video_id in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotIn(List<Long> values) {
            addCriterion("video_id not in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdBetween(Long value1, Long value2) {
            addCriterion("video_id between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotBetween(Long value1, Long value2) {
            addCriterion("video_id not between", value1, value2, "videoId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}