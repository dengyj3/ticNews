package com.zcgx.ticNews.service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatCoreService {
    String processRequest(HttpServletRequest request);

    /**
     * 1. 用户授权并获取code
     * @return
     */
    String getCode();

    /**
     * 2. 根据code获取access_token
     * @return
     */
    String getAccessToken(String code);

    /**
     * 3. 根据access_token获取用户信息
     * @param accessToken
     * @return
     */
    String getUserInfo(String accessToken, String openId);
}
