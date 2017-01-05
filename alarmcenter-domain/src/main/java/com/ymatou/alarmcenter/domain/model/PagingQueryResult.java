package com.ymatou.alarmcenter.domain.model;

/**
 * Created by zhangxiaoming on 2016/12/30.
 */

import java.util.List;

/**
 * Created by zhangxiaoming on 2016/10/18.
 * 分页查询返回值
 */
public class PagingQueryResult<T> {
    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 总计录数
     */
    private Long totalRecords;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalPages(int pageSize) {
        return getTotalRecords() % pageSize > 0 ? (getTotalRecords() / pageSize) + 1 : getTotalRecords() / pageSize;
    }
}