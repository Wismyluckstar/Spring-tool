package com.global.format.response;

public class DefaultPageReq<T> implements IPageReq<T> {
    private static final long serialVersionUID = 4369981691717612605L;
    private long pageNum;
    private long pageSize;
    private T previousPageLastRecord;

    public DefaultPageReq() {
    }

    public long getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public T getPreviousPageLastRecord() {
        return this.previousPageLastRecord;
    }

    public void setPreviousPageLastRecord(T previousPageLastRecord) {
        this.previousPageLastRecord = previousPageLastRecord;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "DefaultPageReq(pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", previousPageLastRecord=" + this.previousPageLastRecord + ")";
    }

    public static <T> IPageReq<T> buildPageReq(long pageNum, T previousPageLastRecord, long pageSize) {
        DefaultPageReq<T> defaultPageReq = new DefaultPageReq();
        defaultPageReq.setPageNum(pageNum);
        defaultPageReq.setPreviousPageLastRecord(previousPageLastRecord);
        defaultPageReq.setPageSize(pageSize);
        return defaultPageReq;
    }
}
