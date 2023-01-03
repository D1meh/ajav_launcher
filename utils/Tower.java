package utils;

import flyable.Flyable;

import java.util.ArrayList;

public class Tower {
	private ArrayList<Flyable> observers;
	private ArrayList<Flyable> toRemove;

	public Tower() {
		observers = new ArrayList<Flyable>();
		toRemove = new ArrayList<Flyable>();
	}

	public void register(Flyable flyable) {
		Simulation.addToOutputString("Tower says: " + flyable.getInfo() + " registered to weather tower.\n");

		if (!flyable.isFlying()) {
			Simulation.addToOutputString(flyable.getInfo() + " is already landed.\n");
			Simulation.addToOutputString("Tower says: " + flyable.getInfo() + " unregistered from weather tower.\n");
		} else {
			this.observers.add(flyable);
		}
	}

	public void unregister(Flyable flyable) {
		this.toRemove.add(flyable);
		Simulation.addToOutputString("Tower says: " + flyable.getInfo() + " unregistered from weather tower.\n");
	}

	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}

	void removeFlyable() {
		if (toRemove.size() == 0)
			return ;

		for (int i = 0; i < toRemove.size(); i++)
			this.observers.remove(toRemove.get(i));
		this.toRemove.clear();
	}
}
