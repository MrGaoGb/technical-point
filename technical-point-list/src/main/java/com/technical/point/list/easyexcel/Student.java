package com.technical.point.list.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Mr.Gao
 * @date: 2021/12/23 17:12
 * @description:
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {
    @ExcelProperty(value = "用户名称", order = 1)
    private String userName;
    @ExcelProperty(value = "年龄", order = 3)
    private Integer age;
    @ExcelProperty(value = "密码", order = 2)
    private String password;

    @ColumnWidth(value = 18)
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "推送时间", order = 4)
    private Date createTime;
}
