package com.example.backend.service;

import com.example.backend.domain.UndoLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author reine
* @description 针对表【t_undo_log】的数据库操作Service
* @createDate 2023-03-13 13:53:37
*/
public interface UndoLogService extends IService<UndoLog> {

    /**
     * 根据请求的ip地址，对对应ip地址的用户操作进行撤回
     *
     * @param ip
     * @return
     */
    Boolean undo(String ip);

    /**
     * 用于延迟注入userService
     * @param userService
     */
    void setUserService(UserService userService);
}
