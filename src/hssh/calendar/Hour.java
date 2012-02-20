
package hssh.calendar;


/**
 * Hour (time)
 * @author Xavier
 */
public class Hour
	implements Comparable<Hour>
{
	/** int value of the hour (0..23), ex: 12 means midday */
	private int hour;

	/** int value of minute (0..59) */
	private int minute;


	/* CONSTRUCTOR */

	public Hour(int hour, int minute)
	{
		try {
			this.setHour(hour);
			this.setMinute(minute);
		}
		catch (HourException ex) {
			System.err.println(ex.getMessage());
		}
	}


	/* ACCESSORS */

	public int getHour() { return this.hour; }
	public int getMinute() { return this.minute; }

	public void setHour(int hour) throws HourException
	{
		if (hour > 23 || hour < 0)
			throw new HourException("Hour.hour must be >= 0 and <= 23");

		this.hour = hour;
	}

	public void setMinute(int minute) throws HourException
	{
		if (minute > 59 || minute < 0)
			throw new HourException("Hour.minute must be >= 0 and <= 59");

		this.minute = minute;
	}


	/* METHODS : Comparable */
	//-1 if "this" is smaller
	// 0 if equals
	// 1 if "this" is bigger
	@Override
	public int compareTo(Hour h)
	{
		if (this.hour < h.getHour())
			return -1;
		else if (this.hour == h.getHour())
		{
			if (this.minute < h.getMinute())
				return -1;
			else if (this.minute == h.getMinute())
				return 0;
			else
				return 1;
		}
		return 1;
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return this.hour+"h"+this.minute;
	}
}

/**
 * An hour exception: invalid hour
 * @author Xavier
 */
class HourException extends Exception
{
	public HourException(String str)
	{
		super(str);
	}
}

