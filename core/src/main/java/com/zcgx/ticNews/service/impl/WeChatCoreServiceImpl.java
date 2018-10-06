package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.message.resp.Article;
import com.zcgx.ticNews.message.resp.NewsMessage;
import com.zcgx.ticNews.message.resp.TextMessage;
import com.zcgx.ticNews.service.WeChatCoreService;
import com.zcgx.ticNews.util.HttpBaseUtils;
import com.zcgx.ticNews.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("weChatCoreService")
public class WeChatCoreServiceImpl implements WeChatCoreService {
    private static Logger logger = LoggerFactory.getLogger(WeChatCoreServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wx.appid:wx18227c539a8494aa}")
    private String appId;
    @Value("${wx.redirect_uri:http://rwyvgz.natappfree.cc/ticNews/article/queryArticle?pageNo=0&pageSize=10}")
    private String redirectUri = "";

    private String responseType = "code";

    //（snsapi_base ，不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo 弹出授权页面，可通过openid拿到昵称、性别、所在地。）
    @Value("{wx.scope:snsapi_base}")
    private String scope;
    @Value("{wx.secret:73ac773580b51790b33d76dd1704c4e4}")
    private String secret;

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        String respContent = "请求处理异常，请稍候重试！";
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("fromUserName");
            String toUserName = requestMap.get("toUserName");
            String msgType = requestMap.get("msgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);

            List<Article> articleList = new ArrayList<>();
            String content = requestMap.get("content");
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                if (isQqFace(content)){
                    respContent = content;
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }else {
                    switch (content){
                        case "1":{
                            StringBuffer buffer = new StringBuffer();
                            buffer.append("");
                            respContent = String.valueOf(buffer);
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCode() {
        /**
         * --------------------用户授权并获取code
         *
         * 参数说明
         * appid=APPID（公众号唯一标识）
         * redirect_uri=REDIRECT_URI（授权后重定向的回调链接地址）
         * response_type=code（返回类型，无需更改）
         * scope=SCOPE（snsapi_base ，不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo 弹出授权页面，可通过openid拿到昵称、性别、所在地。）
         * state=STATE（重定向后会带上state参数，开发者可以填写任意参数值）
         * #wechat_redirect（无需更改）
         *
         * 地址实例（虽是测试号，但我还是隐藏部分信息）红色字体需要根据实际更改。
         * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe5809c42e6c00d22&redirect_uri=http://dingcanphp.applinzi.com/getUserInfo.php&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
         *
         * 返回结果（code的值，不一定是显示在浏览器界面上的，具体看你的redirect_uri中的文件）
         * 061h4k8Z1G7AhY0025bZ1nbh8Z1h4k8Q
         */
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUri+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        logger.info("url is : " + url);
        String result = HttpBaseUtils.getRequestJson(restTemplate,url, null);
        return result;
    }

    @Override
    public String getAccessToken(String code) {
        /**
         * -----------------使用code换取access_token
         *
         * 参数说明
         * appid=APPID（公众号唯一标识）
         * secret=SECRET（公众号的appsecret）
         * code=CODE（第一步获取的code参数）
         * grant_type=authorization_code（无需更改）
         *
         * 地址实例（虽是测试号，但我还是隐藏部分信息）红色字体需要根据实际更改。
         * https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe5809c42e6c00d22&secret=5444ba1b31666f6052e9c703f906368b&code=061h4k8Z1G7AhY0025bZ1nbh8Z1h4k8Q&grant_type=authorization_code
         *
         * 返回结果（json格式数据）
         * {
         * "access_token": "e1nYJFpZuehfQH1buzHFZLb7onqs_wT1cudSdy9HRlnaMXFtFpRMNFOA0euK6UxPcItrSNbAQVcXDdthbLJYX0MdH1p7-tkZSKuGqBCxVc0",
         * "expires_in": 7200,
         * "refresh_token": "0iVsXn4O1rBCASbO7hx8VNVUVFM1RP2Q4xS0giegd4jlIsJYOjTJNZ0b4Dsh_xcoB02ZZ3bt0WH0a47LvjIEPjWUnESJCZyl6EtY_xYZdVs",
         * "openid": "o47Fa0mp9SRTf3eiKmqWm69BjG_8",
         * "scope": "snsapi_userinfo"
         * }
         *
         * 结果解释
         * access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
         * expires_in	access_token接口调用凭证超时时间，单位（秒）
         * refresh_token	用户刷新access_token
         * openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
         * scope	用户授权的作用域，使用逗号（,）分隔
         */
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        String result = HttpBaseUtils.getRequestJson(restTemplate,url, null);
        return result;
    }

    @Override
    public String getUserInfo(String accessToken, String openId) {
        /**
         * 参数说明
         * access_token=ACCESS_TOKEN（第2步获取的access_token参数，此access_token与基础支持的access_token不同）
         * openid=OPENID（第2步获取的openid参数）
         * langlang=zh_CN	返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
         *
         * 地址实例（虽是测试号，但我还是隐藏部分信息）红色字体需要根据实际更改。
         * https://api.weixin.qq.com/sns/userinfo?access_token=e1nYJFpZuehfQH1buzHFZLb7onqs_wT1cudSdy9HRlnaMXFtFpRMNFOA0euK6UxPcItrSNbAQVcXDdthbLJYX0MdH1p7-tkZSKuGqBCxVc0&openid=o47Fa0mp9SRTf3eiKmqWm69BjG_8&lang=zh_CN
         *
         * 返回结果（json格式数据）
         * {
         * "openid": "o47Fa0mp9SRTf3eiKmqWm69BjG_8",
         * "nickname": "齐齐",
         * "sex": 0,
         * "language": "zh_CN",
         * "city": "Shaoxing",
         * "province": "Zhejiang",
         * "country": "CN",
         * "headimgurl": "http://wx.qlogo.cn/mmhead/Q3auHgzwzM6kqfcibzzVc8MDGBch53mIgJjWrbKSwkBnzcsWBOMOGlg/0",
         * "privilege": []
         * }
         *
         * 结果解释
         * openid	用户的唯一标识
         * nickname	用户昵称
         * sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
         * province	用户个人资料填写的省份
         * city	普通用户个人资料填写的城市
         * country	国家，如中国为CN
         * headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
         * privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
         */
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        String result = HttpBaseUtils.getRequestJson(restTemplate, url, null);
        return result;
    }

    public static boolean isQqFace(String content){
        boolean result = false;
        // TODO: 增加表情的正则表达式
        String qqFaceRegex = "/::\\)";
        Pattern p = Pattern.compile(qqFaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()){
            result = true;
        }
        return result;
    }
}
