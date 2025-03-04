package com.MyExpensePal.ReportGenerationService.Service;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;

@Service
public interface ReportGenerationService {
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

}
