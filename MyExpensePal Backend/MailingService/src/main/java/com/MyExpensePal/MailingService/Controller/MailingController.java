package com.MyExpensePal.MailingService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.MailingService.Service.MailingService;

import jakarta.mail.MessagingException;

@RestController
public class MailingController {
	
	@Autowired
	private MailingService mailingService;
	
	
	@GetMapping("/sendAllExpensesReport/{recipient}")
	public String sendMail(@PathVariable String recipient) throws MessagingException {
		return mailingService.generateAndSendFullReport(recipient);
	}

}
