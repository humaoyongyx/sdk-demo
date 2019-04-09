package com.github.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by issac.hu on 2019/4/9.
 */
public class BaseUtils {

    public static String build(String url, Map<String, ?> params) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                stringBuilder.append("&");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }


}
