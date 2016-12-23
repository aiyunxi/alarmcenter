package com.ymatou.alarmcenter.domain.repository;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.ymatou.alarmcenter.domain.model.Operator;
import com.ymatou.alarmcenter.infrastructure.db.mongodb.MongoRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Repository
public class OperatorRepository extends MongoRepository implements InitializingBean {
    @Resource(name = "configMongoClient")
    private MongoClient mongoClient;

    private static final String dbName = "YmatouConfigCenter";

    @Override
    protected MongoClient getMongoClient() {
        return mongoClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getDatastore(dbName).ensureIndex(Operator.class, null, "LoginId", true, false);
    }

    public void saveOperator(Operator operator) {
        if (operator == null)
            return;
        insertEntiy(dbName, operator);
    }

    public Operator getOperatorById(String id) {
        if (StringUtils.isBlank(id))
            return null;
        return getEntity(Operator.class, dbName, "_id", id, ReadPreference.primary());
    }

    public Operator getOperatorByLoginId(String loginId) {
        if (StringUtils.isBlank(loginId))
            return null;
        Query<Operator> query = newQuery(Operator.class, dbName, "Operator", ReadPreference.primaryPreferred());
        return query.field("LoginId").equal(loginId).get();
    }

    public void deleteOperatorById(String id) {
        if (StringUtils.isBlank(id))
            return;
        Bson filter = eq("_id", id);
        deleteOne(dbName, "Operator", filter);
    }
}
