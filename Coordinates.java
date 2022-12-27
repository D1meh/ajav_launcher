package coordinates;

public class Coordinates {


	// Attributes
	private int longitude;
	private int latitude;
	private int height;

	// Constructor
	public Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;

		if (this.height < 0)
			this.height = 0;
		else if (this.height > 100)
			this.height = 100;
	}


	// Methods
	public int getLongitude() {
		return this.longitude;
	}

	public int getLatitude() {
		return this.latitude;
	}

	public int getHeight() {
		return this.height;
	}

}
