package com.ffg.medha.service;

import com.ffg.medha.dao.StudentDetailsRepo;
import com.ffg.medha.model.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Data
@Service
@Slf4j
public class NgoServices {

    @Autowired
    StudentDetailsRepo studentDetailsRepo;

    @Value("${excel.config.location}")
    String metadataLocation;

    Properties metadata;

    /**
     * Upload excel sheet to onboard users
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadExcel(MultipartFile file) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Student> studentList = getStudentsFromFile(sheet);

        //add list of students to be registered into db
        boolean success = persistData(studentList);
        if(success){
            return "Students Registered successfully!!";
        }else{
            return "Error Registering Students";
        }
    }

    /**
     * Persist Students Data into Database
     * @param studentList
     * @return
     */
    private boolean persistData(List<Student> studentList) {
        try{
            studentList.forEach(eachStudent-> {
                studentDetailsRepo.save(eachStudent);
            });
        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Parse Excel Sheet to populate Student Details
     * @param sheet
     * @return
     * @throws Exception
     */
    private List<Student> getStudentsFromFile(XSSFSheet sheet) throws Exception {
        metadata = getExcelMetadata();
        if(metadata == null){
            throw new Exception("Failed to parse metadata");
        }
        List<Student> studentList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while(rowIterator.hasNext()){
            //populate data from rows into Student entity
            Row row = rowIterator.next();
            Student student = new Student();
            if(row.getCell(Integer.parseInt(metadata.getProperty("firstName"))) != null){
                student.setFirstName(row.getCell(Integer.parseInt(
                        metadata.getProperty("firstName"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("lastName"))) != null){
                student.setLastName(row.getCell(Integer.parseInt(
                        metadata.getProperty("lastName"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("emailId"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("emailId"))).setCellType(CellType.STRING);
                student.setEmailId(row.getCell(Integer.parseInt(
                        metadata.getProperty("emailId"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("address"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("address"))).setCellType(CellType.STRING);
                student.setAddress(row.getCell(Integer.parseInt(
                        metadata.getProperty("address"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("phoneNumber"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("phoneNumber"))).setCellType(CellType.STRING);
                student.setPhoneNumber(row.getCell(Integer.parseInt(
                        metadata.getProperty("phoneNumber"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("gender"))) != null){
                student.setGender(row.getCell(Integer.parseInt(
                        metadata.getProperty("gender"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("category"))) != null){
                student.setCategory(row.getCell(Integer.parseInt(
                        metadata.getProperty("category"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("relativeName"))) != null){
                student.setRelativeName(row.getCell(Integer.parseInt(
                        metadata.getProperty("relativeName"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("parentsOccupation"))) != null){
                student.setParentsOccupation(row.getCell(Integer.parseInt(
                        metadata.getProperty("parentsOccupation"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("aadharNumber"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("aadharNumber"))).setCellType(CellType.STRING);
                student.setAadharNumber(row.getCell(Integer.parseInt(
                        metadata.getProperty("aadharNumber"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("noOfEarningFamilyMembers"))) != null){
                row.getCell(Integer.parseInt(metadata.getProperty("noOfEarningFamilyMembers")))
                        .setCellType(CellType.STRING);
                student.setNoOfEarningFamilyMembers(row.getCell(Integer.parseInt(
                        metadata.getProperty("noOfEarningFamilyMembers"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("totalHouseholdIncome"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("totalHouseholdIncome"))).setCellType(CellType.STRING);
                student.setTotalHouseholdIncome(row.getCell(Integer.parseInt(
                        metadata.getProperty("totalHouseholdIncome"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("ownGadget"))) != null){
                student.setOwnGadget(row.getCell(Integer.parseInt(
                        metadata.getProperty("ownGadget"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("courseName"))) != null){
                student.setCourseName(row.getCell(Integer.parseInt(
                        metadata.getProperty("courseName"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("collegeName"))) != null){
                student.setCollegeName(row.getCell(Integer.parseInt(
                        metadata.getProperty("collegeName"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("completedAcademicYear"))) != null){
                row.getCell(Integer.parseInt(
                        metadata.getProperty("completedAcademicYear"))).setCellType(CellType.STRING);
                student.setCompletedAcademicYear(row.getCell(Integer.parseInt(
                        metadata.getProperty("completedAcademicYear"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("about"))) != null){
                student.setAbout(row.getCell(Integer.parseInt(
                        metadata.getProperty("about"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("areaOfInterest"))) != null){
                student.setAreaOfInterest(row.getCell(Integer.parseInt(
                        metadata.getProperty("areaOfInterest"))).getStringCellValue());
            }
            if(row.getCell(Integer.parseInt(metadata.getProperty("hoursAllocation"))) != null){
                student.setHoursAllocation(row.getCell(Integer.parseInt(
                        metadata.getProperty("hoursAllocation"))).getStringCellValue());
            }
            student.setPassword("welcome123");
            student.setNeedsNgoApproval(false);
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Obtain Metadata for parsing Excel Sheet
     * @return
     */
    private Properties getExcelMetadata() {
        Resource resource = new ClassPathResource(metadataLocation);
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(resource);
        return yamlPropertiesFactoryBean.getObject();
    }

    /**
     * Delete Student record
     * @param email
     * @return
     */
    public String deleteUser(String email) {
        Student student = studentDetailsRepo.findByEmailId(email);
        if(student != null)  {
            studentDetailsRepo.delete(student);
            return "Deleted user " + email + " successfully!!";
        }else{
            return "User " + email + " not found.";
        }
    }

    /**
     * Gets the list of users that need ngo approval for registration
     * @return
     */
    public List<Student> getPendingApprovalRequests() {
        List<Student> studentList = studentDetailsRepo.findAllByNeedsNgoApproval(true);
        if(!studentList.isEmpty()){
            return studentList;
        }else{
            return new ArrayList<>();
        }
    }

    /**
     * Approve user registration
     * @param email
     * @return
     */
    public String approveUser(String email) {
        Student student = studentDetailsRepo.findByEmailId(email);
        if(student != null){
            student.setNeedsNgoApproval(false);
            studentDetailsRepo.save(student);
            return "Approved user : " + email ;
        }else{
            return "No user with email " + email + "found";
        }
    }
}

