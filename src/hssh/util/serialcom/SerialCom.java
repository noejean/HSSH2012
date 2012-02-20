
package hssh.util.serialcom;

import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 *
 * @author Xavier
 */
public class SerialCom
{
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;

	private String portName;

	SerialPort serialPort;

	/** Buffered input stream from the port */
	private InputStream input;

	/** The output stream to the port */
	private OutputStream output;


	/* CONSTRUCTORS */

	public SerialCom(String portName, int dataRate)
	{
		System.setProperty("gnu.io.rxtx.SerialPorts", portName);
		this.portName = portName;
		this.init(dataRate);
	}


	/* METHODS */

	private void init(int dataRate)
	{
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			if (currPortId.getName().equals(this.portName)) {
				portId = currPortId;
				break;
			}
		}

		if (portId == null) {
			System.err.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(dataRate,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void addSerialEventListener(SerialPortEventListener spel)
	{
		try {
			serialPort.addEventListener(spel);
			serialPort.notifyOnDataAvailable(true);
		}
		catch (TooManyListenersException ex) {
			System.err.println(ex.toString());
		}
	}


	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close()
	{
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public String lastMessage()
	{
		byte chunk[] = null;

		try {
			int available = input.available();
			chunk = new byte[available];
			input.read(chunk, 0, available);
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}

		return new String(chunk);
	}
}

