package com.global.format.response;

import java.io.Serializable;

public interface IPageReq<T> extends Serializable {
    long getPageNum();

    T getPreviousPageLastRecord();

    long getPageSize();
}