package asu.turinggeeks.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String email){
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try{
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(email);
			
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><a href='http://localhost:8080/meetme/newPassword/{email}'> Click here</a> to reset password</body></html>",true);
			mailSender.send(mimeMessage);
		}
		catch(Exception e){
			System.out.println("Error Caused while Sending the mail" + e.getMessage());
		}
	}
}
