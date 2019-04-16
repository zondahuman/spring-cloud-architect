package org.spring.cloud.architect.common.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: abin
 * Date: 16-4-28
 * Time: 下午1:10
 * To change this template use File | Settings | File Templates.
 * 线程安全的DateFormat
 */
public class DateUtil {
    private static final FastDateFormat YMDHMS_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat YMD_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat HMS_DATE_FORMAT = FastDateFormat.getInstance("HH:mm:ss");


    private static final String ymdhmsSdf = "yyyy-MM-dd HH:mm:ss";
    private static final String ymdSdf = "yyyy-MM-dd";

    private static final String nolineymdSdf = "yyyyMMdd";

    private static final String ymdY = "yyyy";

    private static final String hmsSdf = "HH:mm:ss";

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Date getYMDTime(String param) {
        Date result = null;
        try {
            result = YMD_DATE_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getYMDLongTime(String param) {
        Long result = null;
        try {
            result = YMD_DATE_FORMAT.parse(param).getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Date getYMDHMSTime(String param) {
        Date result = null;
        try {
            result = YMDHMS_DATE_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getYMDHMSLongTime(String param) {
        Long result = null;
        try {
            result = YMDHMS_DATE_FORMAT.parse(param).getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static Date getHMSTime(String param) {
        Date result = null;
        try {
            result = HMS_DATE_FORMAT.parse(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getHMSLongTime(String param) {
        Long result = null;
        try {
            result = HMS_DATE_FORMAT.parse(param).getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDHMSTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDHMSTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getYTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getYMDTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), ymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getNoLineYMDTime(String param) {
        String result = "";
        try {
            Date time = YMD_DATE_FORMAT.parse(param);
            result = DateFormatUtils.format(time, nolineymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String getHMSTime() {
        String result = "";
        try {
            result = DateFormatUtils.format(new Date(), hmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getHMSTime(Date date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, hmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getYMDHMSTime(long date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getYMDTime(long date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String getHMSTime(long date) {
        String result = "";
        try {
            result = DateFormatUtils.format(date, ymdhmsSdf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getYMDTimeStamp() {
        Long result = null;
        try {
            result = YMD_DATE_FORMAT.parse(getYMDTime(new Date())).getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Long getYMDHMSTimeStamp() {
        Long result = null;
        try {
            result = YMDHMS_DATE_FORMAT.parse(getYMDHMSTime(new Date())).getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Date getYMDDate(Long timestamp) {
        Date result = null;
        try {
            result = new Date(timestamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }




    public static long getTimeDiff(String startDate) {
        try {
            Long start= getYMDLongTime(startDate);
            Long now = new Date().getTime();
            long divisor = 24*60*60*1000;
            long days = (now - start) / divisor;
            return days;
        } catch (Exception e) {
            throw new RuntimeException("startDate--e:"+e);
        }
    }


    public static Date getNextDayStartTime(int interval) {
        Calendar someDay = Calendar.getInstance();
        someDay.add(Calendar.DATE, interval);//增加一天
        someDay.set(Calendar.HOUR_OF_DAY, 0);
        someDay.set(Calendar.MINUTE, 0);
        someDay.set(Calendar.SECOND, 0);
        someDay.set(Calendar.MILLISECOND, 0);
        return someDay.getTime();
    }

    public static Date getNextDayEndTime(int interval) {
        Calendar someDay = Calendar.getInstance();
        someDay.add(Calendar.DATE, interval);//增加一天
        someDay.set(Calendar.HOUR_OF_DAY, 23);
        someDay.set(Calendar.MINUTE, 59);
        someDay.set(Calendar.SECOND, 59);
        someDay.set(Calendar.MILLISECOND, 999);
        return someDay.getTime();
    }



    public static void main(String[] args) {

        System.out.println(DateUtil.getNoLineYMDTime("1900-00-00"));
        System.out.println(DateUtil.getNoLineYMDTime("2012-00-00"));


    }

}
