package com.tech.service;

import org.springframework.stereotype.Service;

import com.tech.entity.citizenPlanInfo;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportsService {
	
	public List<citizenPlanInfo> getRecordsFiltered(String planName, String planStatus, String gender, LocalDate startDate, LocalDate endDate);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	public void generateExcel(HttpServletResponse response) throws Exception;
	
	public void generatePdf(HttpServletResponse response) throws Exception;

}
