
package hssh.devicesMessages;

import hssh.devices.BinaryRemoteDevice;


/**
 *
 * @author Xavier
 */
public class BinaryRemoteDeviceMsg
	extends RemoteDeviceMessage
{
	/** possible values for the state */
	public enum BinaryState { OK, NOK }

	/** state of the contactor */
	private BinaryState state;


	/* CONSTRUCTORS */

	public BinaryRemoteDeviceMsg(BinaryState state)
	{
		this.state = state;
	}

	public BinaryRemoteDeviceMsg(BinaryState state, BinaryRemoteDevice sender)
	{
		this(state);
		this.sender = sender;
	}


	/* ACCESSORS */

	public BinaryState getState() { return this.state; }


	/* METHODS Object */

	@Override
	public String toString()
	{
		return "sender: "+this.sender+", state: "+this.state.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		BinaryRemoteDeviceMsg other = (BinaryRemoteDeviceMsg) obj;
		return this.state == other.state;
	}
}
