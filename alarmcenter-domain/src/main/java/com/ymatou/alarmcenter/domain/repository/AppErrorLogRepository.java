package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import io.netty.util.internal.ConcurrentSet;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ymatou.alarmcenter.infrastructure.common.Utils.getTimeStamp;

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
        long begin = getTimeStamp(new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute()));
        long end = getTimeStamp(new DateTime(endTime));

        query.field("AppId").equal(appId).field("ErrorLevel").equal(errorLevel)
                .field("AddTimeStamp").greaterThan(begin)
                .field("AddTimeStamp").lessThanOrEq(end);
        long totalRecords = getDatastore(dbName).getCount(query);
        return totalRecords;
    }

    public long getErrorCount(String appId, int errorLevel, Date beginTime, Date endTime) {
        String dbName = getDatabaseName(beginTime);
        String collectionName = getCollectionName(beginTime);
        return getErrorCount(dbName, collectionName, appId, errorLevel, beginTime, endTime);
    }

    public List<AppErrorLog> getErrorList(String dbName, String collectionName, String appId, int errorLevel, Date beginTime, Date endTime) {
        Query<AppErrorLog> query = newQuery(AppErrorLog.class, dbName, collectionName, ReadPreference.primaryPreferred());
        DateTime dt = new DateTime(beginTime);
        long begin = getTimeStamp(new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute()));
        long end = getTimeStamp(new DateTime(endTime));
        query.field("AppId").equal(appId).field("ErrorLevel").equal(errorLevel)
                .field("AddTimeStamp").greaterThan(begin)
                .field("AddTimeStamp").lessThanOrEq(end);
        return query.order("-AddTimeStamp").limit(100).asList();
    }

    public void deleteDatabse(String dbName) {
        getMongoClient().dropDatabase(dbName);
    }

    public PagingQueryResult<AppErrorLog> getAppErrorLogList(String dbName, String collectionName, String appId,
                                                             Integer errorLevel, Date beginTime, Date endTime,
                                                             String machineIp, String keyWord,
                                                             int pageSize, int pageIndex) {
        if (pageIndex < 1)
            pageIndex = 1;
        Query<AppErrorLog> query = newQuery(AppErrorLog.class, dbName, collectionName, ReadPreference.secondaryPreferred());
        ArrayList<Criteria> conditions = new ArrayList<>();
        if (!StringUtils.isBlank(appId))
            conditions.add(query.criteria("AppId").equal(appId));
        if (errorLevel != null)
            conditions.add(query.criteria("ErrorLevel").equal(errorLevel));
        if (!StringUtils.isBlank(machineIp)) {
            conditions.add(query.criteria("MachineIp").equal(machineIp));
        }
        if (!StringUtils.isBlank(keyWord)) {
            conditions.add(query.criteria("StackTrace").contains(keyWord));
        }
        if (beginTime != null) {
            DateTime dt = new DateTime(beginTime);
            long begin = getTimeStamp(new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour(), dt.getSecondOfMinute()));
            conditions.add(query.criteria("AddTimeStamp").greaterThanOrEq(begin));
        }
        if (endTime != null) {
            long end = getTimeStamp(new DateTime(endTime));
            conditions.add(query.criteria("AddTimeStamp").lessThan(end));
        }
        int size = conditions.size();
        Criteria[] array = conditions.toArray(new Criteria[size]);
        query.and(array);
        long totalRecords = getDatastore(dbName).getCount(query);
        query.order("-AddTimeStamp");
        List<AppErrorLog> list = query.offset(pageSize * (pageIndex - 1)).limit(pageSize).asList();
        if (list == null)
            list = new ArrayList<>();
        PagingQueryResult<AppErrorLog> result = new PagingQueryResult<>();
        result.setList(list);
        result.setTotalRecords(totalRecords);
        return result;
    }

    public PagingQueryResult<AppErrorLog> getAppErrorLogList(String appId, Integer errorLevel,
                                                             Date beginTime, Date endTime,
                                                             String machineIp, String keyWord,
                                                             int pageSize, int pageIndex) {
        String dbName = getDatabaseName(beginTime);
        String collectionName = getCollectionName(beginTime);
        return getAppErrorLogList(dbName, collectionName, appId, errorLevel, beginTime, endTime, machineIp, keyWord, pageSize, pageIndex);
    }
}
