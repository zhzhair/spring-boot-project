package com.github.zhzhair.stepscount.geo.service.impl;

import com.github.zhzhair.stepscount.geo.documents.ChargePoi;
import com.github.zhzhair.stepscount.geo.service.CaculateService;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 49535
 * on 2018/4/17.
 */
@Service
public class CaculateServiceImpl implements CaculateService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insertSample() {
        for (int x = 100; x < 131; x++) {
            for (int y = 30; y < 61; y++) {
                Double loca[] = new Double[]{Double.valueOf(x), Double.valueOf(y)};
                ChargePoi chargePoi = new ChargePoi();
                chargePoi.setPoi_id("poiid" + x);
                chargePoi.setMedia_url("http://www.baidu.com?params=" + x);
                chargePoi.setPoi_name("vini" + Arrays.toString(loca));
                chargePoi.setPrice(Math.random() * 100);
                chargePoi.setLocation(loca);
                mongoTemplate.insert(chargePoi);
            }
        }
    }

    @Override
    public void circleTest() {
        Circle circle = new Circle(115, 45, 20);
        List<ChargePoi> chargePois = mongoTemplate.find(new Query(Criteria.where("location").within(circle)), ChargePoi.class);
        for (ChargePoi chargePoi : chargePois) {
            System.out.println(chargePoi);
        }
        System.out.println(chargePois.size());
    }

    @Override
    public void sphereTest() {
        Circle circle = new Circle(115, 45, 20);
        List<ChargePoi> chargePois = mongoTemplate.find(new Query(Criteria.where("location").withinSphere(circle)), ChargePoi.class);
        for (ChargePoi chargePoi : chargePois) {
            System.out.println(chargePoi);
        }
        System.out.println(chargePois.size());
    }

    @Override
    public void boxTest() {
        Box box = new Box(new Point(0, 0), new Point(110, 131));
        List<ChargePoi> chargePois = mongoTemplate.find(new Query(Criteria.where("location").within(box)), ChargePoi.class);
        for (ChargePoi chargePoi : chargePois) {
            System.out.println(chargePoi);
        }
        System.out.println(chargePois.size());
    }

    @Override
    public void pointTest() {
        Point point = new Point(12, 12);
        List<ChargePoi> chargePois = mongoTemplate.find(new Query(Criteria.where("location").near(point).maxDistance(120)), ChargePoi.class);
        for (ChargePoi chargePoi : chargePois) {
            System.out.println(chargePoi);
        }
        System.out.println(chargePois.size());
    }

    @Override
    public void spaceTest() {
        Point point = new Point(12, 12);
        List<ChargePoi> chargePois = mongoTemplate.find(new Query(Criteria.where("location").nearSphere(point).maxDistance(120)), ChargePoi.class);
        for (ChargePoi chargePoi : chargePois) {
            System.out.println(chargePoi);
        }
        System.out.println(chargePois.size());
    }

    @Override
    public void nearestTest() {
        Point location = new Point(12, 12);
        NearQuery query = NearQuery.near(location).maxDistance(new Distance(100000, Metrics.KILOMETERS));
        GeoResults<ChargePoi> result = mongoTemplate.geoNear(query, ChargePoi.class);
        System.out.println(result.getAverageDistance());
        System.out.println(result.getContent().get(0));
    }
}
