package org.spring.cloud.architect.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by lee on 2019/8/28.
 */
public class BaseExtends {
    public BaseExtends() {
    }

    public static String success() {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(0));
        result.put("errmsg", "SUCCESS");
        result.put("data", Maps.newHashMap());
        return JsonHelper.toJson(result);
    }

    public static String success(String msg) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(0));
        result.put("errmsg", msg);
        result.put("data", Maps.newHashMap());
        return JsonHelper.toJson(result);
    }

    public static String success(Object data) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(0));
        result.put("errmsg", "SUCCESS");
        result.put("data", data);
        return JsonHelper.toJson(result);
    }

    public static String success(String msg, Object dataI) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(0));
        result.put("errmsg", msg);
        result.put("data", dataI);
        return JsonHelper.toJson(result);
    }

    public static String success(Integer errno, String msg) {
        HashMap result = Maps.newHashMap();
        result.put("errno", errno);
        result.put("errmsg", msg);
        result.put("data", Maps.newHashMap());
        return JsonHelper.toJson(result);
    }

    public static String error() {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(1));
        result.put("errmsg", "ERROR");
        result.put("data", Maps.newHashMap());
        return JsonHelper.toJson(result);
    }

    public static String error(String msg) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(1));
        result.put("errmsg", msg);
        result.put("data", Maps.newHashMap());
        return JsonHelper.toJson(result);
    }

    public static String error(Object data) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(1));
        result.put("errmsg", "ERROR");
        result.put("data", data);
        return JsonHelper.toJson(result);
    }

    public static String error(Integer errno, String msg) {
        return success(errno, msg);
    }

    public static String error(String msg, Object data) {
        HashMap result = Maps.newHashMap();
        result.put("errno", Integer.valueOf(1));
        result.put("errmsg", msg);
        result.put("data", data);
        return JsonHelper.toJson(result);
    }

    public static String result(Integer errno, String errmsg, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", errno);
        jsonObject.put("errmsg", errmsg);
        jsonObject.put("data", data);
        return JsonHelper.toJson(jsonObject);
    }




}
