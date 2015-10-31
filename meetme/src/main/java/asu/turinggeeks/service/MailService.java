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
			mimeMessage.setSubject("Reset Password Link");
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><br/> Please use the link given to reset your password. <a href='http://localhost:8080/meetme/newPassword/"+email+"'> Click here</a>. "
					+ "<br/><br/>Thanks and Best Regards,<br/> MeetMe Team</body></html>",true);
			mailSender.send(mimeMessage);
		}
		catch(Exception e){
			System.out.println("Error Caused while Sending the mail" + e.getMessage());
		}
	}
}
