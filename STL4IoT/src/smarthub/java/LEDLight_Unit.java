/** Generated by itemis CREATE code generator. */
package smarthub.java;

import com.yakindu.core.IEventDriven;
import com.yakindu.core.IStatemachine;
import com.yakindu.core.ITimed;
import com.yakindu.core.ITimerService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LEDLight_Unit implements ITimed, IEventDriven {
	public static class Device {
		private LEDLight_Unit parent;
		
		public Device(LEDLight_Unit parent) {
			this.parent = parent;
		}
		private boolean on;
		
		
		public void raiseOn() {
			synchronized(parent) {
				parent.inEventQueue.add(() -> {
					on = true;
				});
				parent.runCycle();
			}
		}
		
		private boolean off;
		
		
		public void raiseOff() {
			synchronized(parent) {
				parent.inEventQueue.add(() -> {
					off = true;
				});
				parent.runCycle();
			}
		}
		
		private boolean isOn;
		
		public synchronized boolean getIsOn() {
			synchronized(parent) {
				return isOn;
			}
		}
		
		public void setIsOn(boolean value) {
			synchronized(parent) {
				this.isOn = value;
			}
		}
		
	}
	
	public static class Bulb {
		private LEDLight_Unit parent;
		
		public Bulb(LEDLight_Unit parent) {
			this.parent = parent;
		}
		private boolean isOn;
		
		public synchronized boolean getIsOn() {
			synchronized(parent) {
				return isOn;
			}
		}
		
		public void setIsOn(boolean value) {
			synchronized(parent) {
				this.isOn = value;
			}
		}
		
	}
	
	public static class Brightness {
		private LEDLight_Unit parent;
		
		public Brightness(LEDLight_Unit parent) {
			this.parent = parent;
		}
		private boolean up;
		
		
		public void raiseUp() {
			synchronized(parent) {
				parent.inEventQueue.add(() -> {
					up = true;
				});
				parent.runCycle();
			}
		}
		
		private boolean down;
		
		
		public void raiseDown() {
			synchronized(parent) {
				parent.inEventQueue.add(() -> {
					down = true;
				});
				parent.runCycle();
			}
		}
		
		private long level;
		
		public synchronized long getLevel() {
			synchronized(parent) {
				return level;
			}
		}
		
		public void setLevel(long value) {
			synchronized(parent) {
				this.level = value;
			}
		}
		
	}
	
	protected Device device;
	
	protected Bulb bulb;
	
	protected Brightness brightness;
	
	public enum State {
		_LED_LIGHT_UNIT___OFF_,
		_LED_LIGHT_UNIT___ON_,
		_LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY,
		_LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON,
		_LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL,
		$NULLSTATE$
	};
	
	private final State[] stateVector = new State[1];
	
	private ITimerService timerService;
	
	private final boolean[] timeEvents = new boolean[2];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isExecuting;
	
	protected boolean getIsExecuting() {
		synchronized(LEDLight_Unit.this) {
			return isExecuting;
		}
	}
	
	protected void setIsExecuting(boolean value) {
		synchronized(LEDLight_Unit.this) {
			this.isExecuting = value;
		}
	}
	public LEDLight_Unit() {
		device = new Device(this);
		bulb = new Bulb(this);
		brightness = new Brightness(this);
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NULLSTATE$;
		}
		
		clearInEvents();
		
		/* Default init sequence for statechart LEDLight_Unit */
		device.setIsOn(false);
		bulb.setIsOn(false);
		brightness.setLevel(1l);
		
		isExecuting = false;
	}
	
	public synchronized void enter() {
		/* Activates the state machine. */
		if (timerService == null) {
			throw new IllegalStateException("Timer service must be set.");
		}
		
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		/* Default enter sequence for statechart LEDLight_Unit */
		enterSequence__LED_Light_Unit__default();
		isExecuting = false;
	}
	
	public synchronized void exit() {
		/* Deactivates the state machine. */
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		/* Default exit sequence for statechart LEDLight_Unit */
		exitSequence__LED_Light_Unit_();
		isExecuting = false;
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public synchronized boolean isActive() {
		return stateVector[0] != State.$NULLSTATE$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return false;
	}
	private void clearInEvents() {
		device.on = false;
		device.off = false;
		brightness.up = false;
		brightness.down = false;
		timeEvents[0] = false;
		timeEvents[1] = false;
	}
	
	private void microStep() {
		switch (stateVector[0]) {
		case _LED_LIGHT_UNIT___OFF_:
			_LED_Light_Unit___Off__react(-1l);
			break;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY:
			_LED_Light_Unit___On___Bulb_Status__Bulb_on_standby_react(-1l);
			break;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL:
			_LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_react(-1l);
			break;
		default:
			break;
		}
	}
	
	private void runCycle() {
		/* Performs a 'run to completion' step. */
		if (timerService == null) {
			throw new IllegalStateException("Timer service must be set.");
		}
		
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		nextEvent();
		do { 
			microStep();
			clearInEvents();
		} while (nextEvent());
		isExecuting = false;
	}
	
	protected boolean nextEvent() {
		if(!inEventQueue.isEmpty()) {
			inEventQueue.poll().run();
			return true;
		}
		return false;
	}
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case _LED_LIGHT_UNIT___OFF_:
			return stateVector[0] == State._LED_LIGHT_UNIT___OFF_;
		case _LED_LIGHT_UNIT___ON_:
			return stateVector[0].ordinal() >= State.
					_LED_LIGHT_UNIT___ON_.ordinal()&& stateVector[0].ordinal() <= State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL.ordinal();
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY:
			return stateVector[0] == State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON:
			return stateVector[0].ordinal() >= State.
					_LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON.ordinal()&& stateVector[0].ordinal() <= State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL.ordinal();
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL:
			return stateVector[0] == State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL;
		default:
			return false;
		}
	}
	
	public synchronized void setTimerService(ITimerService timerService) {
		this.timerService = timerService;
	}
	
	public ITimerService getTimerService() {
		return timerService;
	}
	
	public synchronized void raiseTimeEvent(int eventID) {
		inEventQueue.add(() -> {
			timeEvents[eventID] = true;
		});
		runCycle();
	}
	
	public Device device() {
		return device;
	}
	
	public Bulb bulb() {
		return bulb;
	}
	
	public Brightness brightness() {
		return brightness;
	}
	
	
	/* Entry action for state '<Off>'. */
	private void entryAction__LED_Light_Unit___Off_() {
		/* Entry action for state '<Off>'. */
		device.setIsOn(false);
	}
	
	/* Entry action for state 'Bulb_on_standby'. */
	private void entryAction__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby() {
		/* Entry action for state 'Bulb_on_standby'. */
		timerService.setTimer(this, 0, 500l, true);
	}
	
	/* Entry action for state 'Bulb_on'. */
	private void entryAction__LED_Light_Unit___On___Bulb_Status__Bulb_on() {
		/* Entry action for state 'Bulb_on'. */
		timerService.setTimer(this, 1, 500l, true);
	}
	
	/* Exit action for state '<Off>'. */
	private void exitAction__LED_Light_Unit___Off_() {
		/* Exit action for state '<Off>'. */
		device.setIsOn(true);
	}
	
	/* Exit action for state 'Bulb_on_standby'. */
	private void exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby() {
		/* Exit action for state 'Bulb_on_standby'. */
		timerService.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'Bulb_on'. */
	private void exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on() {
		/* Exit action for state 'Bulb_on'. */
		timerService.unsetTimer(this, 1);
	}
	
	/* 'default' enter sequence for state <Off> */
	private void enterSequence__LED_Light_Unit___Off__default() {
		/* 'default' enter sequence for state <Off> */
		entryAction__LED_Light_Unit___Off_();
		stateVector[0] = State._LED_LIGHT_UNIT___OFF_;
	}
	
	/* 'default' enter sequence for state <On> */
	private void enterSequence__LED_Light_Unit___On__default() {
		/* 'default' enter sequence for state <On> */
		enterSequence__LED_Light_Unit___On___Bulb_Status__default();
	}
	
	/* 'default' enter sequence for state Bulb_on_standby */
	private void enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby_default() {
		/* 'default' enter sequence for state Bulb_on_standby */
		entryAction__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby();
		stateVector[0] = State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY;
	}
	
	/* 'default' enter sequence for state Bulb_on */
	private void enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_default() {
		/* 'default' enter sequence for state Bulb_on */
		entryAction__LED_Light_Unit___On___Bulb_Status__Bulb_on();
		enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__default();
	}
	
	/* 'default' enter sequence for state Change_Luminosity_Level */
	private void enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_default() {
		/* 'default' enter sequence for state Change_Luminosity_Level */
		stateVector[0] = State._LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL;
	}
	
	/* 'default' enter sequence for region <LED_Light_Unit> */
	private void enterSequence__LED_Light_Unit__default() {
		/* 'default' enter sequence for region <LED_Light_Unit> */
		react__LED_Light_Unit___entry_Default();
	}
	
	/* 'default' enter sequence for region <Bulb_Status> */
	private void enterSequence__LED_Light_Unit___On___Bulb_Status__default() {
		/* 'default' enter sequence for region <Bulb_Status> */
		react__LED_Light_Unit___On___Bulb_Status___entry_Default();
	}
	
	/* 'default' enter sequence for region <Luminosity> */
	private void enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__default() {
		/* 'default' enter sequence for region <Luminosity> */
		react__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity___entry_Default();
	}
	
	/* Default exit sequence for state <Off> */
	private void exitSequence__LED_Light_Unit___Off_() {
		/* Default exit sequence for state <Off> */
		stateVector[0] = State.$NULLSTATE$;
		exitAction__LED_Light_Unit___Off_();
	}
	
	/* Default exit sequence for state <On> */
	private void exitSequence__LED_Light_Unit___On_() {
		/* Default exit sequence for state <On> */
		exitSequence__LED_Light_Unit___On___Bulb_Status_();
	}
	
	/* Default exit sequence for state Bulb_on_standby */
	private void exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby() {
		/* Default exit sequence for state Bulb_on_standby */
		stateVector[0] = State.$NULLSTATE$;
		exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby();
	}
	
	/* Default exit sequence for state Bulb_on */
	private void exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on() {
		/* Default exit sequence for state Bulb_on */
		exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity_();
		exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on();
	}
	
	/* Default exit sequence for state Change_Luminosity_Level */
	private void exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level() {
		/* Default exit sequence for state Change_Luminosity_Level */
		stateVector[0] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for region <LED_Light_Unit> */
	private void exitSequence__LED_Light_Unit_() {
		/* Default exit sequence for region <LED_Light_Unit> */
		switch (stateVector[0]) {
		case _LED_LIGHT_UNIT___OFF_:
			exitSequence__LED_Light_Unit___Off_();
			break;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY:
			exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby();
			break;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL:
			exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level();
			exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region <Bulb_Status> */
	private void exitSequence__LED_Light_Unit___On___Bulb_Status_() {
		/* Default exit sequence for region <Bulb_Status> */
		switch (stateVector[0]) {
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON_STANDBY:
			exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby();
			break;
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL:
			exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level();
			exitAction__LED_Light_Unit___On___Bulb_Status__Bulb_on();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region <Luminosity> */
	private void exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity_() {
		/* Default exit sequence for region <Luminosity> */
		switch (stateVector[0]) {
		case _LED_LIGHT_UNIT___ON___BULB_STATUS__BULB_ON__LUMINOSITY__CHANGE_LUMINOSITY_LEVEL:
			exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react__LED_Light_Unit___entry_Default() {
		/* Default react sequence for initial entry  */
		enterSequence__LED_Light_Unit___Off__default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__LED_Light_Unit___On___Bulb_Status___entry_Default() {
		/* Default react sequence for initial entry  */
		enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity___entry_Default() {
		/* Default react sequence for initial entry  */
		enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_default();
	}
	
	private long react(long transitioned_before) {
		/* State machine reactions. */
		return transitioned_before;
	}
	
	private long _LED_Light_Unit___Off__react(long transitioned_before) {
		/* The reactions of state <Off>. */
		long transitioned_after = transitioned_before;
		if (transitioned_after<0l) {
			if (device.on) {
				exitSequence__LED_Light_Unit___Off_();
				enterSequence__LED_Light_Unit___On__default();
				react(0l);
				transitioned_after = 0l;
			}
		}
		/* If no transition was taken */
		if (transitioned_after==transitioned_before) {
			/* then execute local reactions. */
			transitioned_after = react(transitioned_before);
		}
		return transitioned_after;
	}
	
	private long _LED_Light_Unit___On__react(long transitioned_before) {
		/* The reactions of state <On>. */
		long transitioned_after = transitioned_before;
		if (transitioned_after<0l) {
			if (device.off) {
				exitSequence__LED_Light_Unit___On_();
				enterSequence__LED_Light_Unit___Off__default();
				react(0l);
				transitioned_after = 0l;
			}
		}
		/* If no transition was taken */
		if (transitioned_after==transitioned_before) {
			/* then execute local reactions. */
			transitioned_after = react(transitioned_before);
		}
		return transitioned_after;
	}
	
	private long _LED_Light_Unit___On___Bulb_Status__Bulb_on_standby_react(long transitioned_before) {
		/* The reactions of state Bulb_on_standby. */
		long transitioned_after = transitioned_before;
		if (transitioned_after<0l) {
			if (((timeEvents[0]) && (bulb.getIsOn()))) {
				exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby();
				timeEvents[0] = false;
				enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_default();
				_LED_Light_Unit___On__react(0l);
				transitioned_after = 0l;
			}
		}
		/* If no transition was taken */
		if (transitioned_after==transitioned_before) {
			/* then execute local reactions. */
			transitioned_after = _LED_Light_Unit___On__react(transitioned_before);
		}
		return transitioned_after;
	}
	
	private long _LED_Light_Unit___On___Bulb_Status__Bulb_on_react(long transitioned_before) {
		/* The reactions of state Bulb_on. */
		long transitioned_after = transitioned_before;
		if (transitioned_after<0l) {
			if (((timeEvents[1]) && (!(bulb.getIsOn())))) {
				exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on();
				timeEvents[1] = false;
				enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on_standby_default();
				_LED_Light_Unit___On__react(0l);
				transitioned_after = 0l;
			}
		}
		/* If no transition was taken */
		if (transitioned_after==transitioned_before) {
			/* then execute local reactions. */
			transitioned_after = _LED_Light_Unit___On__react(transitioned_before);
		}
		return transitioned_after;
	}
	
	private long _LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_react(long transitioned_before) {
		/* The reactions of state Change_Luminosity_Level. */
		long transitioned_after = transitioned_before;
		if (transitioned_after<0l) {
			if (brightness.down) {
				exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level();
				brightness.level--;
				enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_default();
				_LED_Light_Unit___On___Bulb_Status__Bulb_on_react(0l);
				transitioned_after = 0l;
			} else {
				if (brightness.up) {
					exitSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level();
					brightness.level++;
					enterSequence__LED_Light_Unit___On___Bulb_Status__Bulb_on__Luminosity__Change_Luminosity_Level_default();
					_LED_Light_Unit___On___Bulb_Status__Bulb_on_react(0l);
					transitioned_after = 0l;
				}
			}
		}
		/* If no transition was taken */
		if (transitioned_after==transitioned_before) {
			/* then execute local reactions. */
			transitioned_after = _LED_Light_Unit___On___Bulb_Status__Bulb_on_react(transitioned_before);
		}
		return transitioned_after;
	}
	
	/* Can be used by the client code to trigger a run to completion step without raising an event. */
	public synchronized void triggerWithoutEvent() {
		runCycle();
	}
}
