package com.technical.comm.entity;

import java.io.Serializable;

/**
 * @author gaogba
 * @apiNote 手动分页数据信息
 */
public class PageInfoBean<T> implements Serializable {

    public PageInfoBean() {
    }

    public PageInfoBean(int total, int size, int current) {
        this.total = total;
        this.size = size;
        this.current = current;
        this.pages = total % size == 0 ? total / size : total / size + 1;
    }

    /**
     * 总共多少条
     */
    private int total;
    /**
     * 每页展示多少
     */
    private int size;
    /**
     * 当前第几页
     */
    private int current;
    /**
     * 共有多少页
     */
    private int pages;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
