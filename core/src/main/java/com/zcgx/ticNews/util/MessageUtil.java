package com.zcgx.ticNews.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zcgx.ticNews.message.resp.TextMessage;
import com.zcgx.ticNews.message.resp.Article;
import com.zcgx.ticNews.message.resp.NewsMessage;
import io.swagger.models.auth.In;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    // 事件类型：subscribe（订阅） and 未关注群体扫描二维码
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：已关注群体扫描二维码
    public static final String EVENT_TYPE_SCAN = "scan";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 自定义菜单点击事件
    public static final String EVENT_TYPE_CLICK = "CLICK";
    public static final String EVENT_TYPE_VIEW = "VIEW";
    // 把消息推送给客服
    public static final String EVENT_TYPE_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";


    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
        Map<String, String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        elementList.stream().forEach(element -> {
            map.put(element.getName(), element.getText());
        });
        inputStream.close();
        inputStream = null;
        return map;
    }

    public static String textMessageToXml(TextMessage textMessage){
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String newsMessageToXml(NewsMessage newsMessage){
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new Article().getClass());
        return xStream.toXML(newsMessage);
    }

    private static XStream xStream = new XStream(new XppDriver(){
       public HierarchicalStreamWriter createWriter(Writer out){
           return new PrettyPrintWriter(out){
               boolean cdata = true;
               public void startNode(String name, Class clazz){
                   super.startNode(name, clazz);
               }
               protected void writeText(QuickWriter writer, String text){
                   if (cdata){
                       writer.write("<![CDATA[");
                       writer.write(text);
                       writer.write("]]>");
                   }else {
                       writer.write(text);
                   }
               }
           };
       }
    });


}
