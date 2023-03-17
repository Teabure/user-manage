package com.example.backend.controller;

import com.example.backend.service.UndoLogService;
import com.example.backend.utils.IpUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author reine
 */
@RestController
@CrossOrigin
public class UndoController {

    private UndoLogService undoLogService;

    public UndoController(UndoLogService undoLogService) {
        this.undoLogService = undoLogService;
    }

    @GetMapping("undo")
    public Boolean undo(HttpServletRequest request){
        String ip = IpUtils.getIp(request);
        return undoLogService.undo(ip);
    }






}
