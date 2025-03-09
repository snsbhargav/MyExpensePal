package com.MyExpensePal.ReportGenerationService.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;

@Service
public interface ReportGenerationService {
	
	public ResponseEntity<Resource> exportReport(UUID userID) throws FileNotFoundException, JRException;

}
