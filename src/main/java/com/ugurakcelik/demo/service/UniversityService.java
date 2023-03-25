package com.ugurakcelik.demo.service;

import com.ugurakcelik.demo.dao.UniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

    private final UniversityDao universityDao;

    @Autowired
    public UniversityService(@Qualifier("UniversityDao") UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    public void createUni(String name){
        universityDao.createUniversity(name);
    }

    public String getUni(){
        return universityDao.getUniName();
    }
}
