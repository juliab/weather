package iseroshtan.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.exception.UnavailableServiceException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

final public class TestRestService implements WeatherService {

    public Weather requestWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException {
        return new Weather("20", "69", "27", "1016");
    }
}