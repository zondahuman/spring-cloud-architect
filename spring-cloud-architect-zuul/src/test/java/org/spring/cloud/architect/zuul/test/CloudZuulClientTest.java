package org.spring.cloud.architect.zuul.test;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.spring.cloud.architect.common.util.OkHttpClientUtil;

import java.io.IOException;
import java.util.Map;

/**
 *
 * http://localhost:9477/cloud-client/ribbon-client/add?param1=13&param2=33&token=zz
 */
public class CloudZuulClientTest {
    private static final String httpUrl = "http://localhost:9477/provider-zuul/order/getId?param=13&token=abcde";
    private static final String httpCreateUrl = "http://localhost:9477/provider-zuul/order/getId";

    public static void main(String[] args) throws IOException {


    }

//    @Test
//    public void testCloudZuulClient1() throws IOException {
//        String result = OkHttpClientUtil.httpPost(httpUrl, null);
//        System.out.println("result=" + result);
//    }

    @Test
    public void testCloudZuulClient2() throws IOException {
        String result = OkHttpClientUtil.httpGet(httpUrl);
        System.out.println("result=" + result);
    }

    @Test
    public void testCloudZuulClient() throws IOException {
        Map<String, String> params = Maps.newHashMap();
        params.put("param1", "123");
        params.put("param2", "234");
        params.put("token", "abcde");

        String result = OkHttpClientUtil.httpPost(httpCreateUrl, params);
        System.out.println("result=" + result);
    }


}
