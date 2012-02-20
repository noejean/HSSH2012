
package hssh.util.email;

/**
 *
 * @author Xavier
 */
public class Email
{
	private String subject;
	private String message;
	private String recipient;


	/* CONSTRUCTORS */

	public Email(String subject, String msg, String recipient)
	{
		this.subject = subject;
		this.message = msg;
		this.recipient = recipient;
	}


	/* ACCESSORS */

	public String getSubject() { return this.subject; }
	public String getMessage() { return this.message; }
	public String getRecipient() { return this.recipient; }

}

