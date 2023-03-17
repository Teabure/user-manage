package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.UndoLog;
import com.example.backend.domain.User;
import com.example.backend.service.UndoLogService;
import com.example.backend.mapper.UndoLogMapper;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author reine
 * @description 针对表【t_undo_log】的数据库操作Service实现
 * @createDate 2023-03-13 13:53:37
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog>
        implements UndoLogService {

    private final ObjectMapper jacksonObjectMapper;

    private UserService userService;

    public UndoLogServiceImpl(ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean undo(String ip) {
        LambdaQueryWrapper<UndoLog> queryWrapper = Wrappers.lambdaQuery(UndoLog.class);
        queryWrapper.eq(UndoLog::getUserHost, ip);
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.lt(UndoLog::getCreateTime, now);
        queryWrapper.orderByDesc(UndoLog::getCreateTime);
        queryWrapper.last("limit 1");
        try {
            UndoLog undoLog = getOne(queryWrapper);
            Integer action = undoLog.getAction();
            switch (action) {
                // 新增
                case 1: {
                    Long rowId = undoLog.getRowId();
                    userService.deleteUser(rowId);
                    removeById(undoLog);
                    return true;
                }
                // 修改
                case 2: {
                    Object user = undoLog.getUser();
                    User value = jacksonObjectMapper.convertValue(user, User.class);
                    userService.updateById(value);
                    removeById(undoLog);
                    return true;
                }
                // 删除
                case 3: {
                    Long rowId = undoLog.getRowId();
                    userService.restoreUser(rowId);
                    removeById(undoLog);
                    return true;
                }
                // 批量删除
                case 4: {
                    List<String> ids = (List<String>) undoLog.getUser();
                    for (String id : ids) {
                        userService.restoreUser(Long.valueOf(id));
                    }
                    removeById(undoLog);
                    return true;
                }
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}




