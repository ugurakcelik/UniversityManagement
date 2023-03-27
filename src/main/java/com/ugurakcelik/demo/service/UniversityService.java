package com.ugurakcelik.demo.service;

import com.ugurakcelik.demo.dao.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(@Qualifier("UniversityDao") UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public void createUni(String name){
        universityRepository.createUniversity(name);
    }

    public String getUni(){
        return universityRepository.getUniName();
    }
}
