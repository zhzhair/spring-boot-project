package com.github.zhzhair.search.doctor.service;

import com.github.zhzhair.search.doctor.domain.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor();

    void drop();

    Doctor findByDoctorId(Long doctorId);

    List<Doctor> findByHospitalId(Long hospitalId);

    List<Doctor> findByHospitalNameOrDoctorName(String text);

    List<Doctor> searchDoctor(Integer pageNumber, Integer pageSize, String searchContent);

}
