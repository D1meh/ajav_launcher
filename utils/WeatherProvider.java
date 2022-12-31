package utils;

import coordinates.Coordinates;

public class WeatherProvider {
	
	static private WeatherProvider weatherProvider;
	static private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	private WeatherProvider() {}

	static public WeatherProvider getProvider() {
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int coord = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
		return weather[coord % 4];
	}
}
