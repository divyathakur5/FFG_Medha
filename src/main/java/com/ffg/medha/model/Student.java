package com.ffg.medha.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Getter
@Setter
public class Student {
    protected String firstName;
    protected String lastName;
    protected String emailId;
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
