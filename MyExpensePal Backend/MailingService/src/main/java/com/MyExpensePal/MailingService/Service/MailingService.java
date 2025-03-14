package com.MyExpensePal.MailingService.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public interface MailingService {

	String generateAndSendFullReport(UUID userId) throws MessagingException;

}
