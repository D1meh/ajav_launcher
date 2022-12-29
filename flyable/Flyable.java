package flyable;

import tower.WeatherTower;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower weathertower);
}
