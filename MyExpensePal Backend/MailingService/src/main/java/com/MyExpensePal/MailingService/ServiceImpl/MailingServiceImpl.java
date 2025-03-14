package com.MyExpensePal.MailingService.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	public String generateAndSendFullReport(UUID userId) throws MessagingException {
		//Creating an entity with userId in it.
		HttpHeaders headers = new HttpHeaders();
		headers.add("userId", userId.toString());
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		UserDto user = getUser(entity);
		Resource generatedReport = generateExpenseReport(entity);

		return createAMalingObject(user, generatedReport);

	}

	private String createAMalingObject(UserDto user, Resource generatedReport) throws MessagingException {
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
		helper.setTo(user.getEmail());
		helper.setText(body);
		helper.setSubject(subject);
		helper.addAttachment(generatedReport.getFilename(), generatedReport);

		javaMailSender.send(message);

		return "Sent Successfully";
	}

	private Resource generateExpenseReport(HttpEntity<String> entity) {
		return restTemplate.exchange("lb://REPORT-GENERATION-SERVICE/report/generateReport", HttpMethod.GET, entity,
				Resource.class).getBody();
	}

	private UserDto getUser(HttpEntity<String> entity) {
		return restTemplate.exchange("lb://AUTHENTICATION-SERVICE/auth/getUser", HttpMethod.GET, entity, UserDto.class)
				.getBody();
	}

}
