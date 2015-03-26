package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.City;
import data.Weather;

import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

final public class WeatherService {

    /**
     * Makes a call to worldweatheronline rest service, extracts weather information from incoming json response
     *
     * @param   city City name to request weather information for
     * @param   date Date to request weather information for
     * @return  Weather instance
     * @throws  CityNotFoundException If no city found by search query
     */
    public Weather requestWeather(City city, Date date) throws CityNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = "http://api.worldweatheronline.com/free/v2/past-weather.ashx?" +
                "key={key}&q={city},ua&format=json&date={date}";

        String key = "b868de27c36d1354e818a8447a21a";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectNode objectNode = restTemplate.getForObject(serviceUrl, ObjectNode.class,
                key, city.getName(), dateFormat.format(date));
        if (objectNode.findValue("error") != null) {
            throw new CityNotFoundException(city.getName());
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
