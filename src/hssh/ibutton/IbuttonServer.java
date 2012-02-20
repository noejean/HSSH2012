
package hssh.ibutton;

import hssh.ui.*;

import hssh.util.directorywatcher.DirectoryWatcher;


/**
 *
 * @author Xavier
 */
public class IbuttonServer
{
	final private Observator observator = new Observator(new ObservatorControllerConsole(),new ObservatorControllerVoice(), new ObservatorControllerLog());

	private IbuttonAccounts iaccounts;
	private IbuttonAccount logged;
	private DirectoryWatcher dw;


	/* CONSTRUCTORS */

	public IbuttonServer(String xmlAccountsFile, String folder)
	{
		this.logged = null;
		this.dw = new DirectoryWatcher(folder, 1);
		this.iaccounts = new IbuttonAccounts(xmlAccountsFile);
	}


	/* ACCESSORS */

	public IbuttonAccount getLogged() { return this.logged; }


	/* METHODS */

	public void start()
	{
		dw.addListener(new IbuttonCnxListener(this));
		this.dw.start();
		
		observator.serverStarted();
	}

	public void login(String ibuttonid)
	{
		try {
			this.logged = this.iaccounts.get(ibuttonid);
			this.observator.roomUserLogged(this.logged);

		} catch (UnknowIbuttonAccountException ex) {
			this.observator.unknowPersonLogged(ibuttonid);
		}
	}

	public void logout()
	{
		this.observator.logout(this.logged);
		this.logged = null;
	}


	/* METHODS: Object */

	@Override
	public String toString()
	{
		String str = this.iaccounts.toString();
		str += "Logged person: "+this.logged;
		return str;
	}
}

