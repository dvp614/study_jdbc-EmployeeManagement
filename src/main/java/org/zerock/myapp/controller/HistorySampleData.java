package org.zerock.myapp.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HistorySampleData {
	private int employeeId;
	private String jobId;
	
	@Override
	public String toString() {
	    return "SampleData{" +
	            "employeeId=" + employeeId +
	            ", jobId='" + jobId + '\'' +
	            '}';
	}
} // end class
