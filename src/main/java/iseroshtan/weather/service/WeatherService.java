package iseroshtan.weather.service;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;

import java.util.Date;

/**
 * This interface defines general type for weather service.
 *
 * @author Julia Seroshtan
 */

public interface WeatherService {
    /**
     * Gets weather data based on provided city and date.
     *
     * @param city city instance to request weather information for
     * @param date date to request weather information for
     * @return weather instance
     * @throws CityNotFoundException if no city found by search query
     * @throws UnavailableServiceException if weather service is unavailable
     */
    public Weather requestWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException;
}
