package com.zcgx.ticNews.service;

import com.zcgx.ticNews.po.User;

import java.util.List;

public interface UserService {
    /**
     * 增加或更新用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 查找所有订阅用户
     * @return
     */
    List<User> findByScribeNewspaper();

    /**
     * 根据unionid判断用户表中用户是否存在
     */
    boolean isExist(String unionid);

    /**
     * 根据unionid返回用户信息
     * @param unionid
     * @return
     */
    User findByUnionId(String unionid);

    /**
     * 检查用户是否订阅
     * @param openid
     * @return
     */
    boolean checkIsScribe(String openid);

}
