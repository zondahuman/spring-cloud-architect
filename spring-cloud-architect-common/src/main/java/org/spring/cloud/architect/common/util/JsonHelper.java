package org.spring.cloud.architect.common.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2019/8/22.
 */


public class JsonHelper {
    private static Gson gson = null;

    private JsonHelper() {
    }

    public static JsonHelper getInstance() {
        return GsonHelper.instance;
    }

    private static class GsonHelper {
        private static final JsonHelper instance = new JsonHelper();
    }

    static {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }


    public static String toJson(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }


    public static <T> T fromJson(String gsonString) {
        if (gson != null) {
            Type type = new TypeToken<T>() {
            }.getType();
            return (T) gson.fromJson(gsonString, type);
        }
        return null;
    }


    public static void main(String[] args) {
        List<Map<String, String>> list = Lists.newArrayList();
        Map<String, String> request = Maps.newHashMap();
        for (int i = 0; i < 3; i++) {
            request.put("lee" + i, "apple" + i);
        }
        list.add(request);
        String params = JsonHelper.toJson(list);
        System.out.println("params=" + params);
        List<Map<String, String>> result = JsonHelper.fromJson(params);
        System.out.println("result=" + result);

    }
}