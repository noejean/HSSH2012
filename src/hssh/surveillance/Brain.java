
package hssh.surveillance;

import hssh.actions.*;
import hssh.devicesMessages.DeviceMessage;
import hssh.ui.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Xavier
 */
public class Brain
{
	final private Observator observator = new Observator(new ObservatorControllerConsole(),new ObservatorControllerVoice(), new ObservatorControllerLog());


	
	/** States of the brain automaton */
	private List<State> states;

	/** Current state */
	private State current;

	/** Actions being launched */
	private List<Action> actions;


	/* CONSTRUCTORS */

	public Brain()
	{
		this.current = null;
		this.states = new ArrayList<State>();
		this.actions = new ArrayList<Action>();
	}

	public Brain(State current)
	{
		this();
		this.current = current;
	}


	/* ACCESSORS */

	/* METHODS */

	/**
	 * Add a state to the automaton
	 * @param state state to add
	 */
	public void addState(State... states)
	{
		for (State s : states)
		{
			if (!this.states.contains(s))
				this.states.add(s);
		}
	}

	public void newEvent(DeviceMessage event)
	{
		State next = this.current.nextState(event);
		System.out.println("Event received: "+event+", next state: "+next);

		this.current = next;
		this.doActions();
	}

	private void doActions()
	{
		this.stopActions();
		
		switch (this.current.getId())
		{
			case 0:
				System.out.println("Tout est OK");
				break;

			case 1:
				this.launchAction(new Alarm(10));
				System.out.println("En attente de présence");
				break;

			case 4:
				this.observator.presenceDetected();
				break;

			default:
				System.out.println("no action specified for this state: "+this.current.getId());
		}
	}

	private void launchAction(Action a)
	{
		this.actions.add(a);
		a.start();
	}

	private void stopActions()
	{
		for (Action a: this.actions)
			a.stop();
		this.actions.clear();
	}
}
