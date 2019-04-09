package com.github.demo.cache;

/**
 * Created by issac.hu on 2019/4/9.
 */
public interface ICache {

    /**
     *
     * @param key
     * @return
     */
    public String get(String key);

    /**
     *
     * @param key
     * @param value
     * @param timeOut 超时时间s
     */
    public void set(String key,String value,long timeOut);

}
