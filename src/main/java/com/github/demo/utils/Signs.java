package com.github.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

public class Signs {

    /**
     * @param parameters
     * @param appsecret
     * @return
     */
    public static final String calculate(final Map<String, ? extends Object> parameters, final String appsecret) {
        TreeMap<String, Object> params = new TreeMap<>(parameters);
        params.remove("sign");
        params.put("appsecret", appsecret);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuilder.append(entry.getKey().trim());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue().toString().trim());
            stringBuilder.append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return DigestUtils.sha1Hex(stringBuilder.toString());
    }


}