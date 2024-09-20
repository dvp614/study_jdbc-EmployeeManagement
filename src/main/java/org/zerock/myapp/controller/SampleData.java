package org.zerock.myapp.controller;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SampleData {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Double salary;
	private Double commissionPct;
	private int managerId;
	private int departmentId;
	
	@Override
	public String toString() {
	    return "SampleData{" +
	            "employeeId=" + employeeId +
	            ", firstName='" + firstName + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", email='" + email + '\'' +
	            ", phoneNumber='" + phoneNumber + '\'' +
	            ", hireDate=" + hireDate +
	            ", jobId='" + jobId + '\'' +
	            ", salary=" + salary +
	            ", commissionPct=" + commissionPct +
	            ", managerId=" + managerId +
	            ", departmentId=" + departmentId +
	            '}';
	}
} // end class
