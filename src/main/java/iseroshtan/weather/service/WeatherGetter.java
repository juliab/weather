package iseroshtan.weather.service;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;

import javax.inject.Inject;
import java.util.Date;

/**
 * The purpose of this class is to get weather data based on provided city and date.
 * Concrete weather service that is uses can be injected.
 *
 * @author Julia Seroshtan
 */

public final class WeatherGetter {
    private final WeatherService service;

    /**
     * Create weather getter.
     *
     * @param service weather service that will be used to get weather
     */
    @Inject
    public WeatherGetter(WeatherService service) {
        this.service = service;
    }

    /**
     * Returns weather data based on provided city and date.
     *
     * @param city city instance to request weather information for
     * @param date date to request weather information for
     * @return weather instance
     * @throws CityNotFoundException if no city found by search query
     * @throws UnavailableServiceException if weather service is unavailable
     */
    public Weather getWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException {
        return service.requestWeather(city, date);
    }
}
