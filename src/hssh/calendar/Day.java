
package hssh.calendar;


/**
 * Day of a week
 * @author Xavier
 */
public abstract class Day
{
	/** Values that a day can take */
	public enum DayE { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

	/** Value of the day */
	private DayE day;


	/* CONSTRUCTOR */

	public Day(DayE day)
	{
		this.day = day;
	}


	/* ACCESSORS */

	public DayE getDay() { return this.day; }
	public void setDay(DayE day) { this.day = day; }


	/* METHODS */

	/**
	 * Return true if the room is open at the specified hour of the day
	 * @param h : hour of the day
	 * @return room open
	 */
	public abstract boolean isOpen(Hour h);


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return this.getDay().name();
	}
}

class DayException extends Exception
{
	public DayException(String string)
	{
		super(string);
	}
}
