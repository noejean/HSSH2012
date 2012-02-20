
package hssh.actions;


/**
 * Launch the alarm
 * @author Xavier
 */
public class Alarm
	extends Action
{
	/** num er of seconds of ringing */
	private int secs;


	/* CONSTRUCTORS */

	public Alarm(int secs)
	{
		this.secs = secs;
	}


	/* ACCESSORS */


	/* METHODS */

	@Override
	public void run()
	{
		long now = System.currentTimeMillis();
		while (now < now + (this.secs*1000))
		{
			System.out.println("Alarme sone");

			try {
				this.sleep(1000);
			}catch (InterruptedException ex) {}
		}
	}
}
