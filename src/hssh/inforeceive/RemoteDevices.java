
package hssh.inforeceive;

import hssh.devices.RemoteDevice;
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
public class RemoteDevices
{
	private static final String DEVICES_PACKAGE = "hssh.devices";

	private List<RemoteDevice> remoteDevices;


	/* CONSTRUCTORS */
	
	public RemoteDevices()
	{
		this.remoteDevices = new ArrayList<RemoteDevice>();
	}

	public RemoteDevices(String xmlFile)
	{
		this();
		this.loadFromXml(xmlFile);
	}

	
	/* ACCESSORS */

	/* METHODS */

	private void loadFromXml(String xmlFile)
	{
		SAXBuilder sxb = new SAXBuilder();
		org.jdom.Document document = null;
		try
		{
			document = sxb.build(new File(xmlFile));

			List xmlIAccounts = document.getRootElement().getChildren("remotedevice");
			Iterator i = xmlIAccounts.iterator();
			while (i.hasNext())
			{
				RemoteDevice rd = null;
				Element xmlRemotdevice = (Element)i.next();

				// Créé un objet de type 'type'
				Class classe = Class.forName(DEVICES_PACKAGE+"."+xmlRemotdevice.getAttributeValue("type"));
				rd = (RemoteDevice) classe.newInstance();
				
				rd.setId(Integer.parseInt(xmlRemotdevice.getAttributeValue("id")));
				rd.setName(xmlRemotdevice.getAttributeValue("name"));

				this.addRemoteDevice(rd);
			}
		}
		catch(AlreadyExistException e) {
			System.err.println("Erreur : plusieurs remote devices ont le même ID dans le fichier XML.");
		}
		catch(Exception e) {
			System.err.println("Loading XML file failed : "+e);
		}
	}

	public void addRemoteDevice(RemoteDevice rd)
		throws AlreadyExistException
	{
		if (this.remoteDevices.contains(rd))
			throw new AlreadyExistException();

		this.remoteDevices.add(rd);
	}

	public int indexOf(int remoteDeviceId)
	{
		for (RemoteDevice rd : this.remoteDevices)
		{
			if (rd.getId() == remoteDeviceId)
				return this.remoteDevices.indexOf(rd);
		}

		return -1;
	}


	public RemoteDevice get(int remoteDeviceId)
		throws UnknowRemoteDeviceException
	{
		int index = this.indexOf(remoteDeviceId);

		if (index != -1)
			return this.remoteDevices.get(index);

		throw new UnknowRemoteDeviceException();
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		String str = "Remote devices:\n";
		
		Iterator i = this.remoteDevices.iterator();
		while (i.hasNext())
			str += i.next()+"\n";
		
		return str;
	}
}

class UnknowRemoteDeviceException
	extends Exception {}

class AlreadyExistException
	extends Exception {}
