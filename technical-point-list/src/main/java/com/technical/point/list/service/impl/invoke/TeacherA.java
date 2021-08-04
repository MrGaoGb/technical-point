package com.technical.point.list.service.impl.invoke;

import com.technical.point.list.service.ITeacher;

/**
 * @author: Mr.Gao
 * @date: 2021/8/4 20:57
 * @description:
 */
public class TeacherA implements ITeacher {
    @Override
    public String work() {
        return "教师A工作中......";
    }

    @Override
    public String study() {
        return "教师A学习中......";
    }
}
