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

    public static long getTimeStamp(DateTime dateTime){
        return dateTime.getMillis()/1000;
    }
}
