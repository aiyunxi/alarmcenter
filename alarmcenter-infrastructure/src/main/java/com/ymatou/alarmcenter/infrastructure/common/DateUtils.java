package com.ymatou.alarmcenter.infrastructure.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
     *
     * @param time String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date parseDate(String time) {
        if (time == null || time.length() == 0) return null;
        SimpleDateFormat formatter;
        int tempPos = time.indexOf("AD");
        time = time.trim();
        formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        if (tempPos > -1) {
            time = time.substring(0, tempPos) +
                    "公元" + time.substring(tempPos + "AD".length());//china
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos = time.indexOf("-");
        if (tempPos > -1 && (time.indexOf(" ") < 0)) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
        } else if ((time.indexOf("/") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if ((time.indexOf("-") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if ((time.indexOf("/") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        } else if ((time.indexOf("-") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(time, pos);

        return ctime;
    }


    static {

		/* Create Date Formats */
        final String[] possibleDateFormats = {
                /* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
                /* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",
                /* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",
                /* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",
				/* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",
				/* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss",
				/* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",
				/* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",
				/* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",
				/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",
				/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",
				/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",
				/* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",
				/* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",
				/* Simple Date Format */"yyyy-MM-dd",
				/* Simple Date Format */"MMM dd, yyyy"};


    }
}


