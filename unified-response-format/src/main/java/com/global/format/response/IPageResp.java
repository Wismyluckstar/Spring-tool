package com.global.format.response;

import java.io.Serializable;
import java.util.List;

public interface IPageResp<T> extends Serializable {
    long getPageNum();

    long getPageSize();

    long getPageCount();

    List<T> getPageRecords();

    long getTotalCount();
}