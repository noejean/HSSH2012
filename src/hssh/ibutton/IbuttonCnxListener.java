
package hssh.ibutton;

import hssh.util.directorywatcher.FileListener;
import java.io.File;

/**
 *
 * @author Xavier
 */
public class IbuttonCnxListener extends FileListener
{
	private IbuttonServer iserver;


	/* CONSTRUCTORS */

	public IbuttonCnxListener(IbuttonServer iserver)
	{
		this.iserver = iserver;
	}


	/* METHODS : FileListener */

	@Override
	public void onStart(Object monitoredResource)
	{
    }

	@Override
    public void onStop(Object notMonitoredResource)
	{
    }

	@Override
	public void onChange(Object changedResource)
	{
    }

	@Override
	public void onAdd(Object newResource)
	{
        if (newResource instanceof File)
		{
            String ibuttonId = ((File)newResource).getName();
			try {
				this.iserver.login(ibuttonId);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
        }
    }

	@Override
	public void onDelete(Object deletedResource)
	{
		this.iserver.logout();
	}
}
