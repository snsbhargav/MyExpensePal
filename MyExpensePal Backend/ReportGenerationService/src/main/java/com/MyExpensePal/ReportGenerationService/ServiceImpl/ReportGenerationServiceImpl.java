package com.MyExpensePal.ReportGenerationService.ServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.MyExpensePal.ReportGenerationService.Model.ExpenseType;
import com.MyExpensePal.ReportGenerationService.Model.ExpensesModel;
import com.MyExpensePal.ReportGenerationService.Service.ReportGenerationService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Component
public class ReportGenerationServiceImpl implements ReportGenerationService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String exportReport(UUID userId) throws FileNotFoundException, JRException {
		
//		List<ExpensesModel> expenseList = new ArrayList<>();
		List<ExpensesModel> expenseList = retrieveExpenseList(userId);
//		expenseList.add(new ExpensesModel(1L,new UUID(0, 0), "pizza", 20, ExpenseType.FOOD,"Bridgeport, RockyHill", new Date(), new Date()));
//		expenseList.add(new ExpensesModel(2L,new UUID(0, 0), "pizza", 20, ExpenseType.FOOD,"Bridgeport, RockyHill", new Date(), new Date()));
		File file = ResourceUtils.getFile("classpath:ExpenseReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(expenseList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Created By", "Bhargav");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, "src\\main\\resources\\ExpenseReport.pdf");
		return "report generated.";
	}
	
	private List<ExpensesModel> retrieveExpenseList(UUID userId){
		String api = "lb://MY-EXPENSE-PAL/expense/userId/"+userId;
		return restTemplate.getForEntity(api, List.class).getBody();
	}

}
