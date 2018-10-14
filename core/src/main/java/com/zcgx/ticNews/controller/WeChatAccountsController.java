package com.zcgx.ticNews.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.params.WXACodeParams;
import com.zcgx.ticNews.service.WeChatCoreService;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WeChatAccountsController {
    private final static Logger logger = LoggerFactory.getLogger(WeChatAccountsController.class);

    @Autowired
    WeChatCoreService weChatCoreService;

    /*
     * 自定义token, 用作生成签名,从而验证安全性, TODO: token需要修改为实际的
     * */
    @Value("${wx.token}")
    private final String TOKEN = "mikamika";

    @ApiOperation(value = "测试token", notes = "测试token")
    @RequestMapping(value = "/process",method = RequestMethod.GET)
    public String login(@RequestParam("signature") String signature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("nonce") String nonce,
                                   @RequestParam("echostr") String echostr){

        logger.info("-----开始校验签名-----");
        /**
         * 将token、timestamp、nonce三个参数进行字典序排序
         * 并拼接为一个字符串
         */
        String sortStr = sort(TOKEN,timestamp,nonce);
        /**
         * 字符串进行shal加密
         */
        String mySignature = shal(sortStr);
        /**
         * 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
         */
        if(!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)){
            logger.info("-----签名校验通过-----");
            return echostr;
        }else {
            logger.error("-----校验签名失败-----");
            return "校验签名失败: " + echostr;
        }
    }


    /**
     * 参数排序
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }
    /**
     * 字符串进行shal加密
     * @param str
     * @return
     */
    public String shal(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 处理微信服务器发过来的请求, 需要把该url配置到公众号后台, produces用于解决乱码问题
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "测试token", notes = "测试token")
    @RequestMapping(value = "/process",method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String processRequest(HttpServletRequest request, HttpServletResponse response){
        logger.info("enter process......");
        String respMsg = weChatCoreService.processRequest(request);
        logger.debug("send message is : " + respMsg);
        return respMsg;
    }

    @ApiOperation(value = "解密小程序数据", notes = "解密小程序数据")
    @ApiImplicitParam(name = "jsonObject", value = "小程序用户信息", required = true, dataType = "JSONObject")
    @RequestMapping(value = "/descrypt",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<String> descrypt(@RequestBody JSONObject jsonObject){
        logger.info("enter descrypt......" + jsonObject.toJSONString());
        String code = jsonObject.getString("code");
        try {
            code = URLDecoder.decode(code, "UTF-8");
            String encryptedData = jsonObject.getString("encryptedData");
            encryptedData = URLDecoder.decode(encryptedData, "UTF-8");
            String iv = jsonObject.getString("iv");
            iv = URLDecoder.decode(iv, "UTF-8");
            logger.info("after decode code is : {} ; encryptedData is : {} ; iv is : {} ", code, encryptedData, iv);
            String result = weChatCoreService.decrypt(code, encryptedData, iv);
            if (StringUtils.isNotBlank(result)){
                return Response.ok(result);
            }else {
                return Response.error("解密小程序数据错误!!!");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error("输入参数解码错误！！！" );
        }
    }

    @ApiOperation(value = "获取小程序二维码", notes = "获取小程序二维码")
    @ApiImplicitParam(name = "wxaCodeParams", value = "小程序二维码信息", required = true, dataType = "WXACodeParams")
    @RequestMapping(value = "/getWXACode",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<Object> getWXACode(@RequestBody WXACodeParams wxaCodeParams){
        logger.info("ACodeParams is : " + JSON.toJSONString(wxaCodeParams));
        Object result = weChatCoreService.getWXACodeUnlimit(wxaCodeParams);
        logger.info("getWXACodeUnlimit return is : " + result);
        return Response.ok(result);
    }
}
