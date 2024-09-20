package org.zerock.myapp.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpSampleData {
	private String departmentName;
	private int employeeCount;
	
	@Override
	public String toString() {
	    return "SampleData{" +
	            "employeeCount=" + employeeCount +
	            ", departmentName='" + departmentName + '\'' +
	            '}';
	}
} // end class
