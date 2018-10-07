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

}
