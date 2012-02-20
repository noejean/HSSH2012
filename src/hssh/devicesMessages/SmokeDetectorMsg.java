
package hssh.devicesMessages;

import hssh.devices.BinaryRemoteDevice;


/**
 *
 * @author Xavier
 */
public class SmokeDetectorMsg
	extends BinaryRemoteDeviceMsg
{
	/* CONSTRUCTORS */

	public SmokeDetectorMsg(BinaryRemoteDeviceMsg.BinaryState state)
	{
		super(state);
	}

	public SmokeDetectorMsg(BinaryRemoteDeviceMsg.BinaryState state, BinaryRemoteDevice sender)
	{
		super(state, sender);
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return "Smoke detection - "+super.toString();
	}
}
