package simulator.Vehicles;

import simulator.Simulator;
import simulator.WeatherTower;
import weather.Coordinates;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    HashMap<String, String> messages = new HashMap<String, String>(){{
        put("snow", " OMG! Winter is coming!");
        put("rain", "It's raining. Better watch out for lightning.");
        put("fog", "Too foggy.");
        put("sun", "What a lovely day.");
    }};


    JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates).toLowerCase();
        if (weather.equals("sun"))
        {
            if (this.coordinates.getHeight() >= 100){
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 0,
                        coordinates.getLatitude() + 10,
                        100
                );
            }else{
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 0,
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2
                );
            }
        }
        else if (weather.equals("rain"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 5,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather.equals("fog"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 1,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather.equals("snow"))
        {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 7
            );
        }
        Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (coordinates.getHeight() <= 0){
            weatherTower.unregister(this);
            Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + ")" + " has landed at " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + "0.");
            Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from the tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

    }
}
