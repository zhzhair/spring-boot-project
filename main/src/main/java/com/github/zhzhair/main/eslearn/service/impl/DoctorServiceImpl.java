package com.github.zhzhair.main.eslearn.service.impl;

import com.github.zhzhair.main.eslearn.domain.Doctor;
import com.github.zhzhair.main.eslearn.mapper.DoctorMapper;
import com.github.zhzhair.main.eslearn.repository.DoctorRepository;
import com.github.zhzhair.main.eslearn.service.DoctorService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private DoctorRepository doctorRepository;

    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    private Pageable pageable = PageRequest.of(pageNumber,pageSize);

    @Override
    public void saveDoctor() {
        List<HashMap<String,Object>> list = doctorMapper.getDoctors();
        IntStream.range(0,list.size()).parallel().forEach(i->save(i,list));
    }

    private void save(long i,List<HashMap<String,Object>> list){
        HashMap<String,Object> map = list.get((int)i);
        Doctor doctor = new Doctor();
        doctor.setId(i);
        doctor.setDoctorId(Long.valueOf(map.get("doctorId").toString()));
        doctor.setDoctorName(map.get("doctorName").toString());
        doctor.setDoctorTitle(map.get("doctorTitle").toString());
        doctor.setDoctorDes(map.get("doctorDes").toString());
        doctor.setSpecialty(map.get("specialty").toString());
        doctor.setLabel(map.get("label").toString());
        doctor.setHospitalId(Long.valueOf(map.get("hospitalId").toString()));
        doctor.setHospitalName(map.get("hospitalName").toString());
        doctorRepository.save(doctor);
    }

    @Override
    public void drop() {
        doctorRepository.deleteAll();
    }

    @Override
    public List<Doctor> findByHospitalNameOrDoctorName(String text) {
        return doctorRepository.findByHospitalNameOrDoctorName(text,text,pageable).getContent();
    }

    @Override
    public List<Doctor> searchDoctor(Integer pageNumber, Integer pageSize, String searchContent) {
        // 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = 20;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = 0;
        }

        // 构建搜索查询
        SearchQuery searchQuery = this.getDoctorSearchQuery(pageNumber,pageSize,searchContent);

        Page<Doctor> docPage = doctorRepository.search(searchQuery);
        return docPage.getContent();
    }

    /**
     * 根据搜索词构造搜索查询语句
     *
     * 代码流程：
     *      - 权重分查询
     *      - 短语匹配
     *      - 设置权重分最小值
     *      - 设置分页参数
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return SearchQuery
     */
    private SearchQuery getDoctorSearchQuery(Integer pageNumber, Integer pageSize,String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        //搜索全部文档
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(searchContent,"doctorName", "hospitalName");
//        QueryBuilders.rangeQuery("age").gt(30);
//        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + searchContent + "*");
//        QueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("name", "*" + searchContent + "*");
//        QueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("description", "*beautiful*");
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(queryBuilder1).must(queryBuilder2);
//        boolQueryBuilder.must(QueryBuilders.fuzzyQuery("doctorName", "张"));//模糊搜索
//        boolQueryBuilder.should(queryBuilder1).should(queryBuilder2);
//        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("name", searchContent);

        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(999.0f);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(queryBuilder,scoreFunctionBuilder)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(100.0f);
        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }

}
