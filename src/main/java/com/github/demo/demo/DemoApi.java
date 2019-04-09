package com.github.demo.demo;

import com.github.demo.cache.imp.ICacheImp;
import com.github.demo.utils.BaseApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by issac.hu on 2019/4/9.
 */
public class DemoApi extends BaseApi {

    static {
        BaseApi.init("xxx","xxx","test",new ICacheImp());
    }

    public static void testGetEmployee(){
        Map<String,Object> params=new HashMap<>();
        params.put("corp_code","xxq01");
        post(params,"/employee/get");
    }


}
