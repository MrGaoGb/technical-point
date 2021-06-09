package com.technical.comm.annotation;

import java.lang.annotation.*;

/**
 * @Description 导出Excel标识注解
 * @Author gaogba
 * @Date 2020/10/22 20:27
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ExportExcel {

    /**
     * 列名说明
     *
     * @return
     */
    String colName();

    /**
     * 顺序
     *
     * @return
     */
    int order();
}
