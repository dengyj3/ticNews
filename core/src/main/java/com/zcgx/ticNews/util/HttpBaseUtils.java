package com.zcgx.ticNews.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpBaseUtils {

    /**
     * 获取get请求
     * @param restTemplate
     * @param url
     * @param headers
     * @return
     */
    public static String getRequestJson(RestTemplate restTemplate, String url, Map<String, String> headers){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpHeaders.set(k, v);
            });
        }
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}
