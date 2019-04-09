package com.github.demo.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.demo.cache.ICache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by issac.hu on 2019/4/9.
 */
public class BaseApi {

    private static String appId;
    private static String appSecret;
    private static String base_url;
    private static ICache iCache;

    public static void init(String appId,String appSecret,String baseUrl,ICache iCache){
        BaseApi.appId=appId;
        BaseApi.appSecret=appSecret;
        BaseApi.base_url=baseUrl;
        BaseApi.iCache=iCache;
    }

    protected static String get(Map<String, Object> params, String url){
        return request("GET",params,url);
    }

    protected static String post(Map<String, Object> params, String url){
       return request("POST",params,url);
    }

    protected static String request(String method,Map<String, Object> params, String url){
        String result=null;
        setCommonParams(params);
        url=base_url+url;
        System.out.println("params:"+JSON.toJSONString(params)+",url:"+url);
         if ("GET".equals(method)){
             result= HttpUtil.get(url,params);
         }else if ("POST".equals(method)){
             result= HttpUtil.post(url,params);
         }
         System.out.println("result:"+result);
         return result;
    }

    private static void setCommonParams(Map<String, Object> params){
        if (params==null) {
            params = new TreeMap<>();
        }
        params.put("access_token",getAccessToken());
        params.put("timestamp",System.currentTimeMillis()/1000);
        params.put("version","1.0.0");
        params.put("sign", Signs.calculate(params,appSecret));
    }

    protected static String getAccessToken(){
        String key=appId + "_cache_open_token_key";
        String cacheValue = iCache.get(key);
        if (cacheValue!=null){
            System.out.println("get cache value:"+cacheValue);
            return cacheValue;
        }
        Map<String, Object> params=new HashMap<>();
        params.put("appid",appId);
        params.put("grant_type","client_credential");
        params.put("timestamp",System.currentTimeMillis()/1000);
        params.put("version","1.0.0");
        params.put("sign", Signs.calculate(params,appSecret));
        System.out.println(JSON.toJSONString(params));
        String uriContent= HttpUtil.get(base_url + "/token/create", params);
        System.out.println(uriContent);
        JSONObject jsonObject = JSON.parseObject(uriContent);
        String token = jsonObject.getJSONObject("data").getString("access_token");
        System.out.println("set to cache");
        iCache.set(key,token,3500L);
        return token;
    }
}
