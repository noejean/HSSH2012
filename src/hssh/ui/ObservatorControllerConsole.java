

package hssh.ui;

import hssh.ibutton.IbuttonAccount;

/**
 *
 * @author Xavier
 */
public class ObservatorControllerConsole extends ObservatorController
{
	/* METHODS: ObservatorController */

	public void serverStarted() {
		System.out.println("Server started");
	}

	public void roomUserLogged(IbuttonAccount iba) {
		System.out.println("Room user logged : "+iba);
	}

	public void unknowPersonLogged(String ibuttonid) {
		System.out.println("Unknow person logged : "+ibuttonid);
	}

	public void logout(IbuttonAccount iba) {
		System.out.println("Logout : "+iba);
	}

	public void presenceDetected() {
		System.out.println("Presence detected");
	}

}
