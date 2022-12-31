package flyable;

import coordinates.Coordinates;

enum Status {
	FLYING,
	LANDED
}

public class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	static private long idCounter = 0;
	
	protected Status currentStatus;

	protected Aircraft(String name, Coordinates coordinates) {
		this.id = nextId();
		this.name = name;
		this.coordinates = coordinates;

		if (this.coordinates.getHeight() > 0)
			currentStatus = Status.FLYING;
		else
			currentStatus = Status.LANDED;
	}

	private long nextId() {
		idCounter++;
		return idCounter;
	}
}
