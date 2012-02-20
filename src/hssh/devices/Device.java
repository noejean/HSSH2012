
package hssh.devices;

import hssh.devicesMessages.DeviceMessage;


/**
 * Device
 * @author Xavier
 */
public abstract class Device
{
	/** name of the device, ex: North window contactor */
	protected String name;


	/* CONSTRUCTORS */

	public Device()
	{
	}

	public Device(String name)
	{
		this.name = name;
	}


	/* ACCESSORS */

	public String getName() { return this.name; }

	public void setName(String name) { this.name = name; }
	

	/* METHODS */

	/**
	 * Message that the device has to send.
	 * It can contain its state, for example.
	 * @param msg : raw frame sent
	 * @return message converted to a DeviceMessage
	 */
	public abstract DeviceMessage newMessage(byte msg);
}
