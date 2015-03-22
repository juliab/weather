package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.City;
import data.Weather;

import org.springframework.web.client.RestTemplate;

final public class WeatherService {

    /**
     * Makes a call to worldweatheronline rest service, extracts weather information from incoming json response
     *
     * @param   city City name to request weather information for
     * @param   date Date to request weather information for
     * @return  Weather instance
     * @throws  CityNotFoundException If no city found by search query
     */
    public Weather requestWeather(City city, String date) throws CityNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = "http://api.worldweatheronline.com/free/v2/past-weather.ashx?" +
                "key={key}&q={city},ua&format=json&date={date}";
        String key = "b868de27c36d1354e818a8447a21a";
        ObjectNode objectNode = restTemplate.getForObject(serviceUrl, ObjectNode.class, key, city.getName(), date);
        if (objectNode.get("data").get("error") != null) {
            throw new CityNotFoundException(city.getName());
        }

        JsonNode dailySummary = objectNode.get("data").get("weather").get(0).get("hourly").get(0);

        String temperatureC = dailySummary.get("tempC").asText();
        String humidity = dailySummary.get("humidity").asText();
        String windSpeed = dailySummary.get("windspeedKmph").asText();
        String pressure = dailySummary.get("pressure").asText();
        return new Weather(temperatureC, humidity, windSpeed, pressure);
    }
}
