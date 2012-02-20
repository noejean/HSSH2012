

package hssh.ui;

import hssh.ibutton.IbuttonAccount;
import hssh.util.log.*;


/**
 *
 * @author Xavier
 */
public class ObservatorControllerLog extends ObservatorController
{
	/* METHODS: ObservatorController */

	public void serverStarted() {
		Log.add("Server started");
	}

	public void roomUserLogged(IbuttonAccount iba) {
		Log.add("Room user logged : "+iba);
	}

	public void unknowPersonLogged(String ibuttonid) {
		Log.add("Unknow person logged : ibutton id="+ibuttonid);
	}

	public void logout(IbuttonAccount iba) {
		Log.add("Logout : "+iba);
	}

	public void presenceDetected() {
		Log.add("Presence detected");
	}
}
