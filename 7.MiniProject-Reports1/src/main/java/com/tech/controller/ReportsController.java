package com.tech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tech.entity.citizenPlanInfo;
import com.tech.service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;



@Controller
public class ReportsController {
	
	@Autowired
	private ReportsService reportsServiceObj;
	
	@GetMapping("/")
	public String getHomePage(Model m) {
		
		citizenPlanInfo reportObj = new citizenPlanInfo();
		
		loadFormData(m);
		m.addAttribute("reportAttr", reportObj);
		
		return "index";
	}

	private void loadFormData(Model m) {
		List<String> uniquePlanNames = reportsServiceObj.getUniquePlanNames();
		List<String> uniquePlanStatus = reportsServiceObj.getUniquePlanStatus();
		
		m.addAttribute("uniquePN", uniquePlanNames);
		m.addAttribute("uniquePS", uniquePlanStatus);
	}
	
	@PostMapping("/")
	public String handleHomePage(Model m, @ModelAttribute("reportAttr") citizenPlanInfo reportObj) {
		
		System.out.println("post / map reached");
		loadFormData(m);
		System.out.println("reportObj: "+reportObj);

		List<citizenPlanInfo> recordsFound = reportsServiceObj.getRecordsFiltered(reportObj.getPLAN_NAME(),reportObj.getPLAN_STATUS(), 
				reportObj.getGENDER(), reportObj.getPLAN_START_DATE(), reportObj.getPLAN_END_DATE());
		System.out.println("recordsFound: "+recordsFound);
		
		m.addAttribute("reportOutputAttr", recordsFound);
		reportObj = new citizenPlanInfo();

		m.addAttribute("reportAttr", reportObj);
		
		return "index";
	}
	
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";
		
		response.addHeader(headerKey, headerValue);
		
		reportsServiceObj.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void downloadPdf(HttpServletResponse response) throws Exception{
response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		
		response.addHeader(headerKey, headerValue);
		
		reportsServiceObj.generateExcel(response);
	}

}
