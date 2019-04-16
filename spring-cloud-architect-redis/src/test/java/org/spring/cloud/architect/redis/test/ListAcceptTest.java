package org.spring.cloud.architect.redis.test;

import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.spring.cloud.architect.common.util.HttpClientUtil;
import org.spring.cloud.architect.common.util.JsonUtil;

import java.util.List;

/**
 * Created by lee on 2018/10/15.
 */
public class ListAcceptTest {
    private static final String httpUrl = "http://localhost:9000/redis/getValueByKeys1";
    private static final String httpUrl2 = "http://localhost:9000/redis/getValueByKeys2";
//    private static final String httpUrl = "http://10.96.91.192:9000/redis/getValueByKeys1";


    @Test
    public void testListAccept() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);

            List<String> paramList = Lists.newArrayList("lee1", "lee3", "lee2");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for(String param:paramList){
                builder.addPart("paramList", new StringBody(param, ContentType.TEXT_PLAIN)) ;
            }

//            nvps.add(new BasicNameValuePair("paramList", paramList));//返回有参数的

            httpPost.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());
            httpPost.setEntity(builder.build());
            System.out.println("Executing request: " + httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void testListAccept1() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);
            httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());

            List<String> paramList = Lists.newArrayList("lee1", "lee3", "lee2");

            StringEntity entity = new StringEntity(JsonUtil.toJson(paramList), "utf-8");//解决中文乱码问题
            entity.setContentEncoding(Consts.UTF_8.toString());
            entity.setContentType(ContentType.APPLICATION_JSON.toString());
            httpPost.setEntity(entity);

            System.out.println("Executing request: " + httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testListAccept2() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl2);
            httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());

            String[] paramArray = new String[]{"lee1", "lee3", "lee2"} ;

            StringEntity entity = new StringEntity(JsonUtil.toJson(paramArray),Consts.UTF_8);//解决中文乱码问题
            entity.setContentEncoding(Consts.UTF_8.toString());
            entity.setContentType(ContentType.APPLICATION_JSON.toString());
            httpPost.setEntity(entity);

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
