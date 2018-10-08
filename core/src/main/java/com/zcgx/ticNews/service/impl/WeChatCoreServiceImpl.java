package com.zcgx.ticNews.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.message.resp.TextMessage;
import com.zcgx.ticNews.message.util.MessageModelUtil;
import com.zcgx.ticNews.po.AccessToken;
import com.zcgx.ticNews.po.User;
import com.zcgx.ticNews.service.AccessTokenService;
import com.zcgx.ticNews.service.UserService;
import com.zcgx.ticNews.service.WeChatCoreService;
import com.zcgx.ticNews.util.DateUtils;
import com.zcgx.ticNews.util.HttpBaseUtils;
import com.zcgx.ticNews.message.util.MessageUtil;
import com.zcgx.ticNews.util.WeixinUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service("weChatCoreService")
public class WeChatCoreServiceImpl implements WeChatCoreService {
    private static Logger logger = LoggerFactory.getLogger(WeChatCoreServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Value("${wx.appId}")
    private String appId = "";

    @Value("${wx.appKey}")
    private String appKey = "";

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        String respContent = "请求处理异常，请稍候重试！";
        TextMessage textMessage = new TextMessage();
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {    // 图片消息
                respContent = "您发送的是图片消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }	else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { 					// 地理位置消息
                respContent = "您发送的是地理位置消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }	else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { 					// 链接消息
                respContent = "您发送的是链接消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }	else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { 					// 事件推送(当用户主动点击菜单，或者扫面二维码等事件)
                // 事件类型
                String  eventType =requestMap.get("Event");
                System.out.println("eventType------>"+eventType);
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){// 用户关注时保存用户信息
                    getUserInfo(getAccessToken(), fromUserName);// 保存用户信息
//                    respMessage = MessageModelUtil.followResponseMessageModel(textMessage);
                }else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消关注
                    getUserInfo(getAccessToken(), fromUserName);// 更新用户信息
//                    MessageModelUtil.cancelAttention(getAccessToken(),fromUserName);
            	}else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {              	// 扫描带参数二维码
                    logger.info("扫描带参数二维码");
                }else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {             	// 上报地理位置
                    logger.info("上报地理位置");
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单（点击菜单拉取消息）
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey=requestMap.get("EventKey");
                    logger.info("eventKey------->"+eventKey);
                    getUserInfo(getAccessToken(), fromUserName);// 更新用户信息
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {// 自定义菜单（(自定义菜单URl视图)）
                    logger.info("处理自定义菜单URI视图");
                    getUserInfo(getAccessToken(), fromUserName);// 更新用户信息
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统出错");
            respMessage = null;
        } finally{
            if (null == respMessage) {
                respMessage = MessageModelUtil.systemErrorResponseMessageModel(textMessage);
            }
            return respMessage;
        }
    }

    @Override
    public String getAccessToken() {
        AccessToken accessToken = accessTokenService.selectByPrimaryKey(1);
        if (accessToken != null){
            logger.info("CreateTime: " + accessToken.getCreateDate() + " NOW: "+new Date().getTime() +"\n"+ Long.parseLong(accessToken.getExpiresin()));
        }
        if (accessToken == null || accessToken.getCreateDate().getTime() + Long.parseLong(accessToken.getExpiresin()) * 1000 < new Date().getTime()){
            if (StringUtils.isBlank(appId)){
                logger.error("appId maybe null. receive appid is : " + appId);
                return null;
            }
            if (StringUtils.isBlank(appKey)){
                logger.error("appKey maybe null. receive appKey is : " + appKey);
                return null;
            }
            String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appKey;
            JSONObject jsonObject = WeixinUtil.httpRequest(accessTokenUrl, "GET", null);
            if (jsonObject.getString("errorcode") == null){
                accessToken = new AccessToken();
                accessToken.setId(1);
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresin(jsonObject.getString("expires_in"));
                accessToken.setCreateDate(new Date());
            }
            if (accessToken == null){
                accessTokenService.insert(accessToken);
            }
            accessTokenService.updateByPrimaryKey(accessToken);
        }
        return accessToken.getAccessToken();
    }

    @Override
    public String getUserInfo(String accessToken, String openid) {
        if (StringUtils.isBlank(accessToken)){
            logger.error("access_token is null");
            return null;
        }
        if (StringUtils.isBlank(openid)){
            logger.error("openid is null");
            return null;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
        String result = HttpBaseUtils.getRequestJson(restTemplate, url, null);
        if (StringUtils.isNotBlank(result)){
            JSONObject jsonObject = (JSONObject) JSON.parse(result);
            User user = new User();
            user.setOpenid(openid);
            user.setSubscribe(jsonObject.getInteger("subscribe"));
            user.setRemark(jsonObject.getString("remark"));
            user.setUnionid(jsonObject.getString("unionid"));
            userService.saveUser(user);
            return result;
        }
        return null;
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {

    }

}
