package com.ymatou.alarmcenter.test.common;

import com.ymatou.alarmcenter.infrastructure.common.DateUtils;
import com.ymatou.alarmcenter.test.BaseTest;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

/**
 * Created by zhangxiaoming on 2017/1/11.
 */
public class UtilsTest extends BaseTest {

    @Test
    public void test1() {
        String date1 = "2017-01-11 10:20:30";
        Date date = DateUtils.parseDate(date1);
        DateTime dt = new DateTime(date);
    }
}
