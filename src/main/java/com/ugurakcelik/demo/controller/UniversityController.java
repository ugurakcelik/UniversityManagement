package com.ugurakcelik.demo.controller;

import com.ugurakcelik.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UniversityController {
    private UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public String getName(){
        return universityService.getUni(1);
    }

    @PostMapping
    public ResponseEntity<?> createUni(@RequestBody String first){
        universityService.createUni(first);
        System.out.printf(universityService.getUni(1));
        return ResponseEntity.ok().build();
    }
}
