package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.ymatou.alarmcenter.domain.model.NotifyRecord;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Repository
public class NotifyRecordRepository extends MongoRepository implements InitializingBean {
    @Resource(name = "notifyMongoClient")
    private MongoClient mongoClient;

    private static final String dbName = "NotifyRecordDb";

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    public void saveNotifyRecord(NotifyRecord notifyRecord) {
        if (notifyRecord == null)
            return;
        insertEntiy(dbName, notifyRecord);
    }

    public PagingQueryResult<NotifyRecord> getNotifyRecordList(String appId, Date beginTime, Date endTime, Integer notifyType, Integer notifyStatus, int pageSize, int pageIndex) {
        if (pageIndex < 1)
            pageIndex = 1;
        Query<NotifyRecord> query = newQuery(NotifyRecord.class, dbName, "NotifyRecord", ReadPreference.secondaryPreferred());
        ArrayList<Criteria> conditions = new ArrayList<>();
        if (!StringUtils.isBlank(appId))
            conditions.add(query.criteria("appId").equal(appId));
        if (beginTime != null) {
            conditions.add(query.criteria("addTime").greaterThanOrEq(beginTime));
        }
        if (endTime != null) {
            conditions.add(query.criteria("addTime").lessThan(endTime));
        }
        if (notifyType != null) {
            conditions.add(query.criteria("notifyType").equal(notifyType));
        }
        if (notifyStatus != null) {
            conditions.add(query.criteria("notifyStatus").equal(notifyStatus));
        }
        int size = conditions.size();
        Criteria[] array = conditions.toArray(new Criteria[size]);
        query.and(array);
        long totalRecords = getDatastore(dbName).getCount(query);
        query.order("-addTime");
        List<NotifyRecord> list = query.offset(pageSize * (pageIndex - 1)).limit(pageSize).asList();
        if (list == null)
            list = new ArrayList<>();
        PagingQueryResult<NotifyRecord> result = new PagingQueryResult<>();
        result.setList(list);
        result.setTotalRecords(totalRecords);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getDatastore(dbName).ensureIndex(NotifyRecord.class, null, "addTime", false, false);
        getDatastore(dbName).ensureIndex(NotifyRecord.class, null, "appId", false, false);
    }
}
