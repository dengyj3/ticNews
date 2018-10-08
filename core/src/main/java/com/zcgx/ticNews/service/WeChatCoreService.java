package com.zcgx.ticNews.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WeChatCoreService {
    String processRequest(HttpServletRequest request);

    /**
     * 获取access_token的接口地址（GET） 限200（次/天）
     * @return
     */
    String getAccessToken();

    /**
     * 获取公众号的已关注用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    String getUserInfo(String accessToken, String openid);

    void processRequest(HttpServletRequest request, HttpServletResponse response);
}
