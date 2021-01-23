package com.ffg.medha.controller;

import com.ffg.medha.model.Student;
import com.ffg.medha.model.UserDetails;
import com.ffg.medha.service.RegistrationService;
import com.ffg.medha.service.UserServices;
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

    @Autowired
    UserServices userServices;

    @PostMapping("/uploadFile")
    public String uploadExcelSheet(@RequestBody MultipartFile file) throws Exception {
        return registrationService.uploadExcel(file);
    }

    @PostMapping("/signIn")
    public String userSignIn(@RequestBody UserDetails userDetails)
            throws Exception {
        return userServices.signIn(userDetails);
    }
//
//    @PostMapping("updateData")
//    public String updateData(@RequestBody Student student){
//    }
}
