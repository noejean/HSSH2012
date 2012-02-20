
package hssh.calendar;

import hssh.calendar.Day.DayE;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * A week calendar is a list of 7 Day (OpenedDay or ClosedDay).
 * It allow you to know, for each day of the week, if the room must to be
 * open or close.
 * @author Xavier
 */
public class WeekCalendar
{
	/** Days of the week */
	private Day days[];


	/* CONSTRUCTORS */

	public WeekCalendar()
	{
		this.days = new Day[7];
	}


	/* METHODS */

	/**
	 * Add a day to the week calendar
	 * @param day : day to add (or replace if it is already defined)
	 */
	public void addDay(Day day)
	{
		if (this.days[day.getDay().ordinal()] != null)
			System.err.println("Warning: replacement of an existing day");

		this.days[day.getDay().ordinal()] = day;
	}

	/**
	 * Load a week calendar from a xml file
	 * @param xmlFile : path of the xml file to load
	 */
	public void load(String xmlFile)
	{
		SAXBuilder sxb = new SAXBuilder();
		org.jdom.Document document = null;
		try
		{
			document = sxb.build(new File(xmlFile));
		
			/* parcours des jours */
			List xmlDays = document.getRootElement().getChildren("day");
			Iterator i = xmlDays.iterator();
			while (i.hasNext())
			{
				Element xmlDay = (Element)i.next();
				Day day = null;

				/* jour salle ouverte */
				if (xmlDay.getAttributeValue("type").toLowerCase().equals("opened"))
				{
					day = new OpenedDay(DayE.valueOf(xmlDay.getAttributeValue("name").toUpperCase()));

					/* heures ouverture */
					List xmlHours = xmlDay.getChildren("hours");
					Iterator j = xmlHours.iterator();
					while (j.hasNext())
					{
						Element xmlHour = (Element)j.next();

						Hour hour = new Hour(
							Integer.parseInt(xmlHour.getAttributeValue("hour")),
							Integer.parseInt(xmlHour.getAttributeValue("min"))
							);

						if (xmlHour.getAttributeValue("type").toLowerCase().equals("begin"))
							((OpenedDay) day).setBegin(hour);
						else
							((OpenedDay) day).setEnd(hour);
					}
				}

				/* jour salle fermée */
				else
				{
					day = new ClosedDay(DayE.valueOf(xmlDay.getAttributeValue("name").toUpperCase()));
				}

				this.addDay(day);
			}
		}
		catch(Exception e) {
			System.err.println("Loading XML file failed : "+e.getMessage());
		}
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		String str = "Calendar:\n";
		for (Day d : this.days)
			str += d+"\n";
		return str;
	}
}
