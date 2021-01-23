package com.ffg.medha.controller;

import com.ffg.medha.model.Student;
import com.ffg.medha.service.NgoServices;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Slf4j
@RestController
@RequestMapping("/api/ngo")
public class NgoController {

    @Autowired
    NgoServices ngoServices;

    @PostMapping("/uploadFile")
    public String uploadExcelSheet(@RequestBody MultipartFile file)
            throws Exception {
        log.info("Upload Excel");
        return ngoServices.uploadExcel(file);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody String email){
        log.info("Delete user");
        return ngoServices.deleteUser(email);
    }

    @PostMapping("/viewPendingApprovals")
    public List<Student> viewPendingApprovals(){
        log.info("View Pending Approvals");
        return ngoServices.getPendingApprovalRequests();
    }

}
