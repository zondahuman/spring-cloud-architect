package org.spring.cloud.architect.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lee on 2018/10/23.
 */
public class LoggerUtil {

    private static final Logger vehicle = LoggerFactory.getLogger("vehicle");


    /**
     *
     * @param log
     */
    public static void vehicleInfo(String log) {
        vehicle.info("||"+log);
    }








    public static void main(String[] args) {


    }



}
