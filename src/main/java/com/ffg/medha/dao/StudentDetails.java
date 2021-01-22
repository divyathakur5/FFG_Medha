package com.ffg.medha.dao;

import com.ffg.medha.model.Category;
import com.ffg.medha.model.GenderType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "students")
public class StudentDetails {

    @Id
    protected String emailId;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phoneNumber;
    protected GenderType gender;
    protected Category category;
    protected String relativeName;
    protected String parentsOccupation;
    protected String aadharNumber;
    protected Long noOfEarningFamilyMembers;
    protected Double totalHouseholdIncome;
    protected String ownGadget;
    protected String courseName;
    protected String collegeName;
    protected Long completedAcademicYear;
    protected String about;
    protected String areaOfInterest;
    protected String hoursAllocation;
}
