package com.ffg.medha.dao;

import com.ffg.medha.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDetailsRepo extends MongoRepository<Student, String> {
    Student findByEmailId(String firstName);
    List<Student> findAllByNeedsNgoApproval(boolean approval);
}
