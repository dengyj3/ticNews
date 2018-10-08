package com.zcgx.ticNews.util;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.service.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;

public class WeixinUtil {
    private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
    @Autowired
    AccessTokenService accessTokenService;

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new MyX509TrustManager()};
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(ssf);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpsURLConnection.connect();
            }
            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpsURLConnection.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String sendTemplateMsg(String accessToken, String jsonData) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        JSONObject jsonObject = httpRequest(requestUrl, "GET", jsonData);
        if (jsonObject != null) {
            String errcode = jsonObject.getString("errcode");
            if ("0".equals(errcode)) {
                logger.info("发送模板消息成功！");
            } else {
                logger.error(errcode);
            }
            return errcode;
        }
        return null;
    }

    public static String toTemplateMsgText(String templateId, String openid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);
        jsonObject.put("template_id", templateId);
        // TODO: 替换url
        jsonObject.put("url","https://blog.csdn.net/rwb0123/article/details/50424339");
        jsonObject.put("topcolor","#FF0000");
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "Tic学院 每日早报");
        first.put("color", "#173177");
        data.put("first", first);
        JSONObject mesageType = new JSONObject();
        mesageType.put("value", "早报更新通知");
        mesageType.put("color", "#173177");
        data.put("keyword1", mesageType);

        JSONObject sendState = new JSONObject();
        sendState.put("value", "已发送");
        sendState.put("color", "#173177");
        data.put("keyword2", sendState);

        JSONObject time = new JSONObject();
        time.put("value", DateUtils.getDateYMD(new Date()));
        time.put("color", "#173177");
        data.put("keyword3", time);

        JSONObject sendObject = new JSONObject();
        sendObject.put("value", "订阅用户");
        sendObject.put("color", "#173177");
        data.put("keyword4", sendObject);

        JSONObject remark = new JSONObject();
        remark.put("value", "请点击查看");
        remark.put("color", "#173177");
        data.put("remark", remark);

        jsonObject.put("data", data);
        String jsonText = jsonObject.toJSONString();
        return jsonText;
    }
}
