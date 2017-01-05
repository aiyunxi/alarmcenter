package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Repository
public class AppBaseConfigRepository extends MongoRepository implements InitializingBean {
    @Resource(name = "configMongoClient")
    private MongoClient mongoClient;

    private static final String dbName = "YmatouConfigCenter";

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    public void saveAppBaseConfig(AppBaseConfig appBaseConfig) {
        if (appBaseConfig == null)
            return;
        insertEntiy(dbName, appBaseConfig);
    }

    public AppBaseConfig getAppBaseConfigById(String id) {
        if (StringUtils.isBlank(id))
            return null;
        return getEntity(AppBaseConfig.class, dbName, "_id", id, ReadPreference.primary());
    }

    public AppBaseConfig getAppBaseConfigByAppId(String appId) {
        if (StringUtils.isBlank(appId))
            return null;
        Query<AppBaseConfig> query = newQuery(AppBaseConfig.class, dbName, "AppBaseConfig", ReadPreference.primaryPreferred());
        return query.field("AppId").equal(appId).get();
    }

    public void deleteAppBaseConfigById(String id) {
        if (StringUtils.isBlank(id))
            return;
        Bson filter = eq("_id", id);
        deleteOne(dbName, "AppBaseConfig", filter);
    }

    public void deleteAppBaseConfigByAppId(String appId) {
        if (StringUtils.isBlank(appId))
            return;
        Bson filter = eq("AppId", appId);
        deleteOne(dbName, "AppBaseConfig", filter);
    }

    public List<AppBaseConfig> getAppBaseConfigList() {
        Datastore datastore = getDatastore(dbName);
        return datastore.find(AppBaseConfig.class).asList();
    }

    public PagingQueryResult<AppBaseConfig> getAppBaseConfigList(String appId, int pageSize, int pageIndex) {
        if (pageIndex < 1)
            pageIndex = 1;
        Query<AppBaseConfig> query = newQuery(AppBaseConfig.class, dbName, "AppBaseConfig", ReadPreference.secondaryPreferred());
        ArrayList<Criteria> conditions = new ArrayList<>();
        if (!StringUtils.isBlank(appId))
            conditions.add(query.criteria("AppId").equal(appId));
        int size = conditions.size();
        Criteria[] array = conditions.toArray(new Criteria[size]);
        query.and(array);
        long totalRecords = getDatastore(dbName).getCount(query);
        query.order("-LastUpdateDatetime");
        List<AppBaseConfig> list = query.offset(pageSize * (pageIndex - 1)).limit(pageSize).asList();
        if (list == null)
            list = new ArrayList<>();
        PagingQueryResult<AppBaseConfig> result = new PagingQueryResult<>();
        result.setList(list);
        result.setTotalRecords(totalRecords);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getDatastore(dbName).ensureIndex(AppBaseConfig.class, null, "AppId", true, false);
    }
}
