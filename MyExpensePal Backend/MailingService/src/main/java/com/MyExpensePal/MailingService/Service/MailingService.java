package com.MyExpensePal.MailingService.Service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public interface MailingService {

	String generateAndSendFullReport(String toEmail) throws MessagingException;

}
