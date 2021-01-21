package com.poc.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {

    @Id
    private String id;
    private String name;
    private Long salary;
    private String className;

    public Employee(){

    }

    public Employee(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();
        this.salary = emp.getSalary();
        this.className = emp.getClassName();
    }

    public Employee(String id, String name, Long salary, String className) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}