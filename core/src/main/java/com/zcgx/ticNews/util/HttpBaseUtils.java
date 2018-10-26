package com.zcgx.ticNews.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    public static String postRequest(RestTemplate restTemplate, String url, Map<String, String> headerMap, Map<String, String> paramMap){
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        if (null != headerMap){
            headerMap.forEach((k, v) -> {
                headers.add(k, v);
            });
        }

        if (null != paramMap){
            paramMap.forEach((k, v) -> {
                postParameters.add(k, v);
            });
        }
//        postParameters.add("userCode", "291974");

//        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

        String data= restTemplate.postForObject(url, r, String.class);
//        System.out.println(data);
        return data;
    }
}
