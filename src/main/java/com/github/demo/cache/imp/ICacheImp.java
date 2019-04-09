package com.github.demo.cache.imp;

import com.github.demo.cache.ICache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by issac.hu on 2019/4/9.
 */
public class ICacheImp implements ICache{

    private static Map<String,String> cache=new HashMap<>();
    private static Map<String,Long> cache_time=new HashMap<>();

    @Override
    public String get(String key) {
        Long timeOut = cache_time.get(key);
        if (timeOut !=null){
            long nowSeconds = System.currentTimeMillis() / 1000;
            if (nowSeconds<timeOut){
                return cache.get(key);
            }
        }
        return null;
    }

    @Override
    public void set(String key, String value, long timeOut) {
        cache.put(key,value);
        cache_time.put(key,System.currentTimeMillis()/1000+timeOut);
    }
}
