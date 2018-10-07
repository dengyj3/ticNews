package com.zcgx.ticNews.service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatCoreService {
    String processRequest(HttpServletRequest request);

    String getAccessToken();

    /**
     * 获取公众号的已关注用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    String getUserInfo(String accessToken, String openid);
}
