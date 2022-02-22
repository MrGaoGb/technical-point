package com.technical.point.list.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: Mr.Gao
 * @date: 2021/12/23 17:13
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        Student student = new Student("zhangsan", 22, "112233", date);
        Student student1 = new Student("zhangsan1", 23, "112244", date);
        Student student2 = new Student("zhangsan2", 24, "112255", date);
        Student student3 = new Student("zhangsan3", 25, "112266", date);
        final List<Student> students = Arrays.asList(student, student1, student2, student3);
        EasyExcel.write("D:\\workspace\\gaogba\\technical-point\\" + "export_" + System.currentTimeMillis() + ".xlsx", Student.class)
                .sheet("学生系统")
                .doWrite(students);


    }
}
