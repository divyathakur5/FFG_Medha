package com.ffg.medha.controller;

import com.ffg.medha.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class SampleController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/uploadFile")
    public String uploadExcelSheet(@RequestBody MultipartFile file) throws Exception {
        return registrationService.uploadExcel(file);
    }

    @PostMapping("/insertData")
    public String insertData(String data){
        return "done" + data;
    }

}
