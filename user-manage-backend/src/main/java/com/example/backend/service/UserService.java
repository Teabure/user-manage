package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.domain.User;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author reine
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-03-13 11:26:43
*/
public interface UserService extends IService<User> {

    /**
     * 根据页码，页码显示条数，查询条件查询用户数据
     * @param query
     * @param current
     * @param size
     * @return
     */
    Page<User> pageList(@Nullable String query, Integer current, Integer size);


    /**
     * 查询用户
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * 添加用户
     * @param user
     * @param request
     * @return
     */
    Boolean addUser(User user, HttpServletRequest request);

    /**
     * 更新用户
     *
     * @param user
     * @param request
     * @return
     */
    Boolean updateUser(User user, HttpServletRequest request);

    /**
     * 删除用户
     *
     * @param id
     * @param request
     * @return
     */
    Boolean removeUser(Long id, HttpServletRequest request);

    /**
     * 恢复被删除的用户
     * @param id
     * @return
     */
    Boolean restoreUser(Long id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Boolean deleteUser(Long id);

    /**
     * 批量删除用户
     * @param ids
     * @param request
     * @return
     */
    Boolean removeUserBatch(List<String> ids, HttpServletRequest request);
}
