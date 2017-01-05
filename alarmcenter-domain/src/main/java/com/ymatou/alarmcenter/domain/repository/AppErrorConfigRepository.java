package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.ymatou.alarmcenter.domain.model.AppErrorConfig;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
@Repository
public class AppErrorConfigRepository extends MongoRepository implements InitializingBean {
    @Resource(name = "configMongoClient")
    private MongoClient mongoClient;

    private static final String dbName = "YmatouConfigCenter";

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    public void saveAppErrorConfig(AppErrorConfig appErrorConfig) {
        if (appErrorConfig == null)
            return;
        insertEntiy(dbName, appErrorConfig);
    }

    public AppErrorConfig getAppErrorConfigByAppId(String appId) {
        if (StringUtils.isBlank(appId))
            return null;
        Query<AppErrorConfig> query = newQuery(AppErrorConfig.class, dbName, "AppErrorConfig", ReadPreference.primaryPreferred());
        return query.field("AppId").equal(appId).get();
    }

    public void deleteAppErrorConfig(String appId) {
        if (StringUtils.isBlank(appId))
            return;
        Bson filter = eq("AppId", appId);
        deleteOne(dbName, "AppErrorConfig", filter);
    }

    public void updateLastSmsHandleTime(String appId) {
        MongoCollection<Document> mc = mongoClient.getDatabase(this.dbName).getCollection("AppErrorConfig");
        mc.updateOne(new Document("AppId", appId), set("LastSmsHandleTime", new Date()), new UpdateOptions().upsert(true));
    }

    public void updateLastEmailHandleTime(String appId) {
        MongoCollection<Document> mc = mongoClient.getDatabase(this.dbName).getCollection("AppErrorConfig");
        mc.updateOne(new Document("AppId", appId), set("LastEmailHandleTime", new Date()), new UpdateOptions().upsert(true));
    }

    public List<AppErrorConfig> getAppErrorConfigList() {
        Datastore datastore = getDatastore(dbName);
        return datastore.find(AppErrorConfig.class).asList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getDatastore(dbName).ensureIndex(AppErrorConfig.class, null, "AppId", true, false);
    }
}
