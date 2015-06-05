package iseroshtan.weather.service;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;

import javax.inject.Inject;
import java.util.Date;

public class WeatherGetter {
    private WeatherService service;

    @Inject
    public WeatherGetter(WeatherService service) {
        this.service = service;
    }

    public Weather getWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException {
        return service.requestWeather(city, date);
    }
}
