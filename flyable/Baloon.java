package flyable;

import coordinates.Coordinates;
import utils.Simulation;
import utils.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weathertower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		if (this.currentStatus == Status.LANDED)
			return ;

		String currentWeather = this.weathertower.getWeather(this.coordinates);

		switch (currentWeather) {
			case "RAIN":
				this.coordinates.changeCoordinates("HEI", -5);
				Simulation.addToOutputString(this.getInfo() + ": Damn your rain! You messed up my baloon.\n");
				break ;
			case "FOG":
				this.coordinates.changeCoordinates("HEI", -3);
				Simulation.addToOutputString(this.getInfo() + ": Jeez, I can't see anything!\n");
				break ;
			case "SUN":
				this.coordinates.changeCoordinates("LONG", 2);
				this.coordinates.changeCoordinates("HEI", 4);
				Simulation.addToOutputString(this.getInfo() + ": Let's enjoy the good weather and take some pics.\n");
				break ;
			case "SNOW":
				this.coordinates.changeCoordinates("HEI", -15);
				Simulation.addToOutputString(this.getInfo() + ": It's snowing. We're gonna crash.\n");
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

	public boolean isFlying() {
		return this.currentStatus == Status.FLYING;
	}

}
