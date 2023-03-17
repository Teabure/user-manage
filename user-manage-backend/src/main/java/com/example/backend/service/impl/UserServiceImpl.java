package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.DictDistrict;
import com.example.backend.domain.UndoLog;
import com.example.backend.domain.User;
import com.example.backend.enums.Action;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.DictDistrictService;
import com.example.backend.service.UndoLogService;
import com.example.backend.service.UserService;
import com.example.backend.utils.IpUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author reine
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-03-13 11:26:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private UndoLogService undoLogService;


    private UserMapper userMapper;


    private DictDistrictService dictDistrictService;

    public UserServiceImpl(UndoLogService undoLogService, UserMapper userMapper, DictDistrictService dictDistrictService) {
        this.undoLogService = undoLogService;
        this.userMapper = userMapper;
        this.dictDistrictService = dictDistrictService;
    }

    @PostConstruct
    public void init() {
        undoLogService.setUserService(this);
    }

    @Override
    public Page<User> pageList(@Nullable String query, Integer current, Integer size) {
        Page<User> userPage = new Page<>(current, size);
        if (query == null) {
            page(userPage);
        } else {
            LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
            queryWrapper.like(User::getName, query);
            page(userPage, queryWrapper);
        }
        return userPage;
    }

    @Override
    public User getUser(Long id) {
        return getById(id);
    }

    @Override
    public Boolean addUser(User user, HttpServletRequest request) {
        boolean success = save(user);
        if (success) {
            UndoLog undoLog = new UndoLog();
            undoLog.setUserHost(IpUtils.getIp(request));
            undoLog.setUser(user);
            undoLog.setRowId(user.getId());
            undoLog.setAction(Action.INS.getCode());
            undoLog.setCreateTime(LocalDateTime.now());
            undoLogService.save(undoLog);
        }
        return success;
    }

    @Override
    public Boolean updateUser(User user, HttpServletRequest request) {
        User originUser = getById(user.getId());
        boolean success = updateById(user);
        if (success) {
            UndoLog undoLog = new UndoLog();
            undoLog.setUserHost(IpUtils.getIp(request));
            undoLog.setUser(originUser);
            undoLog.setRowId(user.getId());
            undoLog.setAction(Action.UPD.getCode());
            undoLog.setCreateTime(LocalDateTime.now());
            undoLogService.save(undoLog);
        }
        return success;
    }

    @Override
    public Boolean removeUser(Long id, HttpServletRequest request) {
        User user = getById(id);
        boolean success = removeById(id);
        if (success) {
            UndoLog undoLog = new UndoLog();
            undoLog.setUserHost(IpUtils.getIp(request));
            undoLog.setUser(user);
            undoLog.setRowId(id);
            undoLog.setAction(Action.DEL.getCode());
            undoLog.setCreateTime(LocalDateTime.now());
            undoLogService.save(undoLog);
        }
        return success;
    }

    @Override
    public Boolean restoreUser(Long id) {
        return userMapper.restoreUser(id);
    }

    @Override
    public Boolean deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Boolean removeUserBatch(List<String> ids, HttpServletRequest request) {
        boolean success = removeBatchByIds(ids);
        if (success){
            UndoLog undoLog = new UndoLog();
            undoLog.setUserHost(IpUtils.getIp(request));
            undoLog.setUser(ids);
            undoLog.setAction(Action.DEL_BATCH.getCode());
            undoLog.setCreateTime(LocalDateTime.now());
            undoLogService.save(undoLog);
        }
        return success;
    }
}




