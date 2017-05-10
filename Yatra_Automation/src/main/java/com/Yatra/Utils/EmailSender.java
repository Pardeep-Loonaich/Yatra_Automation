package com.Yatra.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.Reporter;

public class EmailSender {

	private  String testCaseId="";
	private   String sCurrentPageURL="";
	public static  String sPricingURL="";
	private  String fileName="";



	EnvironmentPropertiesReader propReader=EnvironmentPropertiesReader.getInstance();
	/**
	 * 
	 * @param sScreenshotURL
	 * @param testCaseId
	 * @param sCurrentPageURL
	 * @param sPricingURL
	 */
	public EmailSender(String sPricingURL)
	{
		this.sPricingURL=sPricingURL;
	}
	public EmailSender(String fileName, String testCaseId,String sCurrentPageURL)
	{
		this.fileName=fileName;
		this.sPricingURL=sPricingURL;
		this.sCurrentPageURL=sCurrentPageURL;
		this.testCaseId=testCaseId;
	}

	/**
	 * Description: sent Email
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException 
	 */
	public void sendHtmlEmail() throws AddressException,MessagingException, IOException 
	{
		String  Email_BODY_TEXT = "Hi Team,<br><br>"
				+ "TestCase <b>'"+testCaseId+"'</b> has been failed, please find the details below:"
				+ "<br>Current Page URL: <b><font color=blue>"+sCurrentPageURL+"</font></b>"
				+ "<br>"
				+ "<br>"
				+ "<br>"
				+ "<br>Pricing URL: <b><font color=blue>"+sPricingURL+"</font></b>"
				+ "<br>"
				+ "<br>"
				+ "Note:- Please find screenshot in Attachment. </b>"

				+ "<br>"
				+ "<br>"
				+ "Thanks & Regards<br>"
				+ "Automation Team"				
				+ "<font color=red></font>";

		String toAddress=propReader.getProperty("toAddress");
		String toAddressArray[] =toAddress.split("\\|");
		//System.out.println(toAddressArray[1]);

		// sets SMTP server properties
		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(propReader.getProperty("userName"), propReader.getProperty("password"));
			}
		};
		Properties properties =setMailProperties();
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		//set from email field
		msg.setFrom(new InternetAddress(propReader.getProperty("userName")));

		for(String toEmail:toAddressArray)
		{
			InternetAddress[] toAddresses = {  };
			//msg.addRecipient(Message.RecipientType.TO, toAddresses);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

		}
		//set mail subject
		msg.setSubject(propReader.getProperty("subject"));
		//set send  date for mail
		msg.setSentDate(new Date());
		//set file attachment
		File screenShotFolder = new File(Reporter.getCurrentTestResult().getTestContext().getOutputDirectory());
		String screenShotFolderPath = screenShotFolder.getParent() + File.separator + "ScreenShot" + File.separator;
		screenShotFolder = new File(screenShotFolderPath);
		MimeBodyPart mesgFilePart=new MimeBodyPart();
		DataSource source=new FileDataSource(screenShotFolder.getAbsolutePath()+File.separator+fileName);
		mesgFilePart.setDataHandler(new DataHandler(source));
		mesgFilePart.setFileName(fileName);
		// set plain text message
		MimeBodyPart mimeBodyPart=new MimeBodyPart();
		mimeBodyPart.setContent(Email_BODY_TEXT, "text/html");

		MimeMultipart multiPart=new MimeMultipart();
		multiPart.addBodyPart(mesgFilePart);
		multiPart.addBodyPart(mimeBodyPart);


		msg.setContent(multiPart);
		//msg.setContent(Email_BODY_TEXT, "text/html");//
		// sends the e-mail
		Transport.send(msg);



	}

	/**
	 * DEscription: set mail properties
	 * @return: it will return properties object
	 */
	private Properties setMailProperties()

	{
		Properties properties = new Properties();
		properties.put("mail.smtp.host", propReader.getProperty("host"));
		properties.put("mail.smtp.port", propReader.getProperty("port"));
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		return properties;
	}//setMailProperties
}