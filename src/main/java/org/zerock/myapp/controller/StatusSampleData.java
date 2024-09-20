package org.zerock.myapp.controller;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusSampleData {
	private String city;
	private int employeeId;
	private String fullName;
	private String jobId;
	private Date hireDate;
	
	@Override
	public String toString() {
	    return "SampleData{" +
	    		"city='" + city + '\'' +
	            ",employeeId=" + employeeId +
	            ", fullName='" + fullName + '\'' +
	            ", jobId=" + jobId +
	            ", hireDate='" + hireDate + '\'' +
	            '}';
	}
} // end class
