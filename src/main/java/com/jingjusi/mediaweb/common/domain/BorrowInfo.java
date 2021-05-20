package com.jingjusi.mediaweb.common.domain;

import java.util.Date;

public class BorrowInfo {
    private Long id;

    private Long bookId;

    private Long userId;

    private String bookName;

    private String userName;

    private Date borrowTime;

    private Date deadline;

    private Date returnTime;

    private String operator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public BorrowInfo setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public BorrowInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}
