package flyable;

import coordinates.Coordinates;
import utils.Simulation;
import utils.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

	WeatherTower weathertower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		if (this.currentStatus == Status.LANDED)
			return ;

		String currentWeather = this.weathertower.getWeather(this.coordinates);
		
		switch (currentWeather) {
			case "RAIN":
				this.coordinates.changeCoordinates("LONG", 5);
				Simulation.addToOutputString(this.getInfo() + ": A bit of rain won't mess up my helicopter... Right?\n");
				break ;
			case "FOG":
				this.coordinates.changeCoordinates("LONG", 1);
				Simulation.addToOutputString(this.getInfo() + ": I can't pilot with so much fog!\n");
				break ;
			case "SUN":
				this.coordinates.changeCoordinates("LONG", 10);
				this.coordinates.changeCoordinates("HEI", 2);
				Simulation.addToOutputString(this.getInfo() + ": This is hot.\n");
				break ;
			case "SNOW":
				this.coordinates.changeCoordinates("HEI", -12);
				Simulation.addToOutputString(this.getInfo() + ": My rotor is going to freeze!\n");
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