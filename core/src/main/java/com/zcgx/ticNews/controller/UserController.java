package com.zcgx.ticNews.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.po.User;
import com.zcgx.ticNews.service.UserService;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "userInfo", value = "小程序用户信息", required = true, dataType = "User", example = "{\"summary\":\"summaryTest\",\"voteNegtiveCount\":0,\"source\":\"resourceTest\",\"votePositiveCount\":1,\"title\":\"titleTest\",\"votePositiveName\":\"赞同\",\"content\":\"contentTest\",\"url\":\"http://www.baidu.com\",\"voteNegtiveName\":\"不赞同\",\"tags\":[\"检测\",\"认证\"]}")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<String> saveUser(@RequestBody JSONObject userInfo){
        try {
            User user = new User();
            user.setOpenid(userInfo.getString("openid"));
            user.setSubscribe(userInfo.getInteger("subscribe"));
            user.setRemark(userInfo.getString("remark"));
            user.setUnionid(userInfo.getString("unionid"));
            user.setMpOpenid(userInfo.getString("mpOpenid"));
            user.setSubscribeNewspaper(userInfo.getInteger("subscribeNewspaper"));
            user.setUserSource(userInfo.getInteger("userSource"));
            userService.saveUser(user);
            return Response.ok("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("新增用户失败! " + e);
            return Response.error("新增用户失败! " + e);
        }
    }

}
