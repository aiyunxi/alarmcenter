package com.ymatou.alarmcenter.test.repository;

import com.ymatou.alarmcenter.domain.model.NotifyRecord;
import com.ymatou.alarmcenter.domain.repository.NotifyRecordRepository;
import com.ymatou.alarmcenter.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public class NotifyRecordRepositoryTest extends BaseTest {
    @Resource
    private NotifyRecordRepository notifyRecordRepository;

    @Test
    public void test1() {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setAppId("abc");
        notifyRecordRepository.saveNotifyRecord(notifyRecord);
    }

}
