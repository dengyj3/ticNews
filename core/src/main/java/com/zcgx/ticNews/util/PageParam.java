package com.zcgx.ticNews.util;

import java.util.Collections;
import java.util.List;

public class PageParam {
    private static final int DEFAULT_MAX_SIZE = 10000;
    private int pageSize = 20;
    private int pageNo = 1;
    private String sort;
    private boolean asc = false;

    public static int getDefaultMaxSize() {
        return DEFAULT_MAX_SIZE;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public int getFirstResult(){
        return (this.pageNo - 1) * this.pageSize;
    }

    public <T>List<T> getSubList(List<T> list){
        if (this.pageSize == -1){
            return list;
        }else {
            int fromIndex = this.getFirstResult();
            int toIndex = Math.min(list.size(), this.getFirstResult() + this.pageSize);
            return fromIndex > toIndex ? Collections.emptyList() : list.subList(fromIndex, toIndex);
        }
    }

    public int getOffset(){
        return this.pageNo < 1 ? 0 : (this.pageNo - 1) * this.pageSize;
    }
}
