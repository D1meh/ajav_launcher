package utils;

import coordinates.Coordinates;

public class WeatherTower extends Tower {

	public WeatherTower() {
		super();
	}
	
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather() {
		this.conditionsChanged();
	}

}