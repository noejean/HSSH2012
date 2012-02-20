

package hssh.calendar;

/**
 * A ClosedDay is a day ___ the room is ever closed.
 * @author Xavier
 */
public class ClosedDay
	extends Day
{
	/* CONSTRUCTORS */

	public ClosedDay(DayE day)
	{
		super(day);
	}

	
	/* METHODS : Day */

	@Override
	public boolean isOpen(Hour h)
	{
		return false;
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return super.toString()+" : closed";
	}
}
