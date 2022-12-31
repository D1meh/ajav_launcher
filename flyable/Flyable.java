package flyable;

import utils.WeatherTower;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower weathertower);

	public String getInfo();
}
