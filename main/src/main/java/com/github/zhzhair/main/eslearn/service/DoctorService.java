package com.github.zhzhair.main.eslearn.service;

import com.github.zhzhair.main.eslearn.domain.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor();

    void drop();

    List<Doctor> findByHospitalNameOrDoctorName(String text);

    List<Doctor> searchDoctor(Integer pageNumber, Integer pageSize, String searchContent);

}
