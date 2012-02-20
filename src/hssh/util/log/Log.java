
package hssh.util.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Xavier
 */
public class Log
{
	private static final String LOG_FOLDER = "/home/xavier/Desktop/";


	/* METHODS */

	public static void add(String msg)
	{
		msg = Log.getDateTime("yyyy-MM-dd HH:mm:ss")+" : "+msg+"\n";
		Log.writeToFile(msg);
	}

	private static String getDateTime(String format)
	{
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	private static void writeToFile(String msg)
	{
		try {
			FileWriter fstream = new FileWriter(LOG_FOLDER+Log.getDateTime("yyyy-MM-dd")+".log", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(msg);
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}

