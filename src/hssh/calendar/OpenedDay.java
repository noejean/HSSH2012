
package hssh.calendar;

/**
 * A day ___ the room is open between the begin hour and the end hour.
 * @author Xavier
 */
public class OpenedDay
	extends Day
{
	/** begin hour of the open */
    private Hour begin;

	/** end hour of the open */
    private Hour end;


    /* CONSTRUCTORS */

	public OpenedDay(DayE day)
	{
		super(day);
	}

	public OpenedDay(DayE day, Hour begin, Hour end)
	{
		this(day);
		this.setBegin(begin);

		try {
			this.setEnd(end);
		}
		catch (DayException ex) {
			System.err.println(ex.getMessage());
		}
	}


	/* ACCESSORS */

	public Hour getBegin() { return this.begin; }
	public Hour getEnd() { return this.end; }

	public void setBegin(Hour begin)
	{
		this.begin = begin;
	}

	public void setEnd(Hour end) throws DayException
	{
		if (this.begin.compareTo(end) >= 0)
			throw new DayException("End hour must be > begin hour");

		this.end = end;
	}


	/* METHODS : Day */

	@Override
	public boolean isOpen(Hour h)
	{
		if (h.compareTo(this.begin) >= 0 && h.compareTo(this.end) <= 0)
			return true;
		
		return false;
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		String str = super.toString();
		return str+" : "+this.begin+" -> "+this.end;
	}
}

