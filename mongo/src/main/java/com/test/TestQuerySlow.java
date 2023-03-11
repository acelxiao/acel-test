package com.test;
import java.util.Date;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
public class TestQuerySlow {

    @Resource
    private MongoTemplate mongoTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        for (long i = 1 ; i <2; i++){
            UserIntimacy userIntimacy = new UserIntimacy();
            userIntimacy.setId(i+"");
            userIntimacy.setUid(i);
            userIntimacy.setTargetUid(i);
            userIntimacy.setTargetUidAlias("findyou");
            userIntimacy.setIntimacy(999L);
            userIntimacy.setUpdateTime(new Date());
            mongoTemplate.insert(userIntimacy);
        }
        return "sss";
    }

    @RequestMapping("/queryIntimacyForLargeData")
    @ResponseBody
    public List<Long> queryIntimacyForLargeData(long minIntimacy, int limit) {
        BasicDBList andCondition = new BasicDBList();
//        andCondition.add(new BasicDBObject("uid", new BasicDBObject("$eq", uid)));
        andCondition.add(new BasicDBObject("intimacy", new BasicDBObject("$gte", minIntimacy)));
        andCondition.add(new BasicDBObject("targetUidAlias", new BasicDBObject("$in", Lists.newArrayList("findyou","aiqianshou"))));

        BasicDBObject finalBasicDBObject = new BasicDBObject();
        finalBasicDBObject.put("$and", andCondition);

        BasicDBObject projection = new BasicDBObject();
        projection.put("targetUid", 1);

        BasicDBObject sortCond = new BasicDBObject();
        sortCond.put("updateTime", -1);

        long start = System.currentTimeMillis();
        MongoCursor<Document> dbCursor = mongoTemplate.getCollection("user_intimacy").find(finalBasicDBObject)
                .projection(projection).skip(0).limit(limit).iterator();
        List<Long> list = Lists.newArrayListWithCapacity(1000);
        while (dbCursor.hasNext()) {
            Document object = dbCursor.next();
            list.add(Long.parseLong(object.get("targetUid").toString()));
        }
        long cost1 = System.currentTimeMillis() - start;

        long start2 = System.currentTimeMillis();
        MongoCursor<Document> dbCursor2 = mongoTemplate.getCollection("user_intimacy").find(finalBasicDBObject)
                .projection(projection).skip(0).limit(limit).sort(sortCond).batchSize(1000).iterator();
        List<Long> list2 = Lists.newArrayListWithCapacity(1000);
        while (dbCursor2.hasNext()) {
            Document object = dbCursor2.next();
            list2.add(Long.parseLong(object.get("targetUid").toString()));
        }
        long cost2 = System.currentTimeMillis() - start2;
        log.info("size1:{} size2:{} cost1:{} cost2:{}", list.size(), list2.size(), cost1, cost2);
        return list;
    }
}
