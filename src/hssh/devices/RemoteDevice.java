
package hssh.devices;

import hssh.devicesMessages.RemoteDeviceMessage;


/**
 * A remote device is a device that send information by means of a wireless
 * communication card.
 * @author Xavier
 */
public abstract class RemoteDevice
	extends Device
{
	/** ID used in communication frames */
	protected int id;

	
	/* CONSTRUCTORS */

	public RemoteDevice()
	{	
	}

	public RemoteDevice(int id)
	{
		this.id = id;
	}

	public RemoteDevice(int id, String name)
	{
		this(id);
		this.name = name;
	}


	/* ACCESSORS */

	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	

	/* METHODS */

	
	/* METHODS : Object */

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj instanceof RemoteDevice)
		{
			RemoteDevice other = (RemoteDevice) obj;
			return this.id == other.id;
		}

		return false;
	}
}
