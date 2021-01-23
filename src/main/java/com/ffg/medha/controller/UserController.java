package com.ffg.medha.controller;


import com.ffg.medha.model.Student;
import com.ffg.medha.model.UserDetails;
import com.ffg.medha.service.UserServices;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/signIn")
    public String userSignIn(@RequestBody UserDetails userDetails) {
        return userServices.signIn(userDetails);
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody UserDetails userDetails) {
        return userServices.updatePassword(userDetails);
    }

    @PostMapping("/signUp")
    public String userSignUp(@RequestBody Student student){
        return userServices.signUp(student);
    }
}
