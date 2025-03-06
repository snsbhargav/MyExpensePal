package com.MyExpensePal.MailingService.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MyExpensePal.MailingService.Model.UserDto;
import com.MyExpensePal.MailingService.Service.MailingService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailingServiceImpl implements MailingService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	RestTemplate restTemplate;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public String generateAndSendFullReport(String toEmail) throws MessagingException {
		
		UserDto user = getUserByUserEmail(toEmail);
		Resource generatedReport = generateExpenseReport(user.getUserId());

		return createAMalingObject(user, toEmail, generatedReport);

	}
	
	private String createAMalingObject(UserDto user, String toEmail, Resource generatedReport) throws MessagingException {
		String subject = "Expense Report - PDF Attached";
		String body = """
				Dear %s %s,

				As requested, please find your expense report in the attachment.

				Best regards,
				MyExpensePal
				""".formatted(user.getFirstName(), user.getLastName());

		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setText(body);
		helper.setSubject(subject);
		helper.addAttachment(generatedReport.getFilename(), generatedReport);
		
		javaMailSender.send(message);

		
		return "Sent Successfully";
	}

	private Resource generateExpenseReport(UUID userId) {
		return restTemplate.getForObject("lb://REPORT-GENERATION-SERVICE/generateReport/" + userId, Resource.class);
	}

	private UserDto getUserByUserEmail(String email) {
		return restTemplate.getForEntity("lb://AUTHENTICATION-SERVICE/auth/getUserByEmail/" + email, UserDto.class)
				.getBody();
	}

}
