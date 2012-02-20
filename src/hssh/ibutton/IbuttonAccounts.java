
package hssh.ibutton;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Xavier
 */
public class IbuttonAccounts
{
	private List<IbuttonAccount> iaccounts;


	/* CONSTRUCTORS */

	public IbuttonAccounts()
	{
		this.iaccounts = new ArrayList<IbuttonAccount>();
	}

	public IbuttonAccounts(String xmlFile)
	{
		this();
		this.loadFromXml(xmlFile);
	}


	/* METHODS */

	public void loadFromXml(String xmlFile)
	{
		SAXBuilder sxb = new SAXBuilder();
		org.jdom.Document document = null;
		try
		{
			document = sxb.build(new File(xmlFile));

			List xmlIAccounts = document.getRootElement().getChildren("iAccount");
			Iterator i = xmlIAccounts.iterator();
			while (i.hasNext())
			{
				Element xmlIAccount = (Element)i.next();

				IbuttonAccount iAccount = new IbuttonAccount(
					IbuttonAccountType.valueOf(xmlIAccount.getAttributeValue("type").toUpperCase()),
					xmlIAccount.getAttributeValue("ibuttonId"),
					xmlIAccount.getAttributeValue("name")
					);

				this.iaccounts.add(iAccount);
			}
		}
		catch(Exception e) {
			System.err.println("Loading XML file failed : "+e.getMessage());
		}
	}

	public boolean contains(String ibuttonid)
	{
		IbuttonAccount iba = new IbuttonAccount(ibuttonid);
		return this.iaccounts.contains(iba);
	}

	public IbuttonAccount get(String ibuttonid)
		throws UnknowIbuttonAccountException
	{
		IbuttonAccount iba = new IbuttonAccount(ibuttonid);
		
		if (this.contains(ibuttonid))
			return this.iaccounts.get(this.iaccounts.indexOf(iba));

		else
			throw new UnknowIbuttonAccountException("ID button "+ibuttonid+" is unknow");
	}


	/* METHODS : object */

	@Override
	public String toString()
	{
		String str = "ibutton accounts:\n";
		Iterator i = this.iaccounts.iterator();
		while (i.hasNext())
		{
			str += i.next()+"\n";
		}
		return str;
	}
}

class UnknowIbuttonAccountException extends Exception
{
	UnknowIbuttonAccountException(String string) {
		super(string);
	}
}
