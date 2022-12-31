package flyable;

import coordinates.Coordinates;

public class AircraftFactory {

	static public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		Flyable ret;
		Coordinates coord = new Coordinates(longitude, latitude, height);
		
		switch (type) {
			case "Baloon":
				ret = new Baloon(name, coord);
				break ;
			case "JetPlane":
				ret = new JetPlane(name, coord);
				break ;
			case "Helicopter":
				ret = new Helicopter(name, coord);
				break ;
			default:
				ret = null;
				break ;
		}

		return ret;
	}
}
