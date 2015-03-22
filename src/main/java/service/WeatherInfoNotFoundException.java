package service;

public class WeatherInfoNotFoundException extends Exception {
    public WeatherInfoNotFoundException(String cityName) {
        super("Weather information for " + cityName + " not found by weather service");
    }
}
