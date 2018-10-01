package com.zcgx.ticNews.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageList<T> {
    private Integer pageNo = 1;
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Integer totalCount = 0;
    private List<T> list;

    public PageList(){

    }
    public static <T> PageList<T> emptyPageList(Integer pageSize){
        return new PageList(1, pageSize, 0, Collections.emptyList());
    }

    public static <T> PageList<T> emptyPageList(PageParam pageParam){
        List<T> list = new ArrayList<>();
        return new PageList(list, pageParam);
    }

    public PageList(Integer pageNo, Integer pageSize, Integer totalCount, List<T> list){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;
    }
    public PageList(List<T> list, PageParam pageParam){
        this.pageNo = pageParam.getPageNo();
        this.pageNum = this.pageNo;
        this.pageSize = pageParam.getPageSize();
        this.totalCount = list.size();
        this.list = pageParam.getSubList(list);
    }

    public Integer getPageNo(){
        return this.pageNo != null && this.pageNo > 1 ? this.pageNo : 1;
    }

    public Integer getPageNum(){
        return this.pageNum != null && this.pageNum > 1 ? this.pageNum : 1;
    }

    public Integer getPageSize(){
        return this.pageSize != null && this.pageSize > 1 ? this.pageSize : 1;
    }

    public Integer getTotalCount(){
        return this.totalCount != null && this.totalCount > 1 ? this.totalCount : 1;
    }

    public List<T> getList(){
        return this.list;
    }

    public Integer getTotalPageCount(){
        if (this.totalCount != null && this.totalCount > 0){
            return this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        }else {
            return 1;
        }
    }
}
