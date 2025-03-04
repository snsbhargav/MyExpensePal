package com.MyExpensePal.ReportGenerationService.Controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.ReportGenerationService.Service.ReportGenerationService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportGeneratorController {
	
	@Autowired
	ReportGenerationService generationService;
	
	@GetMapping("/generateReport")
	public String generateReport() throws FileNotFoundException, JRException {
		return generationService.exportReport("");
	}

}
