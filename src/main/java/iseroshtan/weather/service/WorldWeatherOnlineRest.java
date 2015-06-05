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

/**
 * This is a concrete weather service implementation. It uses worldweatheronline rest service to get weather data.
 *
 * @author Julia Seroshtan
 */

final public class WorldWeatherOnlineRest implements WeatherService {

    /**
     * Makes a call to worldweatheronline rest service, extracts weather information from incoming json response.
     *
     * @param   city city to request weather information for
     * @param   date date to request weather information for
     * @return  weather instance
     * @throws CityNotFoundException if no city found by search query
     * @throws UnavailableServiceException if 404 error returned by rest service
     */
    public Weather requestWeather(City city, Date date) throws CityNotFoundException, UnavailableServiceException {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = "http://api.worldweatheronline.com/free/v2/past-weather.ashx?" +
                "key={key}&q={city},ua&format=json&date={date}";

        String key = "b868de27c36d1354e818a8447a21a";

        Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectNode objectNode;

        try {
            objectNode = restTemplate.getForObject(serviceUrl, ObjectNode.class,
                    key, city.getName(), dateFormat.format(date));
        } catch (HttpClientErrorException exception) {
            throw new UnavailableServiceException(exception);
        }

        return getWeatherFromJson(objectNode, city.getName());
    }

    private Weather getWeatherFromJson(ObjectNode objectNode, String cityName) throws CityNotFoundException {
        if (objectNode.findValue("error") != null) {
            throw new CityNotFoundException(cityName);
        }

        JsonNode temperatureCJson = objectNode.findValue("tempC");
        String temperatureC = temperatureCJson != null ? temperatureCJson.asText() : null;

        JsonNode humidityJson = objectNode.findValue("humidity");
        String humidity = humidityJson != null ? humidityJson.asText() : null;

        JsonNode windSpeedJson = objectNode.findValue("windspeedKmph");
        String windSpeed = windSpeedJson != null ? windSpeedJson.asText() : null;

        JsonNode pressureJson = objectNode.findValue("pressure");
        String pressure = pressureJson != null ? pressureJson.asText() : null;

        return new Weather(temperatureC, humidity, windSpeed, pressure);
    }
}
