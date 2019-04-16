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
public class VehicleReportTest {
    private static final String httpUrl = "http://localhost:8080/tbox/report";
//    private static final String httpUrl = "http://10.96.91.192:9000/tbox/report";


    @Test
    public void testVehicleReport() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            String json = "{\"no\":0,\"type\":90000,\"ct\":1549969954,\"trace_id\":\"0ab3d14d5c62aa22071b5cc68d68dab0\",\"span_id\":\"680184048862715694\",\"data\":{\"AirConditioner\":0,\"AvgFuel\":0,\"BatteryVoltage\":12.107,\"Charging\":0,\"ChargingType\":0,\"DeviceStatus\":3,\"Direction\":0,\"Door\":1,\"Dormer\":0,\"EngineRpm\":0,\"EngineTemp\":0,\"Gear\":0,\"Gps\":1,\"HandBrake\":2,\"HeadFl\":0,\"HighBeam\":0,\"KeyOnline\":1,\"KeySwitch\":1,\"Lamp\":1,\"Lat\":39.98020307669338,\"Lng\":116.39087867039831,\"Lock\":1,\"LowBeam\":0,\"MotorCurrent\":0,\"MotorVoltage\":0,\"PosLamp\":0,\"PowerLock\":1,\"PubInfo\":{},\"RearFl\":0,\"RemBattery\":0,\"RemFuel\":0,\"RemMileage\":0,\"SignalPower\":27,\"Speed\":0,\"TotalMileage\":0,\"Trigger\":1,\"Trunk\":1,\"UploadTime\":1549969953,\"Window\":0,\"car_uid\":9869216528066226,\"iot_account\":\"fszl-0000036\",\"timestamp\":\"2019-02-12 19:12:33\"}}";

            nvps.add(new BasicNameValuePair("json", json));//返回有参数的

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
