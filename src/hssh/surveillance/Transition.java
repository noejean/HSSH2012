
package hssh.surveillance;

import hssh.devicesMessages.DeviceMessage;


/**
 *
 * @author Xavier
 */
public class Transition
{
	/** DeviceMessage that can change the current state */
	private DeviceMessage event;

	/** Next state of the automaton if the event occur */
	private State nextState;


	/* CONSTRUCTORS */

	/**
	 * Creation of a new transition
	 * @param event
	 * @param nextState
	 */
	public Transition(DeviceMessage event, State nextState)
	{
		this.event = event;
		this.nextState = nextState;
	}

	
	/* ACCESSORS */

	/* METHODS */

	/**
	 * Return the next state of the automaton if the event is ok
	 * @param event
	 * @return State : next state or null
	 */
	public State newEvent(DeviceMessage event)
	{
		if (event.equals(this.event))
			return this.nextState;

		return null;
	}
}
