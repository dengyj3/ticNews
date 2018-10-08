package com.zcgx.ticNews.message.util;


/**
 * 微信消息处理，将关注回复消息等写成方法
 */

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.message.resp.Article;
import com.zcgx.ticNews.message.resp.NewsMessage;
import com.zcgx.ticNews.message.resp.TextMessage;

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
        newsMessage.setToUserName(textMessage.getFromUserName());
        newsMessage.setFromUserName(textMessage.getToUserName());
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
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.newsMessageToXml(newsMessage);
    }

    /**
     * @Description: 用户取消关注，先判断用户是否绑定，如果已经绑定则解除绑定
     * @Parameters: MessageModelUtil
     * @Return: void
     * @Create Date:
     * @Version: V1.00
     * @author:
     */
    public static void cancelAttention(String token, String fromUserName) {

        if (isAlreadyBinding(token,fromUserName)) {
            userUnbinding(fromUserName);
        } else {
            System.out.println("取消关注用户{}" + fromUserName + "还未绑定");
        }
    }


    public static boolean userUnbinding(String openid) {
        return false;
    }

    public static boolean isAlreadyBinding(String token, String openid) {
        Integer subscribe = null;
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openid + "&lang=zh_CN";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.parseObject(message);
            //System.out.println("JSON字符串："+demoJson);
            subscribe = demoJson.getInteger("subscribe");

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == subscribe ? true : false;
    }
}
