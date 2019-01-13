package com.kometsales.flowers.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	/**
	 * Send an email to the client
	 * @param mail
	 */
	public void sendEmail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject(mail.getSubject());
		message.setText(mail.getBody());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());

		javaMailSender.send(message);
	}
}
