package com.ffg.medha.service;

import com.ffg.medha.dao.StudentDetailsRepo;
import com.ffg.medha.model.Student;
import com.ffg.medha.model.UserDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserServices {

    @Autowired
    StudentDetailsRepo studentDetailsRepo;

    public String signIn(UserDetails userDetails){
        Student student = studentDetailsRepo.findByEmailId(userDetails.getEmail());
        if(student == null){
            return "User not registered";
        }
        if(student.getPassword().equals(userDetails.getPassword())){
            return "Sign-in successfull";
        }else{
            return "Invalid Password!!";
        }
    }
}
