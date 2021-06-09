package com.technical.point.basepoint.mapper;

import com.technical.point.basepoint.entity.UserInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author gaogba
 * @Date 2020/9/4 20:40
 * @Version 1.0
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<UserInfoPo>{
}
