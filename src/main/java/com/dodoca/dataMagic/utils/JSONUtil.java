package com.dodoca.dataMagic.utils;

import com.alibaba.fastjson.JSON;
import com.dodoca.dataMagic.common.model.User;

/**
 * Created by lifei on 2016/11/16.
 */
public class JSONUtil {

    public static String objectToJson(Object obj) {
        if (null == obj) {
            return "{}";
        }
        return JSON.toJSONString(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return (T)JSON.parse(json);
    }

    public static void main(String[] args) {
        System.out.println(objectToJson(new User()));

    }
}
