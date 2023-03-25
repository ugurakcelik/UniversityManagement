package com.ugurakcelik.demo.controller;

import com.ugurakcelik.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public String getName(){
        return universityService.getUni();
    }

    @PostMapping
    public ResponseEntity<?> createUni(@RequestBody String first){
        universityService.createUni(first);
        System.out.printf(universityService.getUni());
        return ResponseEntity.ok().build();
    }
}
