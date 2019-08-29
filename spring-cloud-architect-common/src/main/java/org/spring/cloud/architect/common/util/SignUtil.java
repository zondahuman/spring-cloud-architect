package org.spring.cloud.architect.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * Created by lee on 2019/8/23.
 */
public class SignUtil {

    public static String sign(Map<String, Object> paramsMap, String signKey) {
        List<String> list = Lists.newArrayList();
        for (Iterator<Map.Entry<String, Object>> iterator = paramsMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> entry = iterator.next();
            if(!StringUtils.equals(entry.getKey(), "token")) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list);
        StringJoiner stringJoiner = new StringJoiner("&");
        list.forEach(item -> {
            stringJoiner.add(item + "=" + paramsMap.get(item));
        });
        stringJoiner.add("signKey" + "=" + signKey);
        String sign = DigestUtils.md5Hex(stringJoiner.toString());
        return sign;
    }




    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }


}
