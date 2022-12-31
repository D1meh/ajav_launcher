package utils;

import flyable.*;
import java.io.File;
import java.io.FileWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Simulation {
	private int nbOfSim;
	private File simFile;
	private Scanner scanner;
	private WeatherTower wTower;
	static private String output;

	static public void addToOutputString(String stringToAdd) {
		if (output == null)
			output = new String();
		output += stringToAdd;
	}

	private void openFile(String fileName) {
		this.simFile = new File(fileName);

		try {
		if (!this.simFile.exists())
			throw new IncorrectFileException("File Exception : File is invalid or does not exist");
		if (!this.simFile.isFile())
			throw new IncorrectFileException("File Exception : File is not an expect type of file (may be a folder)");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private void getNbOfSim() {
		try {
			this.scanner = new Scanner(this.simFile);
			String firstLine = this.scanner.nextLine();
			if (firstLine == null)
				throw new IncorrectValueForNumberOfSimulation("Value Exception : No line found");
			this.nbOfSim = Integer.parseInt(firstLine);
			if (this.nbOfSim < 1)
				throw new IncorrectValueForNumberOfSimulation("Value Exception : Incorrect value provided");
		
		} catch (NumberFormatException e) {
			try {
				throw new IncorrectValueForNumberOfSimulation("Value Exception : First line should be a positive number");
			} catch (Exception f) {
				System.out.println(f.getMessage());
				System.exit(1);
			}
		
		} catch (NoSuchElementException e) {
			try {
				throw new IncorrectValueForNumberOfSimulation("Value Exception : No line found");
			} catch (Exception f) {
				System.out.println(f.getMessage());
				System.exit(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
			System.exit(1);
		}
	}

	private void initData() {
		this.wTower = new WeatherTower();

		try {
			int lineCounter = 0;

			while (this.scanner.hasNextLine()) {
				String line = this.scanner.nextLine();
				String[] words = line.split(" ");

				if (words.length != 5)
					throw new IncorrectDataForAircraftDescription("Data exception : Too many/few data provided for aircraft description");

				Flyable newAircraft = AircraftFactory.newAircraft(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[4]));
				if (newAircraft == null)
					throw new IncorrectDataForAircraftDescription("Data Exception : Incorrect data for Aircraft Description");
				newAircraft.registerTower(this.wTower);
				lineCounter++;
			}

			if (lineCounter == 0)
				throw new IncorrectDataForAircraftDescription("Data Exception : No line found for aircraft description");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private void simLoop() {

		for (int i = 0; i < this.nbOfSim; i++) {
			this.wTower.changeWeather();
		}
	}

	public static void main(String[] args) {

		try {
			if (args.length != 1)
				throw new InvalidNumberOfArgumentException("Argument Exception : Invalid number of argument");
		} catch (InvalidNumberOfArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Simulation sim = new Simulation();
		sim.openFile(args[0]);
		sim.getNbOfSim();
		sim.initData();
		sim.simLoop();

		try {
			File outFile = new File("simulation.txt");
			FileWriter outFileWriter = new FileWriter("simulation.txt");
			outFileWriter.write(sim.output);
			outFileWriter.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
