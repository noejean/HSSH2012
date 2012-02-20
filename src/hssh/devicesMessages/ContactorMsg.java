
package hssh.devicesMessages;

import hssh.devices.BinaryRemoteDevice;


/**
 * Message sent by a Contactor
 * @author Xavier
 */
public class ContactorMsg
	extends BinaryRemoteDeviceMsg
{
	/* CONSTRUCTORS */

	public ContactorMsg(BinaryRemoteDeviceMsg.BinaryState state)
	{
		super(state);
	}

	public ContactorMsg(BinaryRemoteDeviceMsg.BinaryState state, BinaryRemoteDevice sender)
	{
		super(state, sender);
	}
}
