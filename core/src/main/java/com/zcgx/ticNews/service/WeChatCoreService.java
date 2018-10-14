package com.zcgx.ticNews.service;

import com.zcgx.ticNews.params.WXACodeParams;

import javax.servlet.http.HttpServletRequest;

public interface WeChatCoreService {
    /**
     * 微信请求处理
     * @param request
     * @return
     */
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

    /**
     * 用户取消关注处理, 先判断用户是否绑定, 如果已经绑定则解除绑定
     * @param token
     * @param openid
     */
    void cancelAttention(String token, String openid);

    String decrypt(String code, String encryptedData, String iv);

    /**
     * 获取小程序access_token的接口地址（GET） 限200（次/天）
     * @return
     */
    String getXCXAccessToken();

    /**
     * 获取小程序的二维码
     * @param wxaCodeParams
     * @return
     */
    Object getWXACodeUnlimit(WXACodeParams wxaCodeParams);
}
