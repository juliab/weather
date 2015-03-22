package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.City;
import data.Weather;

import org.springframework.web.client.RestTemplate;

final public class WeatherService {

    /**
     * Makes a call to wunderground rest service, extracts weather information from incoming json response
     *
     * @param   city City name to request weather information for
     * @param   date Date to request weather information for
     * @return  Weather instance
     * @throws  CityNotFoundException If no city found by search query
     * @throws  WeatherInfoNotFoundException If weather information for required city and date wasn't found
     */
    public Weather requestWeather(City city, String date) throws CityNotFoundException, WeatherInfoNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = "http://api.wunderground.com/api/2dcffe6577cd71f9/history_{date}/q/ua/{city}.json";

        ObjectNode objectNode = restTemplate.getForObject(serviceUrl, ObjectNode.class, date, city.getName());
        if (objectNode.get("history") == null) {
            throw new CityNotFoundException(city.getName());
        }

        JsonNode dailySummary = objectNode.get("history").get("dailysummary").get(0);
        if (dailySummary == null) {
            throw new WeatherInfoNotFoundException(city.getName());
        }

        String temperatureC = dailySummary.get("meantempm").asText();
        String humidity = dailySummary.get("humidity").asText();
        String windSpeed = dailySummary.get("meanwindspdm").asText();
        String pressure = dailySummary.get("meanpressurem").asText();
        return new Weather(temperatureC, humidity, windSpeed, pressure);
    }
}
