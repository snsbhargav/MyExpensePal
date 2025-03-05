package com.MyExpensePal.ReportGenerationService.Controller;

import java.io.FileNotFoundException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.ReportGenerationService.Service.ReportGenerationService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportGeneratorController {
	
	@Autowired
	ReportGenerationService generationService;
	
	@GetMapping("/generateReport/{userId}")
	public String generateReport(@PathVariable UUID userId) throws FileNotFoundException, JRException {
		return generationService.exportReport(userId);
	}

}
