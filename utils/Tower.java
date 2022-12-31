package utils;

import flyable.Flyable;

import java.util.ArrayList;

public class Tower {
	private ArrayList<Flyable> observers;

	public Tower() {
		observers = new ArrayList<Flyable>();
	}

	public void register(Flyable flyable) {
		this.observers.add(flyable);
		Simulation.addToOutputString("Tower says: " + flyable.getInfo() + " registered to weather tower.\n");
	}

	public void unregister(Flyable flyable) {
		this.observers.remove(flyable);
		Simulation.addToOutputString("Tower says: " + flyable.getInfo() + " unregistered from weather tower.\n");
	}

	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++)
			observers.get(i).updateConditions();
	}
}
