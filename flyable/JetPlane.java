package flyable;

import coordinates.Coordinates;
import utils.Simulation;
import utils.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

	WeatherTower weathertower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		if (this.currentStatus == Status.LANDED)
			return ;

		String currentWeather = this.weathertower.getWeather(this.coordinates);
		
		switch (currentWeather) {
			case "RAIN":
				this.coordinates.changeCoordinates("LAT", 5);
				Simulation.addToOutputString(this.getInfo() + ": It's raining. Better watch out for lightnings.\n");
				break ;
			case "FOG":
				this.coordinates.changeCoordinates("LAT", 1);
				Simulation.addToOutputString(this.getInfo() + ": Let's hope the fog will lift soon...\n");
				break ;
			case "SUN":
				this.coordinates.changeCoordinates("LAT", 10);
				this.coordinates.changeCoordinates("HEI", 2);
				Simulation.addToOutputString(this.getInfo() + ": The sun is blinding me.\n");
				break ;
			case "SNOW":
				this.coordinates.changeCoordinates("HEI", -7);
				Simulation.addToOutputString(this.getInfo() + ": OMG! Winter is coming!\n");
				break ;
			default:
				break ;
		}

		if (this.coordinates.getHeight() < 1) {
			this.currentStatus = Status.LANDED;
			Simulation.addToOutputString(this.getInfo() + " landing.\n");
			this.weathertower.unregister(this);
		}
	}

	public void registerTower(WeatherTower weathertower) {
		this.weathertower = weathertower;
		weathertower.register(this);
	}

	public String getInfo() {
		String className = this.getClass().getSimpleName();
		String aircraftName = this.name;
		long id = this.id;
		return className + "#" + aircraftName + "(" + id + ")";
	}
}