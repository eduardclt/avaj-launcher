package simulator.Vehicles;

import simulator.Simulator;
import simulator.WeatherTower;
import weather.Coordinates;

import java.util.HashMap;
import java.util.Map;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    HashMap<String, String> messages = new HashMap<String, String>(){{
        put("snow", "It's snowing. We're gonna crash.");
        put("rain", "Damn you rain! You messed up my baloon.");
        put("fog", "F#@k you fog.");
        put("sun", "Let's enjoy the good weather and take some pics.");
    }};

    Balloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates).toLowerCase();
        if (weather.equals("sun"))
        {
            if (this.coordinates.getHeight() >= 100){
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude() + 0,
                        100
                );
            }else{
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 4
            );
            }
        }
        else if (weather.equals("rain"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 5
            );
        }
        else if (weather.equals("fog"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 3
            );
        }
        else if (weather.equals("snow"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 15
            );
        }
        Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (coordinates.getHeight() <= 0){
            weatherTower.unregister(this);
            Simulator.writer.println("Baloon#" + this.name + "(" + this.id + ")" + " has landed at " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + "0.");
            Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from the tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

    }
}
