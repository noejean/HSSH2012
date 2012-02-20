
package hssh.devicesMessages;

import hssh.devices.BinaryRemoteDevice;


/**
 *
 * @author Xavier
 */
public class PresenceDetectorMsg
	extends BinaryRemoteDeviceMsg
{
	/* CONSTRUCTORS */

	public PresenceDetectorMsg(BinaryRemoteDeviceMsg.BinaryState state)
	{
		super(state);
	}

	public PresenceDetectorMsg(BinaryRemoteDeviceMsg.BinaryState state, BinaryRemoteDevice sender)
	{
		super(state, sender);
	}
}
