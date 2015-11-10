package asu.turinggeeks.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.CalendarDao;
import asu.turinggeeks.model.Calendar;

@Service
public class MailService {
	
	@Autowired
	CalendarDao calendarDao;
	
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
	
	public void sendInvite(String email, Calendar calendar, String uuid){
		String firstName = calendarDao.getFirstName(email);
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try{
			InternetAddress[] inviteMails = InternetAddress.parse(calendar.getGuestEmail());
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setBcc(inviteMails);
			mimeMessage.setSubject("Meeting Invite from "+firstName+"");
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><br/> You have been invited by "+firstName+" for a meeting with an agenda of "+calendar.getEventName()+"."
					+ "The proposed meeting times by the invitee are present in the link provided below. Please fill up the form "
					+ "and submit it with your favourable time. We will get back to you with a favourable meeting time. All timings are in MST(Mountain Standard Time). <br/>"
					+ "  <a href='http://localhost:8080/meetme/submitTiming/"+uuid+"'> Click here</a>. "
					+ "<br/><br/>Thanks and Best Regards,<br/> MeetMe Team</body></html>",true);
			mailSender.send(mimeMessage);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Caused while Sending the mail" + e.getMessage());
		}
	}
}
