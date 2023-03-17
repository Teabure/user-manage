package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author reine
 */

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/list")
    public Page<User> list(@RequestBody Map map) {
        String query = (String) map.get("query");
        Integer current = (Integer) map.get("current");
        Integer size = (Integer) map.get("size");
        return userService.pageList(query, current, size);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("user")
    public Boolean addUser(@RequestBody  User user, HttpServletRequest request) {
        return userService.addUser(user,request);
    }

    @PutMapping("user")
    public Boolean updateUser(@RequestBody User user, HttpServletRequest request) {
        return userService.updateUser(user,request);
    }

    @DeleteMapping("user/{id}")
    public Boolean removeUser(@PathVariable Long id, HttpServletRequest request) {
        return userService.removeUser(id,request);
    }

    @PostMapping("user/remove/batch")
    public Boolean removeUserBatch(@RequestBody List<String> ids, HttpServletRequest request){
        return userService.removeUserBatch(ids,request);
    }

}
