package com.zcgx.ticNews.message.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.message.resp.Article;
import com.zcgx.ticNews.message.resp.NewsMessage;
import com.zcgx.ticNews.message.resp.TextMessage;
import com.zcgx.ticNews.util.DateUtils;
import com.zcgx.ticNews.util.WeixinUtil;

/**
 * @Description: 封装微信回复消息，各种回复消息对应不同的方法
 * @Create Date:
 * @Version: V1.00
 * @Author:
 */
public class MessageModelUtil {

    /**
     * @Description: 当系统出错时，默认回复的文本消息
     * @Parameters:
     * @Return: 系统出错回复的消息
     * @Create Date:
     * @Version: V1.00
     * @author:
     */
    public static String systemErrorResponseMessageModel(TextMessage textMessage) {

        // 回复文本消息
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//        textMessage.setFuncFlag(0);
        textMessage.setContent("系统出错啦，请稍后再试");
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * @Description: 用户关注时发送的图文消息
     * @Parameters: MessageModelUtil
     * @Return: 用户关注后发送的提示绑定用户的图文消息
     * @Create Date:
     * @Version: V1.00
     * @author:
     */
    public static String followResponseMessageModel(TextMessage textMessage) {

        // 关注时发送图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(textMessage.getToUserName());
        newsMessage.setFromUserName(textMessage.getFromUserName());
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//        newsMessage.setFuncFlag(0);

        // 图文消息
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        // 设置图文消息的标题
        String string = "尊敬的用户，欢迎关注TIC学院。";
        article.setTitle(string);
        article.setPicUrl("ticlogo.png");//TODO: zcgx logo
        article.setUrl("/WeixinParticipantFouce");//TODO: 订阅早报的url
        article.setDescription("点击进入...");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.newsMessageToXml(newsMessage);
    }
    /**
     * @Description: 用户关注时发送的文本消息
     * @Parameters: MessageModelUtil
     * @Return: 用户关注后发送的提示绑定用户的文本消息
     * @Create Date:
     * @Version: V1.00
     * @author:
     */
    public static String followResponseMessageModel1(TextMessage textMessage) {

        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setMsgId(0);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent("欢迎关注TIC学院。点击获取每天3分钟的行业资讯速览。\n<a href=\"https://ticnews.labyun.cn/daily?date="+ DateUtils.getDateYMD1(new Date()) +"\">欢迎订阅检报</a>");
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 获取用户信息
     * @param token
     * @param openid
     * @return
     */
    public static JSONObject getUserInfo(String token, String openid){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
        JSONObject result = WeixinUtil.httpRequest(url,"GET", null);
        return result;
    }

}
