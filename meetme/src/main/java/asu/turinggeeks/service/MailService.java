package asu.turinggeeks.service;

import java.io.FileWriter;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
	
	MimeMessage mimeMessage;
	MimeMessageHelper mimeMessageHelper;
	
	public void sendMail(String email){
		mimeMessage = this.mailSender.createMimeMessage();
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
	
	public void sendRequiredInvite(String email, Calendar calendar, String uuid){
		String firstName = calendarDao.getFirstName(email);
		mimeMessage = this.mailSender.createMimeMessage();
		try{
			InternetAddress[] inviteMails = InternetAddress.parse(calendar.getGuestRequiredEmail());
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setBcc(inviteMails);
			mimeMessage.setSubject("Meeting Invite from "+firstName+"");
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><br/> You have been invited by "+firstName+" for a meeting with an agenda of <b>"+calendar.getEventName()+"</b>."
					+ "The proposed meeting times by the invitee are present in the link provided below. You are <b>required</b> in this meeting so please fill up the form "
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

	public void sendOptionalInvite(String emailId, Calendar calendar, String uuid) {
		String firstName = calendarDao.getFirstName(emailId);
		mimeMessage = this.mailSender.createMimeMessage();
		try{
			InternetAddress[] inviteMails = InternetAddress.parse(calendar.getGuestOptionalEmail());
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setBcc(inviteMails);
			mimeMessage.setSubject("Meeting Invite from "+firstName+"");
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><br/> You have been invited by "+firstName+" for a meeting with an agenda of <b>"+calendar.getEventName()+"</b>."
					+ "The proposed meeting times by the invitee are present in the link provided below. You have been marked as <b>optional</b> for this meeting. Please fill up the form "
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

	public void sendPreferredTime(String emailId, String requiredPeople, String optionalPeople, List<String> reverseOutput) {
		String all = emailId+","+requiredPeople+","+optionalPeople;
		System.out.println(all);
		
		mimeMessage = this.mailSender.createMimeMessage();
		try{
			FileWriter writer = new FileWriter("output.txt");
			for(int i=0;i<reverseOutput.size();i++){
				writer.write("Rank"+(i+1)+":"+reverseOutput.get(i)+"\n");
			}
			writer.close();
			InternetAddress[] inviteMails = InternetAddress.parse(all);
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setBcc(inviteMails);
			mimeMessage.setSubject("Meeting Times!");
			mimeMessageHelper.setFrom("meetmetg@gmail.com");
			mimeMessageHelper.setText("<html><body>Hi,<br/><br/> We have attached the meeting times as per their preference in the attached file.</br>"
					+ "<br/><br/>Thanks and Best Regards,<br/> MeetMe Team</body></html>",true);
			FileSystemResource file = new FileSystemResource("output.txt");
			mimeMessageHelper.addAttachment("MeetingTime.txt", file);
			mailSender.send(mimeMessage);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Caused while Sending the mail" + e.getMessage());
		}
		
	}
}
