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
@CrossOrigin
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "userInfo", value = "小程序用户信息", required = true, dataType = "User")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<User> saveUser(@RequestBody JSONObject userInfo){
        try {
            User user = new User();
            if (userInfo.containsKey("openid")) {
                user.setOpenid(userInfo.getString("openid"));
            }
            if (userInfo.containsKey("subscribe")) {
                user.setSubscribe(userInfo.getInteger("subscribe"));
            }
            if (userInfo.containsKey("remark")) {
                user.setRemark(userInfo.getString("remark"));
            }
            if (userInfo.containsKey("unionid")) {
                user.setUnionid(userInfo.getString("unionid"));
            }
            if (userInfo.containsKey("mpOpenid")) {
                user.setMpOpenid(userInfo.getString("mpOpenid"));
            }
            if (userInfo.containsKey("subscribeNewspaper")) {
                user.setSubscribeNewspaper(userInfo.getInteger("subscribeNewspaper"));
            }
            if (userInfo.containsKey("userSource")) {
                user.setUserSource(userInfo.getInteger("userSource"));
            }
            userService.saveUser(user);
            return Response.ok(user);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("新增用户失败! " + e);
            return Response.error("新增用户失败! " + e);
        }
    }

    @ApiOperation(value = "根据unionid查询用户信息")
    @ApiImplicitParam(name = "unionid", value = "根据unionid查询用户信息", required = true, dataType = "String")
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public Response<User> queryUser(@RequestParam(required = true) String unionid){
        try {
            User user = userService.findByUnionId(unionid);
            return Response.ok(user);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询用户失败! " + e);
            return Response.error("查询用户失败! " + e);
        }
    }
}
