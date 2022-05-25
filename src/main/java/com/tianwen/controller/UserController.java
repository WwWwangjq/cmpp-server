package com.tianwen.controller;

import com.tianwen.service.AbstractCmppService;
import com.tianwen.service.CmppConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wangjq
 * @Date: 2020年05月28日 14:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private List<AbstractCmppService> cmppConnectService;

    @PostMapping(value = "/add")
    public void add() {
        log.info("add user success ...");
    }
}
