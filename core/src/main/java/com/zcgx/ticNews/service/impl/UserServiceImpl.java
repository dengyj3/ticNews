package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.dao.UserDao;
import com.zcgx.ticNews.message.util.MessageModelUtil;
import com.zcgx.ticNews.po.User;
import com.zcgx.ticNews.service.AccessTokenService;
import com.zcgx.ticNews.service.UserService;
import com.zcgx.ticNews.service.WeChatCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDao userDao;
    @Autowired
    private WeChatCoreService weChatCoreService;
    @Override
    public void saveUser(User user) {
        String unionid = user.getUnionid();
        if (isExist(unionid)){
            user.setId(userDao.findByUnionId(unionid).getId());
        }
        userDao.saveAndFlush(user);
    }

    @Override
    public List<User> findByScribeNewspaper() {
        return userDao.findBySubscribeNewspaper();
    }

    @Override
    public boolean isExist(String unionid) {
        User user = findByUnionId(unionid);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public User findByUnionId(String unionid) {
        return userDao.findByUnionId(unionid);
    }

    @Override
    public boolean checkIsScribe(String openid) {
        return MessageModelUtil.isAlreadyBinding(weChatCoreService.getAccessToken(),openid);
    }
}
