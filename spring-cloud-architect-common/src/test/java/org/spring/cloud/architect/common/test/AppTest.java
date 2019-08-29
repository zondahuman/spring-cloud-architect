package org.spring.cloud.architect.common.test;

/**
 * Created by lee on 2019/2/1.
 */
public class AppTest {
    public static void main(String[] args) {
        String rossetaKey = "rst_high_risk_plate_no_owner:123434567" ;
        String param = "rosetta_high_risk_rule_id_white_list:"+rossetaKey.substring(rossetaKey.indexOf(":")+1);
        System.out.println("param="+param);
        boolean flag = "aaaa:".endsWith(":");
        System.out.println("flag="+flag);

    }
}
