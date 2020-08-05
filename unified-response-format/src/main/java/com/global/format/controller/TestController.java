package com.global.format.controller;

import com.global.format.entity.UserAction;
import com.global.format.response.DefaultHttpPageResult;
import com.global.format.response.IHttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/testGet", produces = "application/json")
    public IHttpResult<String> get() throws Exception{
        return DefaultHttpPageResult.successWithData("success");
    }

    @GetMapping(value = "/testString", produces = "application/json")
    public String testString() throws Exception{
        return "success";
    }

    @GetMapping(value = "/testUser", produces = "application/json")
    public IHttpResult<UserAction> getUserInfo() throws Exception{
        UserAction userAction = new UserAction().setId(322).setCreate_time(new Date())
                .setEventData("testData").setEventName("testEvent").setUpdate_time(new Date()).setUserId(232);
        return DefaultHttpPageResult.successWithData(userAction);
    }

    @GetMapping(value = "/testUserThrow", produces = "application/json")
    public IHttpResult<UserAction> testUserThrow() throws Exception{
        throw new RuntimeException("出错了");
    }

    @GetMapping(value = "/testSimpleUser", produces = "application/json")
    public UserAction getSimpleUser() throws Exception{
        UserAction userAction = new UserAction().setId(322).setCreate_time(new Date())
                .setEventData("testData").setEventName("testEvent").setUpdate_time(new Date()).setUserId(232);
        return userAction;
    }
}
