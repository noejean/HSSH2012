
package hssh.inforeceive;

import hssh.devicesMessages.RemoteDeviceMessage;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import hssh.devices.RemoteDevice;
import hssh.util.serialcom.*;


/**
 *
 * @author Xavier
 */
public class RemoteDeviceReader
	implements SerialPortEventListener
{
	public static final byte DEVICEID_MASK = (byte) 0xF8;

	/** Remote devices known by the remote device info reader */
	private RemoteDevices remoteDevices;

	/** Serial communicator */
	private SerialCom sc;


	/* CONSTRUCTORS */

	public RemoteDeviceReader(String device, int dataRate)
	{
		this.remoteDevices = new RemoteDevices();
		this.sc = new SerialCom(device, dataRate);
		this.sc.addSerialEventListener(this);
	}

	public RemoteDeviceReader(String device, int dataRate, String xmlFile)
	{
		this(device, dataRate);
		this.remoteDevices = new RemoteDevices(xmlFile);
	}


	/* ACCESSORS */

	
	/* METHODS */

	public static int getDeviceId(byte msg)
	{
		return (msg & DEVICEID_MASK) >> 3;
	}

	public static int getContent(byte msg)
	{
		return msg & ~DEVICEID_MASK;
	}

	public void stop()
	{
		this.sc.close();
	}

	public RemoteDeviceMessage create(byte msg)
		throws UnknowRemoteDeviceException
	{
		RemoteDevice src = this.remoteDevices.get(RemoteDeviceReader.getDeviceId(msg));
		return (RemoteDeviceMessage) src.newMessage(msg);
	}

	
	/* METHODS : SerialPortEventListener */

	@Override
	public void serialEvent(SerialPortEvent oEvent)
	{
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
		{
			byte msg = this.sc.lastMessage().getBytes()[0];
			System.out.println("message brut : "+msg+", devide id : "+getDeviceId(msg)+", content : "+getContent(msg));
			
			try {
				RemoteDeviceMessage di = this.create(msg);
				System.out.println(di);
			}
			catch (UnknowRemoteDeviceException ex) {
				System.out.println("Unknow device");
			}
		}
	}


	/* METHODS : Object */
	
	public String toString()
	{
		return this.remoteDevices.toString();
	}
}
