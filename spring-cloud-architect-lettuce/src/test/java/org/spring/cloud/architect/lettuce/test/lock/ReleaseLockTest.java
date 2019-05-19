package org.spring.cloud.architect.lettuce.test.lock;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.spring.cloud.architect.common.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lee on 2018/10/15.
 */
public class ReleaseLockTest {
    private static final String httpUrl = "http://localhost:9000/lettuce/releaseLock";
//    private static final String httpUrl = "http://10.96.91.192:9000/lettuce/releaseLock";


    @Test
    public void testReleaseLock() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            String key = "order_id_"+1;
            String lockValue = "b46aaf12-eadc-42c8-9ab4-677f38f1e6f7";

            nvps.add(new BasicNameValuePair("key", key));
            nvps.add(new BasicNameValuePair("value", lockValue));

            httpPost.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());
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


    @Test
    public void testGetIdGet() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            String param = "580542489925532";   //有数据
            HttpGet httpGet = new HttpGet(httpUrl + "?param=" + param);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();


            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            System.out.println("Executing request: " + httpGet.getRequestLine());
            HttpResponse response = httpClient.execute(httpGet);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void testGetIdBatch() throws ExecutionException, InterruptedException {
        Integer total = 2000;
        ExecutorService exc = Executors.newFixedThreadPool(total);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < total; i++) {
            //提交单个线程
            Future<String> future = exc.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return getIdGet();
                }
            });
            //将每个线程放入线程集合， 这里如果任何一个线程的执行结果没有回调，线程都会自动堵塞
            futures.add(future);
        }
        for (Future<String> future : futures) {
            String json = future.get();
            System.out.println("json=" + json);
        }
        //关闭线程池
        exc.shutdown();
    }


    public String getIdGet() {
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            String param = "580542489925532";   //有数据
            HttpGet httpGet = new HttpGet(httpUrl + "?param=" + param);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();


            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            System.out.println("Executing request: " + httpGet.getRequestLine());
            HttpResponse response = httpClient.execute(httpGet);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            result = EntityUtils.toString(response.getEntity());
//            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
