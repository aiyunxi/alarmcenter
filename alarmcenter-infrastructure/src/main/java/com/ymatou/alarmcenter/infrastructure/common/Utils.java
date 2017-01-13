package com.ymatou.alarmcenter.infrastructure.common;

import org.joda.time.DateTime;

/**
 * Created by zhangxiaoming on 2016/11/15.
 */
public class Utils {
    public static long dateTimeToTicks(DateTime dt) {
        long milli = dt.getMillis() + 8 * 3600 * 1000;
        long ticks = (milli * 10000) + 621355968000000000L;
        return ticks;
    }

    public static DateTime ticksToDateTime(long ticks) {
        long millis = (ticks - 621355968000000000L) / 10000 - 8 * 3600 * 1000;
        return new DateTime(millis);
    }

    public static long getTimeStamp(DateTime dateTime) {
        return dateTime.getMillis() / 1000;
    }


//    public static DateTime DateParse(String dateTime) {
//        if (StringUtils.isBlank(dateTime))
//            return null;
//        //尝试按yyyy-MM-dd HH:mm:ss分析
//        DateTime result;
//        try {
//            result = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(dateTime);
//        } catch (Exception e) {
//            //解析错误
//            result = null;
//        }
//
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//        //尝试按yyyy-MM-dd HH:mm分析
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//
//        //尝试按yyyy-MM-dd HH分析
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy-MM-dd HH").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy/MM/dd HH").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//
//        //尝试按yyyy-MM-dd分析
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//        if (null == result) {
//            try {
//                result = DateTimeFormat.forPattern("yyyy/MM/dd").parseDateTime(dateTime);
//            } catch (Exception e) {
//                //解析错误
//                result = null;
//            }
//        }
//        //尝试按时间分析
//        if (null == result) {
//            result = DateTime.parse(dateTime);
//        }
//        return result;
//
//    }
}
