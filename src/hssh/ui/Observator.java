
package hssh.ui;

import hssh.ibutton.IbuttonAccount;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xavier
 */
public class Observator implements IObservator
{
	private List<ObservatorController> ocs;


	/* CONSTRUCTORS */

	public Observator(ObservatorController... ocs)
	{
		this.ocs = new ArrayList<ObservatorController>();

		for (ObservatorController oc: ocs)
		{
			if (! this.ocs.contains(oc))
				this.ocs.add(oc);
		}
	}


	/* METHODS: IObservator */

	public void serverStarted() {
		for (ObservatorController oc: this.ocs)
			oc.serverStarted();
	}

	public void roomUserLogged(IbuttonAccount iba) {
		for (ObservatorController oc: this.ocs)
			oc.roomUserLogged(iba);
	}

	public void unknowPersonLogged(String ibuttonid) {
		for (ObservatorController oc: this.ocs)
			oc.unknowPersonLogged(ibuttonid);
	}

	public void logout(IbuttonAccount iba) {
		for (ObservatorController oc: this.ocs)
			oc.logout(iba);
	}
	
	public void presenceDetected() {
		for (ObservatorController oc: this.ocs)
			oc.presenceDetected();
	}

}
