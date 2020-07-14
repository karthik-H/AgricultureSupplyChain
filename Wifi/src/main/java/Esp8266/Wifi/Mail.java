package Esp8266.Wifi;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public boolean sendMail(String mailAddress,String subject) {
		try {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("openagr@gmail.com", "Openagr@123456");
					}
				  });
		
		

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Openagr@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mailAddress));
			message.setSubject("Open Agr");
			
			message.setText(subject);

			Transport.send(message);
			
			
			System.out.println("Mail sent to :"+mailAddress);
			return true;
		} catch (Exception e) {
			return false;
		} 
	
	}
}
