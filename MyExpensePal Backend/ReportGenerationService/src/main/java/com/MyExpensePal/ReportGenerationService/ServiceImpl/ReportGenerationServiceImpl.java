package com.MyExpensePal.ReportGenerationService.ServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

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

	@Override
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		
		List<ExpensesModel> expenseList = new ArrayList<>();
		expenseList.add(new ExpensesModel(1L,new UUID(0, 0), "pizza", 20, ExpenseType.FOOD,"Bridgeport, RockyHill", new Date(), new Date()));
		expenseList.add(new ExpensesModel(2L,new UUID(0, 0), "pizza", 20, ExpenseType.FOOD,"Bridgeport, RockyHill", new Date(), new Date()));
		File file = ResourceUtils.getFile("classpath:ExpenseReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(expenseList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Created By", "Bhargav");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\snsbh\\Bhargav\\UB\\Sem 4\\MS Project\\MyExpensePal Application\\MyExpensePal Backend\\ReportGenerationService\\src\\main\\resources\\"+"ExpenseReport.pdf");
		return "report generated.";
	}

}
