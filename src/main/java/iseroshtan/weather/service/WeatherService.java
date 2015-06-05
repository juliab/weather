package iseroshtan.weather.service;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;

import java.util.Date;

public interface WeatherService {
    public Weather requestWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException;
}
