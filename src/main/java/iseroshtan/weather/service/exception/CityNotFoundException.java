package iseroshtan.weather.service.exception;

/**
 * This is a checked exception indicating that weather cannot be found for provided city.
 *
 * @author Julia Seroshtan
 */

public final class CityNotFoundException extends Exception {

    /**
     * Create exception.
     *
     * @param cityName city name
     */
    public CityNotFoundException(String cityName) {
        super(cityName + " city not found by weather service");
    }
}
