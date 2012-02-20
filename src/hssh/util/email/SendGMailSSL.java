
package hssh.util.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendGMailSSL extends Thread
{
	private String username;
	private Properties props;
	private Session session;

	private Email email;
	

	/* CONSTRUCTOR */

	public SendGMailSSL(String username, String password)
	{
		this.initSslCnx(username, password);
	}


	/* METHODS */

	private void initSslCnx(final String username, final String password)
	{
		this.username = username;
		this.props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		this.session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
			@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
	}

	public void sendMail(Email email)
	{
		this.email = email;
		this.start();
	}

	@Override
	public void run()
	{
		if (this.email == null)
			throw new RuntimeException("No email to send");

		try {
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(this.username+"@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.email.getRecipient()));
			message.setSubject(this.email.getSubject());
			message.setText(this.email.getMessage());

			Transport.send(message);

			this.email = null;
		}
		catch (javax.mail.AuthenticationFailedException e) {
			System.err.println("Bad username / password");
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

