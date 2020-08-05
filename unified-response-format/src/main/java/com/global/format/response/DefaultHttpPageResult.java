package com.global.format.response;

import com.global.format.response.constant.PlatformCode;

import java.util.List;

public class DefaultHttpPageResult<T> extends DefaultHttpResult<T> {
    public DefaultHttpPageResult() {
    }

    public static <T> IHttpResult<IPageResp<T>> successWithPageData(long pageNum, long pageSize, long totalCount, List<T> pageRecords) {
        DefaultHttpResult<IPageResp<T>> httpResult = new DefaultHttpResult();
        httpResult.setCode(PlatformCode.SUCCESS.getCode());
        httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
        httpResult.setData(DefaultPageResp.buildPageResp(pageNum, pageSize, totalCount, pageRecords));
        return httpResult;
    }

    public static <T> IHttpResult<IPageResp<T>> successWithPageData(IPageResp<T> pageResp) {
        DefaultHttpResult<IPageResp<T>> httpResult = new DefaultHttpResult();
        httpResult.setCode(PlatformCode.SUCCESS.getCode());
        httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
        httpResult.setData(DefaultPageResp.buildPageResp(pageResp.getPageNum(), pageResp.getPageSize(), pageResp.getTotalCount(), pageResp.getPageRecords()));
        return httpResult;
    }
}