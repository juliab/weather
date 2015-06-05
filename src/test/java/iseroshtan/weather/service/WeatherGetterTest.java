package iseroshtan.weather.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for class that returns weather information.
 *
 * @author Julia Seroshtan
 */

public class WeatherGetterTest {

    @Test
    public void getWeatherTest() throws ParseException, UnavailableServiceException, CityNotFoundException {
        // Create weather service instance using injected dependency (test service)
        Injector injector = Guice.createInjector(new WeatherServiceDiTestModule());
        WeatherGetter weatherGetter = injector.getInstance(WeatherGetter.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Weather weather = weatherGetter.getWeather(new City("Donetsk", "Donetsk"), dateFormat.parse("2015-01-01"));
        assertEquals("20", weather.getTemperatureC());
        assertEquals("69", weather.getHumidity());
        assertEquals("27", weather.getWindSpeed());
        assertEquals("1016", weather.getPressure());
    }
}
