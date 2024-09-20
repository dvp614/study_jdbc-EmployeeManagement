package org.zerock.myapp.controller;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SalarySampleData {
	private String name;
	private String departmentName;
	private Date hireDate;
	private String jobId;
	private Double salary;
	private String gradeLevel;
	
	@Override
	public String toString() {
	    return "SampleData{" +
	            "name=" + name +
	            ", departmentName='" + departmentName + '\'' +
	            ", hireDate='" + hireDate + '\'' +
	            ", jobId='" + jobId + '\'' +
	            ", salary='" + salary + '\'' +
	            ", gradeLevel=" + gradeLevel +
	            '}';
	}
} // end class
