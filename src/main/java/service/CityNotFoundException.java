package service;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(String cityName) {
        super(cityName + " city not found by weather service");
    }
}
