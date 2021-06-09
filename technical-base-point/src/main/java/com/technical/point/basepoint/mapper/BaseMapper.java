package com.technical.point.basepoint.mapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description BaseMapper处理器
 * @Author gaogba
 * @Date 2020/9/4 20:38
 * @Version 1.0
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
