package com.global.format.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class DefaultPageResp<T> implements IPageResp<T> {
    private static final long serialVersionUID = -8373879024289174711L;
    private long pageNum;
    private long pageSize;
    private long pageCount;
    private List<T> pageRecords = Collections.emptyList();
    private long totalCount;

    public DefaultPageResp() {
    }

    @Override
    public String toString() {
        return "DefaultPageResp(super=" + super.toString() + ", pageCount=" + this.pageCount + ", pageRecords=" + this.pageRecords + ", totalCount=" + this.totalCount + ")";
    }

    public static <T> IPageResp<T> buildPageResp(IPageReq pageReq, long totalCount, List<T> pageRecords) {
        return buildPageResp(pageReq.getPageNum(), pageReq.getPageSize(), totalCount, pageRecords);
    }

    public static <T> IPageResp<T> buildPageResp(long pageNum, long pageSize, long totalCount, List<T> pageRecords) {
        DefaultPageResp<T> defaultPageResp = new DefaultPageResp();
        defaultPageResp.setPageNum(pageNum);
        defaultPageResp.setPageSize(pageSize);
        defaultPageResp.setPageCount((totalCount + pageSize - 1L) / pageSize);
        defaultPageResp.setPageRecords(pageRecords);
        defaultPageResp.setTotalCount(totalCount);
        return defaultPageResp;
    }
}
