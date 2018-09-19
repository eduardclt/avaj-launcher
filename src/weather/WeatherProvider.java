package weather;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"FOG", "SNOW", "SUN", "RAIN"};

    private WeatherProvider(){

    }

    public static WeatherProvider getProvider(){
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        int i = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + 4;
        return weather[i % 4];
    }
}