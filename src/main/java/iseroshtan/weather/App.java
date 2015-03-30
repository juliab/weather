package iseroshtan.weather;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.CityNotFoundException;
import iseroshtan.weather.service.WeatherService;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static iseroshtan.weather.Args.*;
import static iseroshtan.weather.io.WeatherData.*;

/**
 * Application that creates csv file with weather information for cities provided by user.
 * Input file format: city name, city area. No header.
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            Args parsedArgs = parseArgs(args);
            Date date = parsedArgs.getDate();
            LOGGER.info("Acquiring weather data on {}. This may take a while...", date);
            List<City> cities = readLocations(parsedArgs.getInputFilePath());
            Map<City, Weather> weatherMap = new HashMap<>();
            WeatherService service = new WeatherService();
            for (City city : cities) {
                try {
                    weatherMap.put(city, service.requestWeather(city, date));
                } catch (CityNotFoundException exception) {
                    LOGGER.warn(exception.getMessage());
                } catch (HttpClientErrorException exception) {
                    LOGGER.error("No response from weather service");
                }
            }
            write(weatherMap, parsedArgs.getOutputFilePath());
            LOGGER.info("CSV file {} was created successfully", parsedArgs.getOutputFilePath());
        } catch (ParseException | java.text.ParseException exception) {
            printHelp();
            System.exit(-1);
        } catch (IOException | InvalidPathException exception) {
            LOGGER.error("Check if input/output file path is valid");
            System.exit(-1);
        }
    }
}
