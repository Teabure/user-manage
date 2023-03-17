package com.example.backend.mapper;

import com.example.backend.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author reine
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-03-13 11:26:43
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    boolean restoreUser(Long id);

    boolean deleteUser(Long id);

}




