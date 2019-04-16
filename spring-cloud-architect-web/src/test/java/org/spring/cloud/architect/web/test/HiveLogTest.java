package org.spring.cloud.architect.web.test;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.spring.cloud.architect.common.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2018/10/15.
 */
public class HiveLogTest {
    private static final String httpUrl = "http://localhost:8080/tbox/hiveLog";
//    private static final String httpUrl = "http://10.96.91.192:9000/tbox/hiveLog";


    @Test
    public void testHiveLog() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            String param = "2";

            nvps.add(new BasicNameValuePair("param", param));//返回有参数的

            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            System.out.println("Executing request: " + httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
