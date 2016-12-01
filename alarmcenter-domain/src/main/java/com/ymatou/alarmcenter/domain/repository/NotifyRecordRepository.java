package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.MongoClient;
import com.ymatou.alarmcenter.domain.model.NotifyRecord;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Repository
public class NotifyRecordRepository extends MongoRepository implements InitializingBean {
    @Resource(name = "notifyMongoClient")
    private MongoClient mongoClient;

    private static final String dbName = "NofityRecordDb";

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    public void saveNotifyRecord(NotifyRecord notifyRecord) {
        if (notifyRecord == null)
            return;
        insertEntiy(dbName, notifyRecord);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        getDatastore(dbName).ensureIndex(NotifyRecord.class, null, "AppId", false, false);
    }
}
