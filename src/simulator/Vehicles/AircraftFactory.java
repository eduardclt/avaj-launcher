package simulator.Vehicles;

import weather.Coordinates;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (height < 0 || longitude < 0 || latitude < 0)
        {
            System.out.println("Coordinates cannot be negative");
            throw new NumberFormatException(null);
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type.toLowerCase()){
            case "helicopter":
                return (new Helicopter(name, coordinates));
            case "baloon":
                return (new Balloon(name, coordinates));
            case "jetplane":
                return (new JetPlane(name, coordinates));
            default :
                System.out.println("Invalid Aircraft Found.");
                return (null);
        }
    }
}
