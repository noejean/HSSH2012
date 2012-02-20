
package hssh.surveillance;

import hssh.devicesMessages.DeviceMessage;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Xavier
 */
public class State
{
	/** number associated to the state */
	private int id;

	/** true if the state is the initial state of the automaton */
	private boolean isInitial;

	/** transitions of this state to another states */
	private List<Transition> transitions;


	/* CONSTRUCTORS */

	/**
	 * New non-final state that has the specified id
	 * @param id id of the state
	 */
	public State(int id)
	{
		this.id = id;
		this.isInitial = false;
		this.transitions = new ArrayList<Transition>();
	}

	/**
	 * New state
	 * @param id : id of the state
	 * @param isInitial : true if the state is the initial state of the automaton
	 */
	public State(int id, boolean isInitial)
	{
		this(id);
		this.isInitial = isInitial;
	}


	/* ACCESSORS */

	public List<Transition> getTransitions() { return this.transitions; }
	public int getId() { return this.id; }
	public boolean isinitial() { return this.isInitial; }


	/* METHODS */

	/**
	 * Add a transition if it is not already present
	 * @param transition transition to add
	 */
	public void addTransition(Transition... transitions)
	{
		for (Transition t : transitions)
		{
			if (!this.transitions.contains(t))
				this.transitions.add(t);
		}
	}

	/**
	 * Get the next State corresponding to the event given
	 * if no event match, the next state is the current state (loop)
	 * @param event : device message event
	 * @return next state of the automaton
	 */
	public State nextState(DeviceMessage event)
	{
		for (Transition t : this.transitions)
		{
			State next = t.newEvent(event);
			if (next != null)
				return next;
		}
		return this;
	}


	/* METHODS : Object */

	@Override
	public String toString()
	{
		return "State "+this.id;
	}
}
