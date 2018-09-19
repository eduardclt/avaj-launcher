package simulator;

import simulator.Vehicles.AircraftFactory;
import simulator.Vehicles.Flyable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static WeatherTower weatherTower;
    private static List<Flyable> flyableList = new ArrayList<>();
    public static PrintWriter writer;

    public static void main(String[] args){

        if (args.length < 1) {
            System.out.println("Please specify a scenario file as an argument.");
            return;
        }
        File simulationFile = new File("simulation.txt");
        try {
            writer = new PrintWriter(simulationFile);
        } catch (FileNotFoundException fne) {
            System.out.println("Error: " + fne.getMessage());
            return;
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line != null)
            {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);

                if (simulations < 0) {
                    System.out.println("Invalid simulation count " + simulations);
                    System.exit(1);
                }

                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    flyableList.add(flyable);
                }
                if (flyableList.size() == 0)
                {
                    System.out.println("There are no valid aircrafts to run simulation");
                    return;
                }
                for (Flyable flyable : flyableList) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 0; i < simulations; i++){
                    weatherTower.changeWeather();
                }

            }
            else {
                System.out.println("Nothing to be read");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found " + args[0]);
        } catch (IOException e) {
            System.out.println("There was an error while reading the file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There was an error with the file format");
        } catch(NumberFormatException e){
            System.out.println("Invalid number format.");
        } catch (NullPointerException e){
            System.out.println("There was a problem in the simulation file.");
        }
        writer.close();
    }
}
