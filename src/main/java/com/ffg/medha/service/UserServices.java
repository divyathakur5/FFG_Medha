package com.ffg.medha.service;

import com.ffg.medha.dao.StudentDetailsRepo;
import com.ffg.medha.model.Student;
import com.ffg.medha.model.UserDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
public class UserServices {

    @Autowired
    StudentDetailsRepo studentDetailsRepo;

    public String signIn(UserDetails userDetails){
        Student student = studentDetailsRepo.findByEmailId(userDetails.getEmail());
        if(student == null){
            return "User not registered";
        }
        if(student.getPassword().equals(userDetails.getPassword())){
            if(student.isNeedsNgoApproval()){
                return "Awaiting Admin Approval for registration!";
            }
            return "Sign-in successfull";
        }else{
            return "Invalid Password!!";
        }
    }

    /**
     * Update User Password
     * @param userDetails
     * @return
     */
    public String updatePassword(UserDetails userDetails) {
        Student student = studentDetailsRepo.findByEmailId(userDetails.getEmail());
        if(student != null){
            if(student.getPassword().equals(userDetails.getOldPassword())){
                student.setPassword(userDetails.getNewPassword());
                studentDetailsRepo.save(student);
                return "Password changed successfully.";
            }else{
                return "Password entered is invalid";
            }
        }else{
            return "No Student record found for given email : " + userDetails.getEmail();
        }
    }

    /**
     * User Registration
     * @param student
     * @return
     */
    public String signUp(Student student) {
        try {
            student.setNeedsNgoApproval(true);
            studentDetailsRepo.save(student);
            return "User registered, Awaiting approval";
        }catch(Exception e){
            log.error(e.getMessage());
            return "Unable to sign up, try again!";
        }
    }
}
