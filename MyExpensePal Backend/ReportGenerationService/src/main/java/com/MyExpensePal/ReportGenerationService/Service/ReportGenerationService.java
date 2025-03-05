package com.MyExpensePal.ReportGenerationService.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;

@Service
public interface ReportGenerationService {
	
	public String exportReport(UUID userID) throws FileNotFoundException, JRException;

}
