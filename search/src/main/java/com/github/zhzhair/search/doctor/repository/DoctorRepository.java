package com.github.zhzhair.search.doctor.repository;


import com.github.zhzhair.search.doctor.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ES 操作类
 */
public interface DoctorRepository extends ElasticsearchRepository<Doctor, Long> {

    Doctor findByDoctorId(Long doctorId);
    List<Doctor> findByHospitalId(Long hospitalId);
    Page<Doctor> findByHospitalNameOrDoctorName(String hospitalName, String doctorName, Pageable pageable);
}
