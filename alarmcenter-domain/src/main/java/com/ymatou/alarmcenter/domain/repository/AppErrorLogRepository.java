package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import io.netty.util.internal.ConcurrentSet;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Repository
public class AppErrorLogRepository extends MongoRepository {

    private static ConcurrentSet concurrentSet = new ConcurrentSet();
    @Resource(name = "logMongoClient")
    private MongoClient mongoClient;

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    public String getDatabaseName(Date date) {
        if (date == null)
            return "AppLogDb";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        return String.format("AppLogDb%s", dateFormat.format(date));
    }

    public String getCollectionName(Date date) {
        if (date == null)
            return "AppErrLog";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return String.format("AppErrLog%s", dateFormat.format(date));
    }

    public void saveAppErrLog(AppErrorLog appErrLog) {
        if (appErrLog == null)
            return;
        Date date = new Date();
        String dbName = getDatabaseName(date);
        String collectionName = getCollectionName(date);
        insertEntiy(dbName, collectionName, appErrLog);

        String key = dbName + collectionName;
        if (!concurrentSet.contains(key)) {
            DBCollection dbCollection = getCollection(dbName, collectionName);
            dbCollection.createIndex(new BasicDBObject("AppId", 1));
            dbCollection.createIndex(new BasicDBObject("ExceptionName", 1));
            dbCollection.createIndex(new BasicDBObject("AddTimeStamp", -1));
            concurrentSet.add(key);
        }
    }

    public AppErrorLog getAppErrLog(String dbName, String collectionName, String id) {
        Query<AppErrorLog> query = newQuery(AppErrorLog.class, dbName, collectionName, ReadPreference.primaryPreferred());
        return query.field("_id").equal(new ObjectId(id)).get();
    }

    public AppErrorLog getAppErrLog(Date date, String id) {
        String dbName = getDatabaseName(date);
        String collectionName = getCollectionName(date);
        return getAppErrLog(dbName, collectionName, id);
    }

    public long getErrorCount(String dbName, String collectionName, String appId, int errorLevel, Date beginTime, Date endTime) {
        Query<AppErrorLog> query = newQuery(AppErrorLog.class, dbName, collectionName, ReadPreference.primaryPreferred());
        DateTime dt = new DateTime(beginTime);
        long begin = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute()).getMillis();
        long end = new DateTime(endTime).getMillis();
        query.field("AppId").equal(appId).field("ErrorLevel").equal(errorLevel)
                .field("AddTimeStamp").greaterThanOrEq(begin)
                .field("AddTimeStamp").lessThan(end);
        long totalRecords = getDatastore(dbName).getCount(query);
        return totalRecords;
    }

    public List<AppErrorLog> getErrorList(String dbName, String collectionName, String appId, int errorLevel, Date beginTime, Date endTime) {
        Query<AppErrorLog> query = newQuery(AppErrorLog.class, dbName, collectionName, ReadPreference.primaryPreferred());
        DateTime dt = new DateTime(beginTime);
        long begin = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute()).getMillis();
        long end = new DateTime(endTime).getMillis();
        query.field("AppId").equal(appId).field("ErrorLevel").equal(errorLevel)
                .field("AddTimeStamp").greaterThanOrEq(begin)
                .field("AddTimeStamp").lessThan(end);
        return query.order("-AddTimeStamp").limit(100).asList();
    }

}
