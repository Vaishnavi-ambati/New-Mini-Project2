package com.tech.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.tech.entity.citizenPlanInfo;
import com.tech.repository.ReportsRepository;
import com.tech.service.ReportsService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServiceImpl implements ReportsService{
	
	@Autowired
	private ReportsRepository reportsRepoObj;

	@Override
	public List<citizenPlanInfo> getRecordsFiltered(String planName, String planStatus, String gender, LocalDate startDate, LocalDate endDate) {
		
		citizenPlanInfo tempInfo = new citizenPlanInfo();
		
		if(StringUtils.isNotBlank(planName)) {
			tempInfo.setPLAN_NAME(planName);
		}
		
		if(StringUtils.isNotBlank(planStatus)) {
			tempInfo.setPLAN_STATUS(planStatus);
		}
		
		if(StringUtils.isNotBlank(gender)) {
			tempInfo.setGENDER(gender);
		}
		
		if(startDate !=null)
			tempInfo.setPLAN_START_DATE(startDate);
		
		if(endDate !=null)
			tempInfo.setPLAN_END_DATE(endDate);
		
		Example<citizenPlanInfo> expOf = Example.of(tempInfo);
		
		System.out.println("expOf: "+expOf);
		
		List<citizenPlanInfo> output = reportsRepoObj.findAll(expOf);
		
		System.out.println("output: "+output);
		
		return output;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return reportsRepoObj.getUniquePlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return reportsRepoObj.getUniquePlanStatus();
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception{
		List<citizenPlanInfo> records = reportsRepoObj.findAll();
		
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Data");
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("SSN");
		
		int rowIndex = 1;
		
		for(citizenPlanInfo record: records) {
			HSSFRow dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(record.getNAME());
			dataRow.createCell(1).setCellValue(record.getEMAIL());
			dataRow.createCell(2).setCellValue(record.getGENDER());
			dataRow.createCell(3).setCellValue(record.getPLAN_NAME());
			dataRow.createCell(4).setCellValue(record.getPLAN_STATUS());
			dataRow.createCell(5).setCellValue(record.getSSN());
			rowIndex++;
		}
		
		ServletOutputStream outStream = response.getOutputStream();
		book.write(outStream);
		book.close();
		outStream.close();		
	}
	
	@Override
	public void generatePdf(HttpServletResponse response) throws Exception{
		List<citizenPlanInfo> records = reportsRepoObj.findAll();
		
		Document pdfDoc = new Document(PageSize.A4);
		
		ServletOutputStream outStream = response.getOutputStream();
		PdfWriter.getInstance(pdfDoc, outStream);
		
		pdfDoc.open();
		
		Paragraph p = new Paragraph("Citizens Plan Info");
		pdfDoc.add(p);
		
		pdfDoc.close();
		outStream.close();
		
		
	}

}
