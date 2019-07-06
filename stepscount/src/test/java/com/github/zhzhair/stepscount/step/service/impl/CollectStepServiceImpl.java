package com.github.zhzhair.stepscount.step.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.zhzhair.stepscount.step.service.CollectStepService;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * Created by 49535
 * on 2018/4/8.
 */
@Service
public class CollectStepServiceImpl implements CollectStepService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void executeScript() {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        ExecutableMongoScript script = new ExecutableMongoScript("function(x) { return x; }");
        //直接执行
//        scriptOps.execute(script, "directly execute script");
        //执行并保存到数据库
        scriptOps.register(new NamedMongoScript("test", script)); //指定脚本名称
        scriptOps.call("test", "execute script via name");//调用远程脚本
        scriptOps.register(script);
        boolean exists = scriptOps.exists("test");
        Assert.isTrue(exists,"不存在此脚本");
        String x = (String)scriptOps.call("test",Integer.toString(20));
        System.out.println(x);
    }

    @Override
    public void getDistinctCountWithJsonSql() {
        Criteria criteria = Criteria.where("groupId").is(1);
        Query query = new Query();
        query.addCriteria(criteria);
//        GroupBy groupBy = new GroupBy("userId");
        Document document0 = new Document();
        document0.put("distinct","steps");
        document0.put("key","userId");
//        document0.put("groupBy",groupBy.getGroupByObject());
        document0.put("query",query.getQueryObject());
        Document document =  mongoTemplate.executeCommand(document0);
        JSONArray jsonArray = JSONArray.parseArray(document.get("values").toString());
        Iterator<Object> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
