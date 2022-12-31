package utils;

class InvalidNumberOfArgumentException extends Exception {
	InvalidNumberOfArgumentException(String errorMessage) {
		super(errorMessage);
	}
}

class IncorrectFileException extends Exception {
	IncorrectFileException(String errorMessage) {
		super(errorMessage);
	}
}

class IncorrectValueForNumberOfSimulation extends Exception {
	IncorrectValueForNumberOfSimulation(String errorMessage) {
		super(errorMessage);
	}
}

class IncorrectDataForAircraftDescription extends Exception {
	IncorrectDataForAircraftDescription(String errorMessage) {
		super(errorMessage);
	}
}