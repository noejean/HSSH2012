
package hssh.devices;

import hssh.devicesMessages.BinaryRemoteDeviceMsg.BinaryState;
import hssh.devicesMessages.ContactorMsg;


/**
 * A contactor is a remote device that can send if it is open or close (its
 * state) and its battery state.
 * @author Xavier
 */
public class Contactor
	extends BinaryRemoteDevice
{
	/* CONSTRUCTORS */


	/* ACCESSORS */


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return "Contactor : id="+this.id+" name="+this.name;
	}


	/* METHODS : RemoteDevice */

	@Override
	public ContactorMsg newMessage(byte msg)
	{
		ContactorMsg ci = null;

		// contactor state
		if ((msg & 0x04) == 0x04)
		{
			if (((msg & 0x01) == 0x01))
				ci = new ContactorMsg(BinaryState.NOK, this);
			else
				ci = new ContactorMsg(BinaryState.OK, this);
		}

		return ci;
	}
}
