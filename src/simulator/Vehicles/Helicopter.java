package simulator.Vehicles;

import simulator.Simulator;
import simulator.WeatherTower;
import weather.Coordinates;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    HashMap<String, String> messages = new HashMap<String, String>(){{
        put("snow", "My rotor is going to freeze.");
        put("rain", "Noooo I'm wetttt!.");
        put("fog", "I can't see a thing MAYDAY.");
        put("sun", "This is hot.");
    }};

    Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }


    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates).toLowerCase();

        if (weather.equals("sun"))
        {
            if (this.coordinates.getHeight() >= 100){
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude() + 0,
                        100
                );
            }else{
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude() + 0,
                        coordinates.getHeight() + 2
                );
            }
        }
        else if (weather.equals("rain"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 5,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather.equals("fog"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 1,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather.equals("snow"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 12
            );
        }
        Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (coordinates.getHeight() <= 0){
            weatherTower.unregister(this);
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + ")" + " has landed at " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " +  "0.");
            Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from the tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

    }
}
