package com.github.zhzhair.main.rabbitproducer.controller;

import com.github.zhzhair.main.common.base.dto.BaseResponse;
import com.github.zhzhair.main.common.util.GeneratorUtil;
import com.github.zhzhair.main.common.util.WebUtil;
import com.github.zhzhair.main.rabbitproducer.dto.request.UserMapperRequest;
import com.github.zhzhair.main.rabbitproducer.dto.response.UserLoginInfo;
import com.github.zhzhair.main.rabbitproducer.service.UserMQService;
import com.github.zhzhair.main.rabbitproducer.asynctask.AsyncTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@CrossOrigin(origins = "*",allowedHeaders = "Content-Type,Access-Token,x-requested-with",allowCredentials = "true",maxAge = 3600,exposedHeaders = "content-type")
@RestController
@RequestMapping("rabbitMQ/user")
@Api(description = "rabbitMQ生产者 -- 用户")
public class UserController{

    @Resource
    private UserMQService userService;
    @Resource
    private AsyncTask asyncTask;

    @ApiOperation(value = "添加测试用户数据", notes = "添加测试用户数据，RabbitMQ方式异步添加登录历史记录")
    @RequestMapping(value = "/loginForRabbitMQTest1", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> loginForRabbitMQTest1(String userName,String ip) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        UserLoginInfo userLoginInfo = userService.login(userName, "123456", ip);
        if (userLoginInfo != null) {
            baseResponse.ok(userLoginInfo);
        } else {
            baseResponse.setCode(1);
            baseResponse.setMsg("用户名或密码不正确");
        }
        return baseResponse;
    }

    @ApiOperation(value = "添加测试用户数据", notes = "添加测试用户数据，RabbitMQ方式异步添加登录历史记录")
    @RequestMapping(value = "/loginForRabbitMQTest", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> loginForRabbitMQTest(HttpServletRequest req) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        String[] userNames = {"xiaoming","xiaohong","jingjing","xiaoqiang","xiaoli","tingting","xiaowang","laowang","zhaixinxin"};
        int rand = new Random().nextInt(userNames.length);
        UserLoginInfo userLoginInfo = userService.login(userNames[rand] + new Random().nextInt(1000000), "123456", WebUtil.getRemoteAddr(req));
        if (userLoginInfo != null) {
            baseResponse.ok(userLoginInfo);
        } else {
            baseResponse.setCode(1);
            baseResponse.setMsg("用户名或密码不正确");
        }
        return baseResponse;
    }

    @ApiOperation(value = "添加测试用户数据", notes = "添加测试用户数据，RabbitMQ方式异步添加注册历史记录")
    @RequestMapping(value = "/registerForRabbitMQTest", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> registerForRabbitMQTest(HttpServletRequest req) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
//        @Valid
        UserMapperRequest userMapperRequest = new UserMapperRequest();
        userMapperRequest.setIp(WebUtil.getRemoteAddr(req));
        userMapperRequest.setMobile(GeneratorUtil.getMobileStr());
        String[] userNames = {"xiaoming","xiaohong","jingjing","xiaoqiang","xiaoli","tingting","xiaowang","laowang","zhaixinxin"};
        int rand = new Random().nextInt(userNames.length);
        userMapperRequest.setUserName(userNames[rand] + new Random().nextInt(1000000));
        userMapperRequest.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        int channel = userService.registerChannel(userMapperRequest);
        if(channel == 0){
            UserMapperRequest userMapperRequest1 = userService.register(userMapperRequest);
            baseResponse.ok(userMapperRequest1);
            asyncTask.asyncMethod();//注册成功后，模拟如果邮箱不为空，就发送邮件
        }else if(channel == 1){
            baseResponse.setCode(1);
            baseResponse.setMsg("手机号已注册");
        }else{
            baseResponse.setCode(2);
            baseResponse.setMsg("用户名已注册");
        }
        return baseResponse;
    }
}
