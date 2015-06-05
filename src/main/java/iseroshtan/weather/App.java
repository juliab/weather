package iseroshtan.weather;

import com.google.inject.Guice;
import com.google.inject.Injector;
import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.exception.CityNotFoundException;
import iseroshtan.weather.service.WeatherServiceDiModule;
import iseroshtan.weather.service.WeatherGetter;
import iseroshtan.weather.service.exception.UnavailableServiceException;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static iseroshtan.weather.Args.*;
import static iseroshtan.weather.io.WeatherStorage.*;

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
            WeatherGetter weatherGetter = createWeatherGetter();
            for (City city : cities) {
                try {
                    weatherMap.put(city, weatherGetter.getWeather(city, date));
                } catch (CityNotFoundException exception) {
                    LOGGER.warn(exception.getMessage());
                } catch (UnavailableServiceException exception) {
                    LOGGER.error("No response from weather service");
                }
            }
            writeWeather(weatherMap, parsedArgs.getOutputFilePath());
            LOGGER.info("CSV file {} was created successfully", parsedArgs.getOutputFilePath());
        } catch (ParseException | java.text.ParseException exception) {
            printHelp();
            System.exit(-1);
        } catch (IOException | InvalidPathException exception) {
            LOGGER.error("Check if input/output file path is valid");
            System.exit(-1);
        }
    }

    /**
     * Get WeatherGetter instance using Dependency Injection
     * @return WeatherGetter instance
     */
    private static WeatherGetter createWeatherGetter() {
        Injector injector = Guice.createInjector(new WeatherServiceDiModule());
        return injector.getInstance(WeatherGetter.class);
    }
}
