
package hssh.ui;

import hssh.ibutton.IbuttonAccount;

/**
 *
 * @author Xavier
 */
public interface IObservator
{
	public void serverStarted();
	
	public void roomUserLogged(IbuttonAccount iba);
	public void unknowPersonLogged(String ibuttonid);
	public void logout(IbuttonAccount iba);

	public void presenceDetected();
}
