
package hssh.ibutton;

/**
 *
 * @author Xavier
 */
public class IbuttonAccount
{
	private IbuttonAccountType type;
	private String ibuttonId;
	private String name;


	/* CONSTRUCTORS */

	public IbuttonAccount()
	{
		this.type = IbuttonAccountType.GUEST;
		this.ibuttonId= "";
		this.name = "";
	}

	public IbuttonAccount(String ibuttonId)
	{
		this.ibuttonId = ibuttonId;
	}

	public IbuttonAccount(IbuttonAccountType type, String ibuttonId, String name)
	{
		this.type = type;
		this.ibuttonId = ibuttonId;
		this.name = name;
	}


	/* ACCESSORS */

	public IbuttonAccountType getType() { return this.type; }
	public String getIbuttonId() { return this.ibuttonId; }
	public String getName() { return this.name; }


	/* METHODS */


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return this.type+" - "+this.ibuttonId+" : "+this.name;
	}

	@Override
	public boolean equals(Object obj)
	{
		// Vérification de l'égalité des références
		if (obj == this)
			return true;

		// Vérification du type du paramètre
		if (obj instanceof IbuttonAccount)
		{
			// Vérification des valeurs des attributs
			IbuttonAccount other = (IbuttonAccount) obj;

			return this.ibuttonId.equals(other.ibuttonId);
		}

		return false;
	}
}
