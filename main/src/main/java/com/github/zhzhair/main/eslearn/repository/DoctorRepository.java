package com.github.zhzhair.main.eslearn.repository;


import com.github.zhzhair.main.eslearn.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES 操作类
 */
public interface DoctorRepository extends ElasticsearchRepository<Doctor, Long> {

    Page<Doctor> findByHospitalNameOrDoctorName(String hospitalName, String doctorName, Pageable pageable);
}
