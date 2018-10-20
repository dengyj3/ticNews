package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.dao.UserDao;
import com.zcgx.ticNews.po.User;
import com.zcgx.ticNews.service.QuartzService;
import com.zcgx.ticNews.service.WeChatCoreService;
import com.zcgx.ticNews.util.WeixinUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuartzServiceImpl implements QuartzService {
    final static Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);
    @Autowired
    WeChatCoreService weChatCoreService;

    @Autowired
    UserDao userDao;

    @Value("${wx.push}")
    boolean isPush; //推送消息开关, 正式上线时设置为true
    @Value("${wx.templateId}")
    String templateId; //推送消息的模板Id

    @Override
    @Scheduled(cron = "0 0 10 * * MON-FRI")
    public void pushMessage() {
        if (isPush){
            logger.info("push message switch is true");
            List<User> users = userDao.findBySubscribeNewspaper();
            String accessToken = weChatCoreService.getAccessToken();
            if (StringUtils.isBlank(accessToken)){
                logger.error("access_token is null. access_token is : " + accessToken);
                return;
            }
            users.parallelStream().forEach(user -> {
                String openid = user.getOpenid();
                if (StringUtils.isBlank(openid)){
                    openid = user.getMpOpenid();
                }
                String tmpTxt = WeixinUtil.toTemplateMsgText(templateId,openid);
                logger.info("send message is : " + tmpTxt);
                String sendTemplateMsg = WeixinUtil.sendTemplateMsg(accessToken, tmpTxt);
                logger.info("send message return is : " + sendTemplateMsg);
            });
        }
    }
}
