
package hssh.ui;

import hssh.ibutton.IbuttonAccount;
import hssh.ibutton.IbuttonAccountType;
import hssh.util.audioplayer.SoundPlayer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xavier
 */
public class ObservatorControllerVoice extends ObservatorController
{
	private static final String SOUND_FOLDER = "/home/xavier/NetBeansProjects/hssh/resources/audio/";

	
	/* METHODS: ObservatorController */

	public void serverStarted() {
		new SoundPlayer(SOUND_FOLDER+"ServerStarted.wav").start();
	}

	public void roomUserLogged(IbuttonAccount iba) {
		if (iba.getType() == IbuttonAccountType.ADMINISTRATOR)
			new SoundPlayer(SOUND_FOLDER+"HelloAdministrator.wav").start();
		else
			new SoundPlayer(SOUND_FOLDER+"HelloGuest.wav").start();
	}

	public void unknowPersonLogged(String ibuttonid) {
		new SoundPlayer(SOUND_FOLDER+"UnknowUser.wav").start();
	}

	public void logout(IbuttonAccount iba) {
		new SoundPlayer(SOUND_FOLDER+"Goodbye.wav").start();
	}

	public void presenceDetected() {
		new SoundPlayer(SOUND_FOLDER+"IntrusionDetected.wav").start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {}
		new SoundPlayer(SOUND_FOLDER+"PleaseLogin.wav").start();
	}
}
